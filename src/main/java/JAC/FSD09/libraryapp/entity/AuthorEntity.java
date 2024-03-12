package JAC.FSD09.libraryapp.entity;

import JAC.FSD09.libraryapp.domain.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorEntity {
   // Entities represent persistent data structures mapped to database tables and are typically used with ORM
    // frameworks for database interaction.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //The @GeneratedValue annotation in JPA is used to specify how the primary key values for entities are generated.
    private Long author_id;

    @Column(name="author_name")
    private String authorName;

    @Column(name="nationality")
    private String nationality;

    @Column(name="gender")
    private String gender;

    @Column(name="birth_year")
    private String yearBirth;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="author")
//    private List<BookEntity> books;
}
