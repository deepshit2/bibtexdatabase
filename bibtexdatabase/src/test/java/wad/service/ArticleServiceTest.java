
package wad.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wad.Application;
import wad.domain.Article;
import wad.repository.ArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    private Article article1;
    private Article article2;
    
    @Before
    public void setUp() {
        articleRepository.deleteAll();
        article1 = new Article();
        article1.setCitation("artsu");
        article1.setAuthor("kirjoittaja1");
        article1.setTitle("otsikko1");
        article1.setJournal("journal");
        article1.setYear(2001);
        article1.setVolume(2);
        article2 = new Article();
        article2.setCitation("artsu2");
        article2.setAuthor("kirjoittaja2");
        article2.setTitle("otsikko2");
        article2.setJournal("journal2");
        article2.setYear(2002);
        article2.setVolume(3);
    }
    
    @Test
    public void testAddArticle() {
        articleService.addArticle(article1);
        Article retrieved = articleRepository.findOne(article1.getId());
        assertEquals(retrieved.getId(), article1.getId());
        assertEquals(retrieved.getAuthor(), article1.getAuthor());
        assertEquals(retrieved.getTitle(), article1.getTitle());
    }
    
    @Test
    public void testDeleteArticle() {
        articleRepository.save(article1);
        Long id = article1.getId();
        articleService.deleteArticle(article1.getId());
        Article eiOle = articleRepository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetArticle() {
        articleRepository.save(article1);
        Article retrieved = articleService.getArticle(article1.getId());
        assertEquals(retrieved.getId(), article1.getId());
        assertEquals(retrieved.getAuthor(), article1.getAuthor());
        assertEquals(retrieved.getTitle(), article1.getTitle());
    }
    
    @Test
    public void testListArticles() {
        articleRepository.save(article1);
        articleRepository.save(article2);
        List<Article> articles = articleService.list();
        List<String> titles = new ArrayList();
        for(Article a : articles) {
            titles.add(a.getTitle());
        }
        assertTrue(titles.contains(article1.getTitle()));
        assertTrue(titles.contains(article2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        articleRepository.save(article1);
        articleRepository.save(article2);
        List<Article> articles = articleService.search("aja1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), article1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        articleRepository.save(article1);
        articleRepository.save(article2);
        List<Article> articles = articleService.search("kko1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), article1.getAuthor());
    }

    @Test
    public void testSearcCanFindAll() {
        articleRepository.save(article1);
        articleRepository.save(article2);
        List<Article> articles = articleService.search("ikko");
        assertTrue(articles.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        articleRepository.save(article1);
        articleRepository.save(article2);
        List<Article> articles = articleService.search("batman134134");
        assertTrue(articles.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        articleRepository.save(article1);
        String bibtex = articleService.getBibtex(article1.getId());
        assertTrue(bibtex.contains("@Article"));
        assertTrue(bibtex.contains("{"));
        assertTrue(bibtex.contains("}"));
        assertTrue(bibtex.contains(","));
        assertTrue(bibtex.contains("kirjoittaja1"));
    }
    
    
}
