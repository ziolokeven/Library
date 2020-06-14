package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.*;
import pl.edu.pwsztar.domain.entity.Book;
import pl.edu.pwsztar.domain.mapper.BookMapper;
import pl.edu.pwsztar.domain.repository.LibraryRepository;
import pl.edu.pwsztar.exception.BookNotFound;
import pl.edu.pwsztar.exception.UserNotFound;
import pl.edu.pwsztar.service.LibraryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final UserServiceImpl userService;
    private final BookMapper bookMapper;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, UserServiceImpl userService, BookMapper bookMapper) {
        this.libraryRepository = libraryRepository;
        this.userService = userService;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> findAll() {
        return libraryRepository.findAll().stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAllByYearDesc() {
        return libraryRepository.findByOrderByReleasedYearDesc().stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAllByTitleLikeIgnoreCase(String title) {
        return libraryRepository.findAllByTitleLikeIgnoreCase(title).stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAllByYearLike(int year) {
        return libraryRepository.findAllByYearLike(year).stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void createBook(CreateBook createBook, UserDto user) {
        if (user != null && user.isAdmin()) {
            Book book = new Book(createBook.getTitle(), createBook.getImage(), createBook.getReleasedYear(), createBook.getQuantity());
            libraryRepository.save(book);
        }
    }

    @Override
    public void deleteBook(Long bookId, UserDto user) {
        if (user != null && user.isAdmin()) {
            Optional<Book> book = libraryRepository.findById(bookId);
            if (book.isPresent()) {
                libraryRepository.deleteById(bookId);
            }
        }
    }

    @Override
    public BookCounterDto countBooks() {
        long count = libraryRepository.count();
        return new BookCounterDto(count);
    }

    @Override
    public BookDto borrowBook(BookBorrowedDto bookBorrowed) {
        if (bookBorrowed.getUser() == null) {
            throw new UserNotFound();
        }
        UserDto user = userService.getUser(bookBorrowed.getUser().getLogin());
        Book book = libraryRepository.findById(bookBorrowed.getBookId()).orElseThrow(() -> new BookNotFound());
        book.makeReservation(user);
        libraryRepository.save(book);

        return bookMapper.toDto(book);
    }

    @Override
    public BookDto returnBook(BookReturnedDto bookReturned) {
        if (bookReturned.getUser() == null) {
            throw new UserNotFound();
        }
        UserDto user = userService.getUser(bookReturned.getUser().getLogin());
        Book book = libraryRepository.findById(bookReturned.getBookId()).orElseThrow(() -> new BookNotFound());
        book.returnBook(user);
        libraryRepository.save(book);

        return bookMapper.toDto(book);
    }
}
