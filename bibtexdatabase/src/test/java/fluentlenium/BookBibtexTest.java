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
import wad.domain.Book;
import wad.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class BookBibtexTest extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    public WebDriver webDriver = new HtmlUnitDriver();
    
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    /*
    @Autowired
    private BookRepository repo;
    
    @Before
    public void setUp() {
        repo.deleteAll();
        Book article1 = new Book();
        article1.setCitation("artsu");
        article1.setAuthor("kirjoittaja1");
        article1.setTitle("otsikko1");
        article1.setPublisher("journal");
        article1.setYear(2001);
        repo.save(article1);
    }
    
    @Test
    public void submitArticle(){
        goTo("http://localhost:" +serverPort+"/books/1/bibtex");
        assertTrue(pageSource().contains("@Book {"));
        assertTrue(pageSource().contains("title"));
        assertTrue(pageSource().contains("year"));
        assertTrue(pageSource().contains("}"));
    }
    */
    @Test
    public void test() {
        
    }
    
}
