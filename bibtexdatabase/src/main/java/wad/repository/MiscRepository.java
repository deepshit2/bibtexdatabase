
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Misc;

public interface MiscRepository extends JpaRepository<Misc, Long> {
    
    List<Misc> findByAuthorContaining(String name);

    List<Misc> findByTitleContaining(String name);
    
}
