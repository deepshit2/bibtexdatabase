package fluentlenium;


import org.fluentlenium.adapter.FluentTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wad.Application;
import wad.domain.Article;
import wad.repository.ArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ArticleBibtexTest extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    public WebDriver webDriver = new HtmlUnitDriver();
    
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    @Autowired
    private ArticleRepository repo;
    
    @Before
    public void setUp() {
        repo.deleteAll();
        Article article1 = new Article();
        article1.setCitation("artsu");
        article1.setAuthor("kirjoittaja1");
        article1.setTitle("otsikko1");
        article1.setJournal("journal");
        article1.setYear(2001);
        article1.setVolume(2);
        repo.save(article1);
    }
    
    @Test
    public void submitArticle(){
        goTo("http://localhost:" +serverPort+"/articles/1/bibtex");
        assertTrue(pageSource().contains("@Article {"));
        assertTrue(pageSource().contains("title"));
        assertTrue(pageSource().contains("year"));
        assertTrue(pageSource().contains("}"));
    }
}
