/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fluentlenium;

import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wad.Application;

/**
 *
 * @author santeri
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ConferenceTest extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    public WebDriver webDriver = new HtmlUnitDriver();
    
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    @Test
    public void submitConference(){
        goTo("http://localhost:" +serverPort+"/conferences/new");
        fill("#author").with("Santeri");
        fill("#title").with("Eeppinen konferenssi");
        fill("#citation").with("artsu");
        fill("#booktitle").with("bokkerie");
        fill("#year").with("1999");
        submit("button[type=submit]");
        assertTrue(pageSource().contains("New conference created"));
    }
}
