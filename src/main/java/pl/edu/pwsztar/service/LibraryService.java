package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dto.*;

import java.util.List;

public interface LibraryService {

    List<BookDto> findAll();

    List<BookDto> findAllByYearDesc();

    List<BookDto> findAllByTitleLikeIgnoreCase(String title);

    List<BookDto> findAllByYearLike(int year);

    void createBook(CreateBook createBook, UserDto user);

    void deleteBook(Long bookId, UserDto user);

    BookCounterDto countBooks();

    BookDto borrowBook(BookBorrowedDto bookBorrowed);

    BookDto returnBook(BookReturnedDto bookReturned);

}
