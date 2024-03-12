package JAC.FSD09.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDTO {
    //DTOs are used for transferring data between different layers or components of the application.

    private Long author_id;

    @NotEmpty
    @NotBlank
    private String authorName;
    private String nationality;
    private String gender;
    private String yearBirth;

}
