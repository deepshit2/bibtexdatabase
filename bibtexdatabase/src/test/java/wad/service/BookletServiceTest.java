
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
import wad.domain.Booklet;
import wad.repository.BookletRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookletServiceTest {
    
    @Autowired
    private BookletService service;
    
    @Autowired
    private BookletRepository repository;
    
    private Booklet booklet1;
    private Booklet booklet2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        booklet1 = new Booklet();
        booklet1.setCitation("citation");
        booklet1.setAuthor("author1");
        booklet1.setTitle("otsikko1");
        booklet1.setYear(2001);
        booklet2 = new Booklet();
        booklet2.setCitation("citation");
        booklet2.setAuthor("author2");
        booklet2.setTitle("otsikko2");
        booklet2.setYear(2002);
    }
    
    @Test
    public void testAddBooklet() {
        service.addBooklet(booklet1);
        Booklet retrieved = repository.findOne(booklet1.getId());
        assertEquals(retrieved.getId(), booklet1.getId());
        assertEquals(retrieved.getAuthor(), booklet1.getAuthor());
        assertEquals(retrieved.getTitle(), booklet1.getTitle());
    }
    
    @Test
    public void testDeleteBooklet() {
        repository.save(booklet1);
        Long id = booklet1.getId();
        service.deleteBooklet(booklet1.getId());
        Booklet eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetTechreport() {
        repository.save(booklet1);
        Booklet retrieved = service.getBooklet(booklet1.getId());
        assertEquals(retrieved.getId(), booklet1.getId());
        assertEquals(retrieved.getAuthor(), booklet1.getAuthor());
        assertEquals(retrieved.getTitle(), booklet1.getTitle());
    }
    
    @Test
    public void testListBooklet() {
        repository.save(booklet1);
        repository.save(booklet2);
        List<Booklet> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Booklet m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(booklet1.getTitle()));
        assertTrue(titles.contains(booklet2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(booklet1);
        repository.save(booklet2);
        List<Booklet> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), booklet1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(booklet1);
        repository.save(booklet2);
        List<Booklet> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), booklet1.getAuthor());
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(booklet1);
        repository.save(booklet2);
        List<Booklet> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(booklet1);
        repository.save(booklet2);
        List<Booklet> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(booklet1);
        String bibtex = service.getBibtex(booklet1.getId());
        assertTrue(bibtex.contains("@Booklet"));
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
