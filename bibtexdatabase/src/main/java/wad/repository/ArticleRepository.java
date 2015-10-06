
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    
        List<Article> findByAuthorContaining(String name);

    List<Article> findByTitleContaining(String name);
    
}
