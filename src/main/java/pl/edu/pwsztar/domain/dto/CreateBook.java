package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;

public final class CreateBook implements Serializable {
  private String title;
  private String image;
  private int releasedYear;
  private int quantity;

  public CreateBook() {
  }

  public CreateBook(String title, String image, int releasedYear, int quantity) {
    this.title = title;
    this.image = image;
    this.releasedYear = releasedYear;
    this.quantity = quantity;
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

  public int getQuantity() {
    return quantity;
  }
}
