package JAC.FSD09.libraryapp.service;

import JAC.FSD09.libraryapp.domain.Author;
import JAC.FSD09.libraryapp.exception.IdNotFoundException;

import java.util.List;

public interface AuthorService {

    /*
    * These methods define the basic operations for managing authors in the library application. Implementations of this interface will provide the actual logic for interacting with the data layer to perform these operations.
    * */
    List<Author> getAllAuthors();

    Long saveAuthor(Author author);

    Author findAuthorById(Long authorId) throws IdNotFoundException;

    void deleteAuthorById(Long authorId);

}
