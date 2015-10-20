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
        element.sendKeys("cite");
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

    when 'käyttäjä on siirtynyt bibtex sivulle', {}

    then 'uusi inproceedings tallennetaan', {
        driver.getPageSource().contains("@Inproceedings").shouldBe true
    }
}


