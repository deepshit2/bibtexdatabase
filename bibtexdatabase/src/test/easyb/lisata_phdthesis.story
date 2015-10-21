import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan lisätä phdthesis-viitteitä järjestelmään lomakkeella webbisivun kautta.'

scenario "käyttäjä voi lisätä phdthesisin kunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new phdthesis"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt kunnolliset syötteet', {
        element = driver.findElement(By.name("citation"));
        element.sendKeys("WW1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Will Williams");
        element = driver.findElement(By.name("title"));
        element.sendKeys("GetLucky");
        element = driver.findElement(By.name("school"));
        element.sendKeys("School of Luck");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2014");
        element = driver.findElement(By.name("address"));
        element.sendKeys("Street 12");
        element = driver.findElement(By.name("month"));
        element.sendKeys("12");
        element = driver.findElement(By.name("note"));
        element.sendKeys("no note");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uusi phdthesis tallennetaan', {
        driver.getPageSource().contains("New phdthesis created").shouldBe true
    }
}

scenario "käyttäjä ei voi lisätä phdthesisia epäkunnollisilla syötteillä", {
    given 'käyttäjä on lomakesivulla', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new phdthesis"));       
        element.click();
    }

    when 'käyttäjä on syöttänyt epäkunnolliset syötteet', {
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    then 'uutta phdthesisia ei tallenneta', {
        driver.getPageSource().contains("New phdthesis created").shouldBe false
    }

}

