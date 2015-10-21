
package wad.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TechreportTest {
    
    @Test
    public void techreportSetteritGetteritTestiNostattaaRivikattavuutta() {
        
        Techreport t = new Techreport();
        
        t.setCitation("cite");
        t.setAuthor("tekija");
        t.setTitle("otsikko");
        t.setInstitution("instituutio");
        t.setType("tyyppi");
        t.setAddress("osoite");
        t.setNote("note");
        t.setKey("avain");
        
        t.setMonth(2);
        t.setYear(2001);
        t.setNumber(4);
        
        assertEquals("cite", t.getCitation());
        assertEquals("tekija", t.getAuthor());
        assertEquals("otsikko", t.getTitle());
        assertEquals("instituutio", t.getInstitution());
        assertEquals("tyyppi", t.getType());
        assertEquals("osoite", t.getAddress());
        assertEquals("note", t.getNote());
        assertEquals("avain", t.getKey());
        
        assertTrue(t.getMonth() == 2);
        assertTrue(t.getYear() == 2001);
        assertTrue(t.getNumber() == 4);
    }
    
    @Test
    public void testTagsEmpty() {
        Techreport t = new Techreport();
        assertTrue(t.getTags().isEmpty());
    }
    
            
}
