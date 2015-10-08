
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    
    List<Conference> findByAuthorContaining(String name);

    List<Conference> findByTitleContaining(String name);

    List<Conference> findByBooktitleContaining(String name);
    
}
