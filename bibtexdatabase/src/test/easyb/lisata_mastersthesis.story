import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä mastersthesis-viitteitä järjestelmään lomakkeella webbisivun kautta'

scenario "käyttäjä voi lisätä mastersthesis kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new mastersthesis"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("masterblastermastersthesis");
        element = driver.findElement(By.name("author"));
        element.sendKeys("kirjoittaja");
        element = driver.findElement(By.name("title"));
        element.sendKeys("kirjan otsikko");
        element = driver.findElement(By.name("school"));
        element.sendKeys("julkaisija");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi mastersthesis tallennetaan', {
        driver.getPageSource().contains("New mastersthesis created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä mastersthesis epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new mastersthesis"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta mastersthesisia ei tallenneta', {
        driver.getPageSource().contains("New mastersthesis created").shouldBe false
    }
}
