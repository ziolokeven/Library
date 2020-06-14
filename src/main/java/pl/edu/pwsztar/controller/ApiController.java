package pl.edu.pwsztar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.dto.*;
import pl.edu.pwsztar.service.LibraryService;
import pl.edu.pwsztar.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value="/api")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    private final LibraryService libraryService;
    private final UserService userService;
    private UserDto currentUser;

    @Autowired
    public ApiController(LibraryService libraryService, UserService userService) {
        this.libraryService = libraryService;
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping(value = "/user/register")
    public ResponseEntity<UserDto> register(@RequestBody Registration registration) {
        LOGGER.info("login user");
        UserDto registered = userService.register(registration);
        currentUser = registered;

        return new ResponseEntity<>(registered, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/user/login")
    public ResponseEntity<UserDto> login(@RequestBody Login login) {
        LOGGER.info("login user");
        UserDto logged = userService.login(login);
        currentUser = logged;

        return new ResponseEntity<>(logged, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/user/current")
    public ResponseEntity<UserDto> getCurrentUser() {
        LOGGER.info("login user");

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/user/logout")
    public ResponseEntity<Void> logout() {
        LOGGER.info("user logout");
        currentUser = null;

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BookDto>> getBooks() {
        LOGGER.info("find all books");

        List<BookDto> moviesDto = libraryService.findAll();
        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody CreateBook createBook) {
        LOGGER.info("create book: {}", createBook);
        libraryService.createBook(createBook, currentUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping(value = "/books/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        LOGGER.info("delete book: {}", bookId);

        libraryService.deleteBook(bookId, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/books/title/{title}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BookDto>> getBooksWithTitle(@PathVariable String title) {
        LOGGER.info("search book by title: {}", title);

        List<BookDto> movies = libraryService.findAllByTitleLikeIgnoreCase(title);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/books/year/{year}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BookDto>> getBooksWithYear(@PathVariable Integer year) {
        LOGGER.info("search book by year: {}", year);

        List<BookDto> movies = libraryService.findAllByYearLike(year);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/books/counter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BookCounterDto> countBooks() {
        LOGGER.info("count books");

        return new ResponseEntity<>(libraryService.countBooks(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/books/borrow/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BookDto> borrowBook(@PathVariable Long id) {
        LOGGER.info("borrow book: {}", id);
        BookDto book = libraryService.borrowBook(new BookBorrowedDto(currentUser, id));
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "/books/return/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BookDto> returnBook(@PathVariable Long id) {
        LOGGER.info("return book: {}", id);
        BookDto book = libraryService.returnBook(new BookReturnedDto(currentUser, id));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
