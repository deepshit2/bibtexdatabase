import wad.*
import wad.controller.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Käyttäjänä haluan book-viitteen bibtex-muodossa'

scenario "luon viitteen ja saan halutessa dokumentit bibtex-muodossa", {
    given 'book on luotu', {
        driver = new HtmlUnitDriver();
        driver.get("https://bibtexdatabase.herokuapp.com/");
        element = driver.findElement(By.linkText("Add new book"));       
        element.click();
        element = driver.findElement(By.name("citation"));
        element.sendKeys("GW1");
        element = driver.findElement(By.name("author"));
        element.sendKeys("George Washingston");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Freedom or Not");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("US authors");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1899");
        element = driver.findElement(By.name("volume"));
        element.sendKeys("1");
        element = driver.findElement(By.name("series"));
        element.sendKeys("3");
        element = driver.findElement(By.name("address"));
        element.sendKeys("No address");
        element = driver.findElement(By.name("edition"));
        element.sendKeys("5");
        element = driver.findElement(By.name("month"));
        element.sendKeys("1");
        element = driver.findElement(By.name("note"));
        element.sendKeys("free");
        element = driver.findElement(By.name("isbn"));
        element.sendKeys("315-6216-156");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    when 'käyttäjä on siirtynyt bibtex sivulle', {}

    then 'uusi book-bibtex näytetään', {
        driver.getPageSource().contains("@Book").shouldBe true
    }
}



