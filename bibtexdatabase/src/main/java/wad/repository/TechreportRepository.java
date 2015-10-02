
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Techreport;

public interface TechreportRepository extends JpaRepository<Techreport, Long> {
    
}

