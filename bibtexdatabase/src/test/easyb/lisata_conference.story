import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä conference-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä conferencen kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new conference"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("CD1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Carl Draper");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Lemon or grape");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("Lime suits better");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2014");
        element = driver.findElement(By.name("volume"));
        element.sendKeys("1");
        element = driver.findElement(By.name("series"));
        element.sendKeys("2");
        element = driver.findElement(By.name("address"));
        element.sendKeys("Valleygarden 91");
        element = driver.findElement(By.name("organization"));
        element.sendKeys("Lime Inc.");
        element = driver.findElement(By.name("editor"));
        element.sendKeys("Jenn Draper");
        element = driver.findElement(By.name("month"));
        element.sendKeys("8");
        element = driver.findElement(By.name("note"));
        element.sendKeys("note to readers");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi conference tallennetaan', {
        driver.getPageSource().contains("New conference created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä conferencea epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new conference"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta conferencea ei tallenneta', {
        driver.getPageSource().contains("New conference created").shouldBe false
    }

}

