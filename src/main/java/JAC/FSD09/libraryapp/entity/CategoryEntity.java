package JAC.FSD09.libraryapp.entity;

import JAC.FSD09.libraryapp.domain.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="description")
    private String description;

    @Column(name="age_range")
    private String ageRange;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="category")
//    private List<BookEntity> books;
}
