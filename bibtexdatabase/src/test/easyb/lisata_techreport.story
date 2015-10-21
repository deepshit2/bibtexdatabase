import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä techreport-viitteitä järjestelmään lomakkeella webbisivun kautta'

scenario "käyttäjä voi lisätä techreportit kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new techreport"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("MM1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Marc Marcheford");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ford, Car or Harrison");
        element = driver.findElement(By.name("institution"));
        element.sendKeys("Ford Institute");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1933");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi techreport tallennetaan', {
        driver.getPageSource().contains("New techreport created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä techreporttia epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new techreport"));   
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta techreporttia ei tallenneta', {
        driver.getPageSource().contains("New techreport created").shouldBe false
    }
}
