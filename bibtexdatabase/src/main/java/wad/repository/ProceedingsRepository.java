
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Inproceedings;
import wad.domain.Proceedings;

public interface ProceedingsRepository extends JpaRepository<Proceedings, Long> {
    
    List<Proceedings> findByEditorContaining(String name);

    List<Proceedings> findByTitleContaining(String name);
    
}
