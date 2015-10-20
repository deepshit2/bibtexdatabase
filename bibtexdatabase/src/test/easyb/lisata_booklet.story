import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä booklet-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä bookletin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new booklet"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("citation");
        element = driver.findElement(By.name("title"));
        element.sendKeys("otsikko");
        element = driver.findElement(By.name("author"));
        element.sendKeys("kirjoittaja");
        element = driver.findElement(By.name("howpublished"));
        element.sendKeys("miten julkaistu");
        element = driver.findElement(By.name("address"));
        element.sendKeys("osoite 10");
        element = driver.findElement(By.name("month"));
        element.sendKeys("12");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");    
        element = driver.findElement(By.name("note"));
        element.sendKeys("note");
        element = driver.findElement(By.name("key"));
        element.sendKeys("avain");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi booklet tallennetaan', {
        driver.getPageSource().contains("New booklet created").shouldBe true
    }
}



