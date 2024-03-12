package JAC.FSD09.libraryapp.mapper;

import JAC.FSD09.libraryapp.domain.Author;
import JAC.FSD09.libraryapp.dto.AuthorDTO;
import JAC.FSD09.libraryapp.entity.AuthorEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapperHelper {
    /*
    * this class facilitates the conversion between different representations of author data (DTOs, entities, and domain objects) in the application, making it easier to work with different layers of the system.
    * */

    //Constructor Injection: the class has a constructor that injects an ObjectMapper instance.
    private final ObjectMapper mapper;

    @Autowired
    //The @Autowired annotation is used in Spring to automatically inject dependencies into a Spring bean.
    //In this context, an instance of ObjectMapper should be automatically provided when an instance of AuthorMapperHelper is created.
    public AuthorMapperHelper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /*
    * takes a list of Author objects and converts them into a list of AuthorDTO objects using the ObjectMapper's convertValue method.
    * */
    public List<AuthorDTO> convertAuthorListToAuthorDTOList(List<Author> authors){
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        for(Author temp: authors){
            authorDTOs.add(mapper.convertValue(temp, AuthorDTO.class));
        }

        return authorDTOs;
    }

    public List<Author> convertAuthorEntityListToAuthorList(List<AuthorEntity> authorEntities) {
        List<Author> authorList = new ArrayList<>();
        for (AuthorEntity temp: authorEntities){
            authorList.add(mapper.convertValue(temp, Author.class));
        }
        return authorList;
    }

    public Author convertAuthorEntityToAuthor(AuthorEntity authorEntity) {
        return mapper.convertValue(authorEntity, Author.class);
    }

    public AuthorEntity convertAuthorToAuthorEntity(Author author){
        return mapper.convertValue(author, AuthorEntity.class);
    }

    public Author convertAuthorDTOToAuthor(AuthorDTO authorDTO) {
        return mapper.convertValue(authorDTO, Author.class);
    }

}
