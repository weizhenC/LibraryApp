package JAC.FSD09.libraryapp.repository;

import JAC.FSD09.libraryapp.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
*The AuthorRepository interface is a Spring Data JPA repository interface. It extends the JpaRepository interface, which is a part of Spring Data JPA. This interface provides methods for performing CRUD (Create, Read, Update, Delete) operations on the AuthorEntity entities.
 * */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
