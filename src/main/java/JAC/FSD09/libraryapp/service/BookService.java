package JAC.FSD09.libraryapp.service;

import JAC.FSD09.libraryapp.domain.Book;
import JAC.FSD09.libraryapp.exception.IdNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Long saveBook(Book book);

    Book findBookById(Long bookId) throws IdNotFoundException;

    void deleteBookById(Long bookId);
}
