import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä inproceedings-viitteitä järjestelmään lomakkeella webbisivun kautta'

scenario "käyttäjä voi lisätä inproceedingsin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new inproceedings"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("kirjoittaja");
        element = driver.findElement(By.name("title"));
        element.sendKeys("kirjan otsikko");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("julkaisija");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("julkaisija2");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi inproceedings tallennetaan', {
        driver.getPageSource().contains("New inproceeding").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä inproceedingsin epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new inproceedings"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta inproceedingsia ei tallenneta', {
        driver.getPageSource().contains("New inproceeding").shouldBe false
    }
}
