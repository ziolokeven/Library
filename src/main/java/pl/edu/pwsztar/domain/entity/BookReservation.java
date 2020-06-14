package pl.edu.pwsztar.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_reservations")
public class BookReservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "book_id")
  private Long bookId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "reservation_from")
  private LocalDateTime from;

  public BookReservation() {
  }

  public BookReservation(Long bookId, Long userId, LocalDateTime from) {
    this.bookId = bookId;
    this.userId = userId;
    this.from = from;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public LocalDateTime getFrom() {
    return from;
  }

  public Long getBookId() {
    return bookId;
  }

}
