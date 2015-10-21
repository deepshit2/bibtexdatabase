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
        element.sendKeys("BC1");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Welcome to my life");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Barry Caxton");
        element = driver.findElement(By.name("howpublished"));
        element.sendKeys("Not known");
        element = driver.findElement(By.name("address"));
        element.sendKeys("Maydayhill 9");
        element = driver.findElement(By.name("month"));
        element.sendKeys("10");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1991");    
        element = driver.findElement(By.name("note"));
        element.sendKeys("note for readers");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi booklet tallennetaan', {
        driver.getPageSource().contains("New booklet created").shouldBe true
    }
}



