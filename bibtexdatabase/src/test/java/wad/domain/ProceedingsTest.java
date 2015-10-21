
package wad.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ProceedingsTest {
    
    @Test
    public void proceedingsSetteritGetteritTestiNostattaaRivikattavuutta() {

        Proceedings i = new Proceedings();
        i.setCitation("seksiwau");
        i.setTitle("otsikko");

        i.setEditor("editoija");
        i.setOrganization("organisaatio");
        i.setPublisher("julkaisija");
        i.setAddress("osoite");
        i.setNote("jeeee");
        i.setKey("avain");
        
        
        i.setYear(2000);
        i.setMonth(2);
        i.setVolume(2);
        i.setSeries(1);


        assertEquals("otsikko", i.getTitle());
        assertEquals("editoija", i.getEditor());
        assertEquals("organisaatio", i.getOrganization());
        assertEquals("julkaisija", i.getPublisher());
        assertEquals("osoite", i.getAddress());
        assertEquals("jeeee", i.getNote());
        assertEquals("avain", i.getKey());
        assertEquals("seksiwau", i.getCitation());
        assertTrue(i.getYear() == 2000);
        assertTrue(i.getMonth() == 2);
        assertTrue(i.getVolume() == 2);
        assertTrue(i.getSeries() == 1);
        
    }
    
    @Test
    public void testTagsEmpty() {
        Proceedings i = new Proceedings();
        assertTrue(i.getTags().isEmpty());
    }

}
