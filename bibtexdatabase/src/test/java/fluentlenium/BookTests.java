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
public class BookTests extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    public WebDriver webDriver = new HtmlUnitDriver();
    
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    @Before
    public void setUp() {
        goTo("http://localhost:" +serverPort+"/books/new");
        fill("#citation").with("kirja");
        fill("#author").with("Santeri");
        fill("#publisher").with("kustantamo");
        fill("#year").with("1234");
        fill("#title").with("Eeppinen väitöskirja");
        submit("button[type=submit]");
    }
    
    @Test
    public void findBook(){
        goTo("http://localhost:" +serverPort+"/books/1");
        assertTrue(pageSource().contains("1234"));
        assertTrue(pageSource().contains("Santeri"));
    }
    

}
