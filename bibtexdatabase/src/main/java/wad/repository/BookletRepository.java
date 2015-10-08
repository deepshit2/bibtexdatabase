
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Booklet;

public interface BookletRepository extends JpaRepository<Booklet, Long> {
    
    List<Booklet> findByAuthorContaining(String name);

    List<Booklet> findByTitleContaining(String name);
    
}
