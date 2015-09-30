import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä book-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä bookin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new book"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("kirjoittaja");
        element = driver.findElement(By.name("title"));
        element.sendKeys("artikkelin otsikko");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("julkaisija");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("volume"));
        element.sendKeys("1");
        element = driver.findElement(By.name("series"));
        element.sendKeys("2");
        element = driver.findElement(By.name("address"));
        element.sendKeys("osoite 10");
        element = driver.findElement(By.name("edition"));
        element.sendKeys("3");
        element = driver.findElement(By.name("month"));
        element.sendKeys("12");
        element = driver.findElement(By.name("note"));
        element.sendKeys("joooo");
        element = driver.findElement(By.name("isbn"));
        element.sendKeys("315-6616-156");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi book tallennetaan', {
        driver.getPageSource().contains("New book created").shouldBe true
    }



}