/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fluentlenium;

import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
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
public class UnpublishedTest extends FluentTest {
    @Value("${local.server.port}")
    private int serverPort;
    public WebDriver webDriver = new HtmlUnitDriver();
    
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    @Before
    public void init(){
        goTo("http://localhost:" + serverPort + "/unpublisheds/new");
        fill("#author").with("Santeri");
        fill("#title").with("Eeppinen julkaisematon salaisuus");
        fill("#citation").with("artsu");
        fill("#note").with("paras julkaisu sitten julkaisemattomien ä");
        fill("#year").with("1999");
        submit("button[type=submit]");
    }
    
    @Test
    public void submitUnpublished(){
        assertTrue(pageSource().contains("New unpublished created"));
    }
    
    @Test
    public void unpublishedBibtex(){
        assertTrue(pageSource().contains("@Unpublished {"));
        assertTrue(pageSource().contains("1999"));
        assertTrue(pageSource().contains("year"));
        assertTrue(pageSource().contains("julkaisemattomien {\\\"a}"));
        assertTrue(pageSource().contains("}"));
    }
}
