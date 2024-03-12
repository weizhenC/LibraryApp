package JAC.FSD09.libraryapp.controller;

import JAC.FSD09.libraryapp.domain.Author;
import JAC.FSD09.libraryapp.dto.AuthorDTO;
import JAC.FSD09.libraryapp.exception.IdNotFoundException;
import JAC.FSD09.libraryapp.mapper.AuthorMapperHelper;
import JAC.FSD09.libraryapp.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller: Indicates that this class serves as a Spring MVC controller.
@Controller
//@RequestMapping("/author"): Specifies the base URL path for mapping requests handled by this controller.
@RequestMapping("/author")
//@SessionAttributes("title"): Specifies that the attribute "title" should be stored in the session.
@SessionAttributes("title")
public class AuthorController {

    /*
    Dependencies:
    AuthorService: Autowired dependency for accessing author-related business logic.
    AuthorMapperHelper: Autowired dependency for mapping between domain objects (Author) and DTOs (AuthorDTO).
    */
    private final AuthorService service;
    private final AuthorMapperHelper mapperHelper;

    @Autowired
    public AuthorController(AuthorService service, AuthorMapperHelper mapperHelper) {
        this.service = service;
        this.mapperHelper = mapperHelper;
    }


    @GetMapping("/list")
    //the Model in Spring MVC is used to pass data to the view so that the view can render the response.
    // By storing the list of authors in the authors attribute of the Model object,the controller passes the author data to the view.
    public String listAuthor(Model theAuthors){
        /*
        * Handles GET requests to retrieve a list of authors. It invokes the getAllAuthors() method of AuthorService to fetch all authors, maps them to AuthorDTO, and adds them to the model before returning the view name.
         */
        List<Author> allAuthors = service.getAllAuthors();
        List<AuthorDTO> authorDTOS = mapperHelper.convertAuthorListToAuthorDTOList(allAuthors);
        theAuthors.addAttribute("authors", authorDTOS);
        //return the thymeleaf
        return "list_author";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //Handles GET requests to display the form for adding a new author. It adds the "title" attribute and an empty AuthorDTO object to the model.
        theModel.addAttribute("title", "Add an Author");
        theModel.addAttribute("author", new AuthorDTO());
        return "author_form";
    }

    @GetMapping("/showFormForUpdate")
    //Handles GET requests to display the form for updating an existing author.
    public String showFormForUpdate(@RequestParam("authorId") Long theId, Model theModel){
        try{
            // retrieves the author by ID using findAuthorById() method of AuthorService
            Author authorById = service.findAuthorById(theId);
            //adds the "title" attribute and the author to the model, and returns the view name
            theModel.addAttribute("title", "Update an Author");
            theModel.addAttribute("author", authorById);
            return "author_form";
        }
        catch (IdNotFoundException exception) {
            theModel.addAttribute("author", new AuthorDTO());
            theModel.addAttribute("title", "Add an Author");
            theModel.addAttribute("exceptionMessage", exception.getMessage());
            return "author_form";
        }
    }

    @PostMapping("/upsert")
    // Handles POST requests to add or update an author.
    public String upsertAuthor(@Valid @ModelAttribute("author") AuthorDTO theAuthor, BindingResult result){
        //validates the AuthorDTO object using JSR-380 annotations. If there are validation errors, it returns the form again.
        if(result.hasErrors()){
            return "author_form";
        }
        //converts the AuthorDTO to Author and saves it using saveAuthor() method of AuthorService, then redirects to the list of authors.
        Author author = mapperHelper.convertAuthorDTOToAuthor(theAuthor);
        service.saveAuthor(author);

        return "redirect:/author/list";
    }

    @GetMapping("/delete")
    //Handles GET requests to delete an author
    public String delete(@RequestParam("authorId") Long theId, Model theModel){
        try{
            service.deleteAuthorById(theId);
            return "redirect:/author/list";
        } catch (DataIntegrityViolationException ex) {
            theModel.addAttribute("error", "Cannot delete the author due to existing dependencies.");
            List<Author> authorList = service.getAllAuthors();
            List<AuthorDTO> authorDTOList = mapperHelper.convertAuthorListToAuthorDTOList(authorList);
            theModel.addAttribute("authors", authorDTOList);
            return "list_author";
        }
    }

}
/*
*the AuthorController class manages CRUD operations for authors, handles form submissions, and interacts with the AuthorService and AuthorMapperHelper to perform business logic and data mapping.
*/
