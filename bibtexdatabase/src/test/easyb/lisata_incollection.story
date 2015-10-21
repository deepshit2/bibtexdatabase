import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä incollection-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä incollectionin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new incollection"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("MS1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("My Self");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Me, Myself & I");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("I");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1993");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("Book of Titles");
        element = driver.findElement(By.name("series"));
        element.sendKeys("2");
        element = driver.findElement(By.name("address"));
        element.sendKeys("No address");
        element = driver.findElement(By.name("edition"));
        element.sendKeys("2");
        element = driver.findElement(By.name("month"));
        element.sendKeys("1");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi incollection tallennetaan', {
        driver.getPageSource().contains("New incollection created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä incollectionia epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new incollection"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta incollectionia ei tallenneta', {
        driver.getPageSource().contains("New incollection created").shouldBe false
    }

}

