
package wad.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class IncollectionTest {
    
    @Test
    public void incollectionSetteritGetteritTestiNostattaaRivikattavuutta() {

        Incollection i = new Incollection();
        i.setCitation("seksiwau");
        i.setAuthor("tekija");
        i.setTitle("otsikko");
        i.setBooktitle("toinenOtsikko");
        i.setEditor("editoija");
        
        i.setPublisher("julkaisija");
        i.setAddress("osoite");
        i.setNote("jeeee");
        i.setKey("avain");
        
        i.setPages(666);
        i.setYear(2000);
        i.setMonth(2);
        i.setVolume(2);
        i.setSeries(1);
        i.setType("tyyppi");
        i.setChapter(1);
        i.setEdition(2);
        
        assertTrue(i.getEdition() == 2);
        assertTrue(i.getChapter() == 1);
        assertEquals("tyyppi", i.getType());
        assertEquals("tekija", i.getAuthor());
        assertEquals("otsikko", i.getTitle());
        assertEquals("toinenOtsikko", i.getBooktitle());
        assertEquals("editoija", i.getEditor());
        
        assertEquals("julkaisija", i.getPublisher());
        assertEquals("osoite", i.getAddress());
        assertEquals("jeeee", i.getNote());
        assertEquals("avain", i.getKey());
        assertEquals("seksiwau", i.getCitation());
        
        assertTrue(i.getPages() == 666);
        assertTrue(i.getYear() == 2000);
        assertTrue(i.getMonth() == 2);
        assertTrue(i.getVolume() == 2);
        assertTrue(i.getSeries() == 1);
        
    }

}
