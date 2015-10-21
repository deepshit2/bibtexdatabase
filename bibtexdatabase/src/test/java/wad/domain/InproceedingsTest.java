
package wad.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class InproceedingsTest {
    
    @Test
    public void inproceedingsSetteritGetteritTestiNostattaaRivikattavuutta() {

        Inproceedings i = new Inproceedings();
        i.setCitation("seksiwau");
        i.setAuthor("tekija");
        i.setTitle("otsikko");
        i.setBooktitle("toinenOtsikko");
        i.setEditor("editoija");
        i.setOrganization("organisaatio");
        i.setPublisher("julkaisija");
        i.setAddress("osoite");
        i.setNote("jeeee");
        i.setKey("avain");
        
        i.setPages(666);
        i.setYear(2000);
        i.setMonth(2);
        i.setVolume(2);
        i.setSeries(1);
        
        assertEquals("tekija", i.getAuthor());
        assertEquals("otsikko", i.getTitle());
        assertEquals("toinenOtsikko", i.getBooktitle());
        assertEquals("editoija", i.getEditor());
        assertEquals("organisaatio", i.getOrganization());
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
    
    @Test
    public void testTagsEmpty() {
        Inproceedings i = new Inproceedings();
        assertTrue(i.getTags().isEmpty());
    }

}
