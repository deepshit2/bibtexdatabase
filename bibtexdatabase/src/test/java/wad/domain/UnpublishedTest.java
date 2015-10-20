
package wad.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UnpublishedTest {
    
    @Test
    public void createUnpublished() {
        Unpublished book = new Unpublished();

        //Setterit
        book.setCitation("citation");
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setMonth(1);
        book.setNote("Note");
        book.setYear(2015);
        book.setKey("avain");
        //Getterit
                
        assertEquals("Author", book.getAuthor());
        assertEquals("Note", book.getNote());
        assertEquals("avain", book.getKey());
        assertEquals("Title", book.getTitle());
        assertEquals(1, (int) book.getMonth());
        assertEquals(2015, (int) book.getYear());
        
        
    }
    
}
