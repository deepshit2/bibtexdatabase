
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Inbook;

public interface InbookRepository extends JpaRepository<Inbook, Long> {
    
    List<Inbook> findByAuthorContaining(String name);

    List<Inbook> findByTitleContaining(String name);
    
    List<Inbook> findByPublisherContaining(String name);
    
}
