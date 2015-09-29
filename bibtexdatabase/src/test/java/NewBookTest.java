/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.fluentlenium.adapter.FluentTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *
 * @author santeri
 */
public class NewBookTest extends FluentTest {
    
public WebDriver webDriver = new HtmlUnitDriver();
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    @Test
    public void submitBook(){
        goTo("http://bibtexdatabase.herokuapp.com/books/new");
        fill("#author").with("Santeri");
        fill("#title").with("Eeppinen väitöskirja");
        submit("input[type=submit]");
        assertTrue(pageSource().contains("New book created"));
    }
}
