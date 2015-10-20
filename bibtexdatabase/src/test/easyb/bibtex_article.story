import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan article-viitteen bibtex-muodossa'

scenario "luon viitteen ja saan halutessa dokumentit bibtex-muodossa", {
    given 'inproceeding on luotu', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new article"));       
        element.click();
        element = driver.findElement(By.name("citation"));
        element.sendKeys("artsu");
        element = driver.findElement(By.name("author"));
        element.sendKeys("kirjoittaja");
        element = driver.findElement(By.name("title"));
        element.sendKeys("artikkelin otsikko");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("jossain lehdessa");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2000");
        element = driver.findElement(By.name("number"));
        element.sendKeys("2");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("20");
        element = driver.findElement(By.name("month"));
        element.sendKeys("10");
        element = driver.findElement(By.name("note"));
        element.sendKeys("joooo");
        element = driver.findElement(By.name("volume"));
        element.sendKeys("3");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    when 'käyttäjä on siirtynyt bibtex sivulle', {}

    then 'uusi article-bibtex näytetään', {
        driver.getPageSource().contains("@Article").shouldBe true
    }
}



