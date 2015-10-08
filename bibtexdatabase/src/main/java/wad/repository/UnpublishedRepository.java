package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Book;
import wad.domain.Unpublished;

public interface UnpublishedRepository extends JpaRepository<Unpublished, Long> {

    List<Unpublished> findByAuthorContaining(String name);

    List<Unpublished> findByTitleContaining(String name);

}
