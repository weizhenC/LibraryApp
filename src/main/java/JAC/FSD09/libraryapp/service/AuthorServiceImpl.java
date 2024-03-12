package JAC.FSD09.libraryapp.service;

import JAC.FSD09.libraryapp.domain.Author;
import JAC.FSD09.libraryapp.entity.AuthorEntity;
import JAC.FSD09.libraryapp.exception.IdNotFoundException;
import JAC.FSD09.libraryapp.mapper.AuthorMapperHelper;
import JAC.FSD09.libraryapp.repository.AuthorRepository;
import jakarta.persistence.Converts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
    /*
    * Dependencies Injection:
    * AuthorRepository: This is injected via constructor injection. It is used to interact with the database to
    * perform CRUD operations on author entities.
    * AuthorMapperHelper: This is also injected via constructor injection. It provides methods to convert between
    * domain objects (Author) and entity objects (AuthorEntity).
    * */

    private final AuthorRepository authorRepository;

    private final AuthorMapperHelper mapperHelper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapperHelper mapperHelper) {
        this.authorRepository = authorRepository;
        this.mapperHelper = mapperHelper;
    }

    @Override
    public List<Author> getAllAuthors() {
        //Retrieves all author entities from the database using the authorRepository.findAll() method.
        List<AuthorEntity> all = authorRepository.findAll();
       // Converts the list of AuthorEntity objects to a list of Author domain objects
        return mapperHelper.convertAuthorEntityListToAuthorList(all);
    }

    @Override
    public Long saveAuthor(Author author) {
        //Converts the Author domain object to an AuthorEntity object
        AuthorEntity authorEntity = mapperHelper.convertAuthorToAuthorEntity(author);
        //Saves the AuthorEntity object in the database using the authorRepository.save() method.
        AuthorEntity save = authorRepository.save(authorEntity);
        //Returns the ID of the saved author entity.
        return save.getAuthor_id();
    }

    @Override
    public Author findAuthorById(Long authorId) throws IdNotFoundException {
        //Retrieves the author entity by ID from the database
        Optional<AuthorEntity> byId = authorRepository.findById(authorId);

        if(byId.isPresent()){
            //If the author entity is present, it converts it to a Author domain object using the mapperHelper.convertAuthorEntityToAuthor() method and returns it.
            AuthorEntity authorEntity = byId.get();
            return mapperHelper.convertAuthorEntityToAuthor(authorEntity);
        }

        throw new IdNotFoundException("There is no author by id " + authorId);
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        //Deletes the author entity from the database by ID using the authorRepository.deleteById(authorId) method.
        authorRepository.deleteById(authorId);
    }
}
/*
* Overall, the AuthorServiceImpl class encapsulates the business logic for managing authors, including retrieving, saving, finding by ID, and deleting authors from the database. It utilizes the AuthorRepository for database interactions and the AuthorMapperHelper for mapping between domain and entity objects.
 */
