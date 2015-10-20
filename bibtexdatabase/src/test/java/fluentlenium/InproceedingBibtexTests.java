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
public class InproceedingBibtexTests extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    public WebDriver webDriver = new HtmlUnitDriver();
    
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    @Autowired
    private InproceedingsRepository repo;
    
    @Before
    public void setUp() {
        goTo("http://localhost:" +serverPort+"/inproceedings/new");
        fill("#author").with("Santeri");
        fill("#citation").with("seksiwau");
        fill("#booktitle").with("Kumpula");
        fill("#year").with("2015");
        fill("#title").with("Eeppinen seksiopas");
        submit("button[type=submit]");
    }
    
    @Test
    public void inproBibtex() {
        goTo("http://localhost:" +serverPort+"/inproceedings/1/bibtex");
        assertTrue(pageSource().contains("@Inproceedings"));
        assertTrue(pageSource().contains("}"));
        assertTrue(pageSource().contains("Santeri"));
        assertTrue(pageSource().contains(","));
    }
    
}
