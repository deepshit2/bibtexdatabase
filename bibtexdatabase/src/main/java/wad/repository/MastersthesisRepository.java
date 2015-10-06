
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Mastersthesis;

public interface MastersthesisRepository extends JpaRepository<Mastersthesis, Long> {
    
    List<Mastersthesis> findByAuthorContaining(String name);

    List<Mastersthesis> findByTitleContaining(String name);

    List<Mastersthesis> findBySchoolContaining(String name);
    
}
