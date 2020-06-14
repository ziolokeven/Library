package pl.edu.pwsztar.domain.entity;

import pl.edu.pwsztar.domain.dto.UserDto;
import pl.edu.pwsztar.exception.WrongBookInLibrary;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String image;

    @Column(name = "released_year")
    private Integer releasedYear;

    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", updatable = false, insertable = false)
    private Set<BookReservation> reservations = new HashSet<>();

    public Book() {
    }

    public Book(String title, String image, Integer releasedYear, Integer quantity) {
        this.title = title;
        this.image = image;
        this.releasedYear = releasedYear;
        this.quantity = quantity;
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

    public Integer getReleasedYear() {
        return releasedYear;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public int getReservationsSize() {
        if (this.reservations == null) {
            this.reservations = new HashSet<>();
        }
        return this.reservations.size();
    }

    public void makeReservation(UserDto user) {
        if (this.reservations == null) {
            this.reservations = new HashSet<>();
        }
        if (this.reservations.size() < this.quantity) {
            createReservation(user);
        }
    }

    private void createReservation(UserDto user) {
        if (!getReservation(user).isPresent()) {
            BookReservation reservation = new BookReservation(this.id, user.getId(), LocalDateTime.now());
            this.reservations.add(reservation);
        }
    }

    private Optional<BookReservation> getReservation(UserDto user) {
        return this.reservations.stream()
            .filter(reservation -> reservation.getUserId().equals(user.getId()))
            .findFirst();
    }

    public void returnBook(UserDto user) {
        Optional<BookReservation> reservation = getReservation(user);
        if (reservation.isPresent()) {
            this.reservations.remove(reservation.get());
        } else {
            throw new WrongBookInLibrary();
        }
    }
}
