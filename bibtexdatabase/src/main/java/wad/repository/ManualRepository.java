
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Manual;

public interface ManualRepository extends JpaRepository<Manual, Long> {
    
    List<Manual> findByAuthorContaining(String name);

    List<Manual> findByTitleContaining(String name);
    
    
    
}
