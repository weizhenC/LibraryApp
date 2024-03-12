package JAC.FSD09.libraryapp.repository;

import JAC.FSD09.libraryapp.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
