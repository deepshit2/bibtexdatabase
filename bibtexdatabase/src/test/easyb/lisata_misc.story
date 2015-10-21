import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä misc-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä miscin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new misc"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("NN1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Ninety Nine");
        element = driver.findElement(By.name("title"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("howpublished"));
        element.sendKeys("Internet");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("month"));
        element.sendKeys("12");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi misc tallennetaan', {
        driver.getPageSource().contains("New misc created").shouldBe true
    }
}
