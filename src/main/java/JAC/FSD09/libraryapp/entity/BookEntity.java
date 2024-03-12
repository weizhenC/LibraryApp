package JAC.FSD09.libraryapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;


    @Column(name="title")
    private String title;

    @Column(name="ISBN")
    private String ISBN;

    @Column(name="edition")
    private String edition;

    @Column(name="description")
    private String description;

    @Column(name="amount_in_library")
    private int amountInLibrary;

    @ManyToOne
    //many books is assigned to be associated with one author
    @JoinColumn(name="author_id", nullable=false)
    //@JoinColumn  specifies the foreign key column that links the BookEntity table to the related AuthorEntity and CategoryEntity tables.
    //For the author attribute, the foreign key column is named author_id.
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    //@JoinColumn  specifies the foreign key column that links the BookEntity table to the related AuthorEntity and CategoryEntity tables.
    //For the category attribute, the foreign key column is named category_id.
    private CategoryEntity category;

}
