package pl.edu.pwsztar.domain.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.BookDto;
import pl.edu.pwsztar.domain.dto.FileDto;

import java.util.Date;
import java.util.List;

@Component
public final class FileMapper {

  public FileDto convert(List<BookDto> from) {
    String name = "books_" + new Date().getTime();
    String extension = "txt";

    String content = from.stream()
        .map(book -> book.getReleasedYear() + " " + book.getTitle() + "\n")
        .reduce("", String::concat);

    return new FileDto.Builder()
        .name(name)
        .content(content)
        .extension(extension)
        .build();
  }
}
