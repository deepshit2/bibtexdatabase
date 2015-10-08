
package wad.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InbookTest {
    
    @Test
    public void createInBook() {
        Inbook book = new Inbook();

        //Setterit
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setPages(2);
        book.setAddress("Osoite");
        
        book.setEdition(1);
        book.setMonth(1);
        book.setNote("Note");
        book.setPublisher("Publisher");
        book.setSeries(1);
        book.setType("aaa");
        book.setVolume(1);
        book.setYear(2015);
        book.setKey("avain");
        //Getterit
        
        assertEquals("Osoite", book.getAddress());
        assertEquals("Publisher", book.getPublisher());
        assertEquals("Author", book.getAuthor());
        assertEquals("aaa", book.getType());
        assertEquals(2, (int) book.getPages());
        assertEquals("Note", book.getNote());
        assertEquals("avain", book.getKey());
        assertEquals("Title", book.getTitle());
        assertEquals(1, (int) book.getEdition());
        assertEquals(1, (int) book.getMonth());
        assertEquals(1, (int) book.getSeries());
        assertEquals(1, (int) book.getVolume());
        assertEquals(2015, (int) book.getYear());
    }
    
}
