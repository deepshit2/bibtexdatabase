import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä inbook-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä inbookin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new inbook"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("EE1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Esther Eston");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Badcode for dummies");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("123");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("For dummies Inc.");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1984");
        element = driver.findElement(By.name("address"));
        element.sendKeys("Dummystreet 50, NY");
        element = driver.findElement(By.name("month"));
        element.sendKeys("11");
        element = driver.findElement(By.name("note"));
        element.sendKeys("notes for readers");
        element = driver.findElement(By.name("volume"));
        element.sendKeys("1");
        element = driver.findElement(By.name("series"));
        element.sendKeys("3");
        element = driver.findElement(By.name("edition"));
        element.sendKeys("2");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi inbook tallennetaan', {
        driver.getPageSource().contains("New inbook created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä inbookkia epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new inbook"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta inbookkia ei tallenneta', {
        driver.getPageSource().contains("New inbook created").shouldBe false
    }

}

