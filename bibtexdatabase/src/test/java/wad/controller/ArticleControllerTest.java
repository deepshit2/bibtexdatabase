package wad.controller;

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
public class ArticleControllerTest extends FluentTest {
    
public WebDriver webDriver = new HtmlUnitDriver();
    public WebDriver getDefaultDriver(){
        return webDriver;
    }
    
    @Test
    public void submitArticle(){
        goTo("http://bibtexdatabase.herokuapp.com/articles/new");
        fill("#author").with("Santeri");
        fill("#title").with("Eeppinen kandi");
        submit("input[type=submit]");
        assertTrue(pageSource().contains("New article created"));
    }
}
