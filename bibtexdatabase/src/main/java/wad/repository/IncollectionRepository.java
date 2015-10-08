
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Incollection;

public interface IncollectionRepository extends JpaRepository<Incollection, Long> {
    
    List<Incollection> findByAuthorContaining(String name);

    List<Incollection> findByTitleContaining(String name);

    List<Incollection> findByBooktitleContaining(String name);
    
}
