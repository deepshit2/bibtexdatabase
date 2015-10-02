
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Inproceedings;


public interface InproceedingsRepository extends JpaRepository<Inproceedings, Long> {
    
}
