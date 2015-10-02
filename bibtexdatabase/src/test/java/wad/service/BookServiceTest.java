
package wad.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wad.Application;
import wad.domain.Book;
import wad.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookServiceTest {
    
    @Autowired
    private BookService service;
    
    @Autowired
    private BookRepository repository;
    
    private Book m1;
    private Book m2;
    
    @Before
    public void setUp() {
        m1 = new Book();
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setPublisher("koulu1");
        m1.setYear(2001);
        m2 = new Book();
        m2.setAuthor("author2");
        m2.setTitle("otsikko2");
        m2.setPublisher("koulu2");
        m2.setYear(2002);
    }
    
    @Test
    public void testAddBook() {
        service.addBook(m1);
        Book retrieved = repository.findOne(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testDeleteBook() {
        repository.save(m1);
        Long id = m1.getId();
        service.deleteBook(m1.getId());
        Book eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetTechreport() {
        repository.save(m1);
        Book retrieved = service.getBook(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testListBook() {
        repository.save(m1);
        repository.save(m2);
        List<Book> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Book m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }
    
}
