package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthorContaining(String name);

    List<Book> findByTitleContaining(String name);

}
