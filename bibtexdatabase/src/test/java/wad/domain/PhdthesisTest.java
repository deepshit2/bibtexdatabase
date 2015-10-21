
package wad.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PhdthesisTest {
    
    @Test
    public void phdthesisSetteritGetteritTestiNostattaaRivikattavuutta() {
        
        Phdthesis thesis = new Phdthesis();
        
        thesis.setCitation("cite");
        thesis.setAuthor("opiskelija");
        thesis.setTitle("otsikko");
        thesis.setSchool("koulu");
        thesis.setType("tyyppi");
        thesis.setAddress("osoite");
        thesis.setNote("joooo");
        thesis.setKey("avain");
        
        thesis.setYear(2015);
        thesis.setMonth(1);
        
        assertEquals("cite", thesis.getCitation());
        assertEquals("opiskelija", thesis.getAuthor());
        assertEquals("otsikko", thesis.getTitle());
        assertEquals("koulu", thesis.getSchool());
        assertEquals("tyyppi", thesis.getType());
        assertEquals("osoite", thesis.getAddress());
        assertEquals("joooo", thesis.getNote());
        assertEquals("avain", thesis.getKey());
        
        assertTrue(thesis.getYear() == 2015);
        assertTrue(thesis.getMonth() == 1);
        
    }
    
    @Test
    public void testTagsEmpty() {
        Phdthesis thesis = new Phdthesis();
        assertTrue(thesis.getTags().isEmpty());
    }
    
}
