import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan inproceedings-viitteen bibtex-muodossa'

scenario "luon viitteen ja saan halutessa dokumentit bibtex-muodossa", {
    given 'inproceeding on luotu', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new inproceedings"));       
        element.click();
        element = driver.findElement(By.name("citation"));
        element.sendKeys("ML1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Matt Luuks");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Best Course Ever");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("Courses Ever");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("Not known");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2015");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    when 'käyttäjä on siirtynyt bibtex sivulle', {}

    then 'uusi inproceedings tallennetaan', {
        driver.getPageSource().contains("@Inproceedings").shouldBe true
    }
}


