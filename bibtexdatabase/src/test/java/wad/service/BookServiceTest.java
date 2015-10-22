
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
    
    private Book book1;
    private Book book2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        book1 = new Book();
        book1.setCitation("kirja");
        book1.setAuthor("author1");
        book1.setTitle("otsikko1");
        book1.setPublisher("koulu1");
        book1.setYear(2001);
        book2 = new Book();
        book2.setCitation("kirja");
        book2.setAuthor("author2");
        book2.setTitle("otsikko2");
        book2.setPublisher("koulu2");
        book2.setYear(2002);
    }
    
    @Test
    public void testAddBook() {
        service.addBook(book1);
        Book retrieved = repository.findOne(book1.getId());
        assertEquals(retrieved.getId(), book1.getId());
        assertEquals(retrieved.getAuthor(), book1.getAuthor());
        assertEquals(retrieved.getTitle(), book1.getTitle());
    }
    
    @Test
    public void testDeleteBook() {
        repository.save(book1);
        Long id = book1.getId();
        service.deleteBook(book1.getId());
        Book eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetTechreport() {
        repository.save(book1);
        Book retrieved = service.getBook(book1.getId());
        assertEquals(retrieved.getId(), book1.getId());
        assertEquals(retrieved.getAuthor(), book1.getAuthor());
        assertEquals(retrieved.getTitle(), book1.getTitle());
    }
    
    @Test
    public void testListBook() {
        repository.save(book1);
        repository.save(book2);
        List<Book> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Book m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(book1.getTitle()));
        assertTrue(titles.contains(book2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(book1);
        repository.save(book2);
        List<Book> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), book1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(book1);
        repository.save(book2);
        List<Book> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), book1.getAuthor());
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(book1);
        repository.save(book2);
        List<Book> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(book1);
        repository.save(book2);
        List<Book> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(book1);
        String bibtex = service.getBibtex(book1.getId());
        assertTrue(bibtex.contains("@Book"));
        assertTrue(bibtex.contains("{"));
        assertTrue(bibtex.contains("}"));
        assertTrue(bibtex.contains(","));
        assertTrue(bibtex.contains("author1"));
    }
    
    @Test
    public void testGetNoBibtex() {
        String bibtex = "";
        try {
            bibtex = service.getBibtex(9999999L);
        } catch(Exception e) {
            
        }
        assertTrue(bibtex.isEmpty());
    }
    
}
