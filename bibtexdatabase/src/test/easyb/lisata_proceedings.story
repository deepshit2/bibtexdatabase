import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä proceedings-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä proceedingsin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new proceedings"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("PB1");
        element = driver.findElement(By.name("title"));
        element.sendKeys("End of this");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("Kumpula publishing");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2013");
        element = driver.findElement(By.name("volume"));
        element.sendKeys("2");
        element = driver.findElement(By.name("series"));
        element.sendKeys("11");
        element = driver.findElement(By.name("address"));
        element.sendKeys("Gumproad 120");
        element = driver.findElement(By.name("editor"));
        element.sendKeys("3");
        element = driver.findElement(By.name("month"));
        element.sendKeys("3");
        element = driver.findElement(By.name("note"));
        element.sendKeys("note");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi proceedings tallennetaan', {
        driver.getPageSource().contains("New proceeding created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä proceedingsia epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new proceedings"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta proceedingsia ei tallenneta', {
        driver.getPageSource().contains("New proceeding created").shouldBe false
    }

}

