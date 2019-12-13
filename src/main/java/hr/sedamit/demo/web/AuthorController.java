package hr.sedamit.demo.web;

import hr.sedamit.demo.model.Author;
import hr.sedamit.demo.service.AuthorManager;
import hr.sedamit.demo.web.commands.UpdateAuthorCommand;
import hr.sedamit.demo.web.dto.DTOFactory;
import hr.sedamit.demo.web.dto.AuthorDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController {

    private AuthorManager authorManager;

    @Value("${author.list.allowed:true}")
    private boolean allowListAuthors;

    public AuthorController(AuthorManager authorManager) {
        this.authorManager = authorManager;
    }


    @ApiOperation(value = "List all authors.")
    @GetMapping(value = "/list")
    public Page<AuthorDTO> listAllAuthors(Pageable pageable, Sort sort){

        if (allowListAuthors) {
            Page<Author> allAuthors = authorManager.getAllAuthors(pageable);
            return allAuthors.map(DTOFactory::toAuthorDTO);
        }
        else {
            log.error("Author listing not allowed!");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Author listing not allowed!");
        }
    }

    @ApiOperation(value = "Gets author by id")
    @GetMapping("/{authorId}")
    public AuthorDTO showAuthorDetails(@PathVariable Long authorId) {
        Optional<Author> optionalAuthor = authorManager.getAuthor(authorId);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            return DTOFactory.toAuthorDTO(author);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with such id not found: " + authorId);
    }

    @PostMapping("/add")
    public AuthorDTO addAuthor(@RequestBody UpdateAuthorCommand authorData) {
        Author author = new Author();
        updateAuthorData(author, authorData);
        Author savedAuthor = authorManager.save(author);
        return DTOFactory.toAuthorDTO(savedAuthor);
    }

    @PutMapping("/{authorId}/update")
    public AuthorDTO updateAuthor(@PathVariable Long authorId,
                              @RequestBody UpdateAuthorCommand authorData) {
        Optional<Author> optionalAuthor = authorManager.getAuthor(authorId);
        if (!optionalAuthor.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No optionalAuthor with such id: " + authorId);
        Author author = optionalAuthor.get();
        updateAuthorData(author, authorData);
        Author savedAuthor = authorManager.save(author);
        return DTOFactory.toAuthorDTO(savedAuthor);
    }

    @DeleteMapping("/delete/{authorId}")
    public Boolean deleteAuthor(@PathVariable Long authorId) {
        try {
            authorManager.delete(authorId);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @PostConstruct
    public void init(){
        log.info("Author controller ready");
    }

    private void updateAuthorData(Author author, UpdateAuthorCommand command) {
        author.setFirstName(command.getFirstName());
        author.setLastName(command.getLastName());
        author.setNationality(command.getNationality());
        author.setYearOfBirth(command.getYearOfBirth());
    }

}
