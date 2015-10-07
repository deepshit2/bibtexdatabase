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
        element.sendKeys("kirja");
        element = driver.findElement(By.name("author"));
        element.sendKeys("kirjoittaja");
        element = driver.findElement(By.name("title"));
        element.sendKeys("kirjan otsikko");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("julkaisija");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("volume"));
        element.sendKeys("1");
        element = driver.findElement(By.name("series"));
        element.sendKeys("2");
        element = driver.findElement(By.name("address"));
        element.sendKeys("osoite 10");
        element = driver.findElement(By.name("edition"));
        element.sendKeys("3");
        element = driver.findElement(By.name("month"));
        element.sendKeys("12");
        element = driver.findElement(By.name("note"));
        element.sendKeys("joooo");
        element = driver.findElement(By.name("isbn"));
        element.sendKeys("315-6616-156");
        element = driver.findElement(By.name("submit"));
        element.submit();
    }

    when 'käyttäjä klikkaa bibtex-linkkiä', {
        element = driver.findElement(By.linkText("BibTeX"));       
        element.click();
    }

    then 'uusi book-bibtex näytetään', {
        driver.getPageSource().contains("@Book").shouldBe true
    }
}



