package wad.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BookTest {

    @Test
    public void createBook() {
        Book book = new Book();

        //Setterit
        book.setAddress("Osoite");
        book.setAuthor("Author");
        book.setEdition(1);
        book.setIsbn("123456");
        book.setMonth(1);
        book.setNote("Note");
        book.setPublisher("Publisher");
        book.setSeries(1);
        book.setTitle("Title");
        book.setVolume(1);
        book.setYear(2015);

        //Getterit
        assertEquals("Osoite", book.getAddress());
        assertEquals("Author", book.getAuthor());
        assertEquals("123456", book.getIsbn());
        assertEquals("Note", book.getNote());
        assertEquals("Title", book.getTitle());
        assertEquals(1, (int) book.getEdition());
        assertEquals(1, (int) book.getMonth());
        assertEquals(1, (int) book.getSeries());
        assertEquals(1, (int) book.getVolume());
        assertEquals(2015, (int) book.getYear());
    }
}
