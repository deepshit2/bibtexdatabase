import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä article-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä articlen kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new article"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
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

    then 'uusi article tallennetaan', {
        driver.getPageSource().contains("New article created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä articlea epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new article"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("volume"));
        element.sendKeys("3");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta articlea ei tallenneta', {
        driver.getPageSource().contains("New article created").shouldBe false
    }

}
