import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä unpublished-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä unpublishedin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new unpublished"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("GT1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("George Turner");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Citing and referencing using Bibtex");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1991");
        element = driver.findElement(By.name("month"));
        element.sendKeys("1");
        element = driver.findElement(By.name("note"));
        element.sendKeys("note to self");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi unpublished tallennetaan', {
        driver.getPageSource().contains("New unpublished created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä unpublishedia epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new unpublished"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta unpublishedia ei tallenneta', {
        driver.getPageSource().contains("New unpublished created").shouldBe false
    }

}
