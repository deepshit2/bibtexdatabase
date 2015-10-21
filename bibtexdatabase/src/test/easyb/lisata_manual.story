import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä manual-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä manualin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new manual"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("TO1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Therry Organ");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Organize everything");
        element = driver.findElement(By.name("organization"));
        element.sendKeys("Organization.org");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2001");
        element = driver.findElement(By.name("address"));
        element.sendKeys("Road 100, Cali");
        element = driver.findElement(By.name("edition"));
        element.sendKeys("3");
        element = driver.findElement(By.name("month"));
        element.sendKeys("11");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi manual tallennetaan', {
        driver.getPageSource().contains("New manual created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä manualia epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new manual"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta manualia ei tallenneta', {
        driver.getPageSource().contains("New manual created").shouldBe false
    }

}

