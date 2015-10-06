package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Techreport;

public interface TechreportRepository extends JpaRepository<Techreport, Long> {

    List<Techreport> findByAuthorContaining(String name);

    List<Techreport> findByTitleContaining(String name);

    List<Techreport> findByInstitutionContaining(String name);

}
