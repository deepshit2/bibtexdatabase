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
        goTo("http://localhost:" +serverPort+"/articles/new");
        fill("#author").with("Santeri");
        fill("#title").with("Eeppinen kandi");
        fill("#citation").with("artsu");
        fill("#journal").with("test");
        fill("#year").with("1999");
        fill("#volume").with("1");
        submit("button[type=submit]");
    }
    
    @Test
    public void articleBibtex(){
        goTo("http://localhost:" +serverPort+"/articles/1/bibtex");
        assertTrue(pageSource().contains("@Article {"));
        assertTrue(pageSource().contains("1999"));
        assertTrue(pageSource().contains("year"));
        assertTrue(pageSource().contains("}"));
    }
    
}
