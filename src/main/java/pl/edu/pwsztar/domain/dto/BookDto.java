package pl.edu.pwsztar.domain.dto;

public final class BookDto {
  private final Long id;
  private final String title;
  private final String image;
  private final int releasedYear;
  private final int toBorrow;


  public BookDto(Long id, String title, String image, int releasedYear, int toBorrow) {
    this.id = id;
    this.title = title;
    this.image = image;
    this.releasedYear = releasedYear;
    this.toBorrow = toBorrow;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getImage() {
    return image;
  }

  public int getReleasedYear() {
    return releasedYear;
  }

  public int getToBorrow() {
    return toBorrow;
  }
}
