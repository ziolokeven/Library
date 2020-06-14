package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.Book;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Book, Long>, CrudRepository<Book, Long> {

    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = :bookId")
    void deleteById(@Param("bookId") Long bookId);

    List<Book> findByOrderByReleasedYearDesc();

    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', :title, '%'))")
    List<Book> findAllByTitleLikeIgnoreCase(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE CAST(b.releasedYear as text) LIKE concat('%', CAST(:year as text), '%')")
    List<Book> findAllByYearLike(@Param("year") int year);
}
