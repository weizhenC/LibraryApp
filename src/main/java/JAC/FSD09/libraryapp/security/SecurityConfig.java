package JAC.FSD09.libraryapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//@Configuration: Indicates that this class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions.
@Configuration
//@EnableWebSecurity: Enables Spring Security's web security support and provides the Spring MVC integration.
@EnableWebSecurity
public class SecurityConfig {
    /*
     the SecurityConfig class sets up the security configuration for the library application, defining access rules,
     authentication mechanisms, and exception handling. It ensures that certain URLs are accessible only to users with specific roles and provides form-based login and logout functionality.
    */

    @Bean
    //Creates an instance of JdbcUserDetailsManager, which is responsible for managing user details using JDBC-based authentication.
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Configures security filters for HTTP requests using the HttpSecurity object.
        //Defines authorization rules for different URL patterns and roles using the authorizeHttpRequests() method.
        http.authorizeHttpRequests((requests) -> requests
                       //Permit access to static resources such as images and CSS files using the permitAll() method.
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/CSS/**").permitAll()
                       //Define access rules for specific URLs based on user roles (e.g., hasAnyRole("USER", "EMPLOYEE", "MANAGER")).
                        .requestMatchers("/book/user_list").hasAnyRole("USER", "EMPLOYEE", "MANAGER")
                        .requestMatchers("/staff/**", "/category/**", "/author/**", "/book/**").hasAnyRole("EMPLOYEE", "MANAGER")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                //Configures form-based authentication using the formLogin() method, specifying the login page, login processing URL, and default success URL.
                .formLogin(form -> form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                //Handles access-denied exceptions by redirecting to the specified access-denied page.
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"))
                //Configures logout functionality using the logout() method.
                .logout(LogoutConfigurer::permitAll)
        ;

        return http.build();
    }

}
