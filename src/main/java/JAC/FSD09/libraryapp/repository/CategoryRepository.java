package JAC.FSD09.libraryapp.repository;

import JAC.FSD09.libraryapp.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
