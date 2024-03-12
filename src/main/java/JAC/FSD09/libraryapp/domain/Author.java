package JAC.FSD09.libraryapp.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Author {
    //the domain classes represent the business logic of the application.

    private Long author_id;

    @NotEmpty
    @NotBlank
    private String authorName;
    private String nationality;
    private String gender;
    private String yearBirth;

}

