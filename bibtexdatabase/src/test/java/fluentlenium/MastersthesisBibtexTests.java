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
import wad.domain.Inproceedings;
import wad.repository.InproceedingsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MastersthesisBibtexTests extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    public WebDriver webDriver = new HtmlUnitDriver();
    
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    
    @Before
    public void setUp() {
        goTo("http://localhost:" +serverPort+"/mastersthesises/new");
        fill("#citation").with("meisterpapper");
        fill("#author").with("Santeri");
        fill("#school").with("Kumpula");
        fill("#year").with("2015");
        fill("#title").with("Eeppinen seksiopas");
        submit("button[type=submit]");
    }
    
    
    @Test
    public void masterBibtex() {
        goTo("http://localhost:" +serverPort+"/mastersthesises/1/bibtex");
        assertTrue(pageSource().contains("@Mastersthesis"));
        assertTrue(pageSource().contains("}"));
        assertTrue(pageSource().contains("Santeri"));
        assertTrue(pageSource().contains(","));
    }
    
}
