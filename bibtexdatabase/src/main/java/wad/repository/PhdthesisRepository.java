
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Phdthesis;

public interface PhdthesisRepository extends JpaRepository<Phdthesis, Long> {
    
    List<Phdthesis> findByAuthorContaining(String name);

    List<Phdthesis> findByTitleContaining(String name);

    List<Phdthesis> findBySchoolContaining(String name);
    
}
