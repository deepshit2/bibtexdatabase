
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
