package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.BookDto;
import pl.edu.pwsztar.domain.entity.Book;

@Component
public final class BookMapper {
  public BookDto toDto(Book book) {
    int toBorrow = book.getQuantity() - book.getReservationsSize();
    return new BookDto(book.getId(), book.getTitle(), book.getImage(), book.getReleasedYear(), toBorrow);
  }
}
