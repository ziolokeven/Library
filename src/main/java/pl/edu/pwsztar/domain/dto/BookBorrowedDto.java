package pl.edu.pwsztar.domain.dto;

public class BookBorrowedDto {

  private final UserDto user;
  private final Long bookId;

  public BookBorrowedDto(UserDto user, Long bookId) {
    this.user = user;
    this.bookId = bookId;
  }

  public UserDto getUser() {
    return user;
  }

  public Long getBookId() {
    return bookId;
  }
}
