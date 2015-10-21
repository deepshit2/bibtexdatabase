
package wad.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ManualTest {
    
    @Test
    public void createManual() {
        Manual book = new Manual();

        //Setterit
        book.setCitation("citation");
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setAddress("Osoite");
        book.setOrganization("org");
        book.setEdition(1);
        book.setMonth(1);
        book.setNote("Note");
        book.setYear(2015);
        book.setKey("avain");
        //Getterit
        
        assertEquals("Osoite", book.getAddress());
        assertEquals("Author", book.getAuthor());
        assertEquals("org",book.getOrganization());
        assertEquals("Note", book.getNote());
        assertEquals("avain", book.getKey());
        assertEquals("Title", book.getTitle());
        assertEquals(1, (int) book.getEdition());
        assertEquals(1, (int) book.getMonth());
        assertEquals(2015, (int) book.getYear());
    }
    
    @Test
    public void testTagsEmpty() {
        Manual book = new Manual();
        assertTrue(book.getTags().isEmpty());
    }
    
}
