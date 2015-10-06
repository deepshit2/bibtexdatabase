
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Inproceedings;

public interface InproceedingsRepository extends JpaRepository<Inproceedings, Long> {
    
    List<Inproceedings> findByAuthorContaining(String name);

    List<Inproceedings> findByTitleContaining(String name);

    List<Inproceedings> findByBooktitleContaining(String name);
    
}
