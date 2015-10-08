
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
import wad.domain.Inbook;
import wad.repository.InbookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class InbookServiceTest {
    
    @Autowired
    private InbookService service;
    
    @Autowired
    private InbookRepository repository;
    
    private Inbook m1;
    private Inbook m2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        m1 = new Inbook();
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setPublisher("koulu1");
        m1.setYear(2001);
        m1.setPages(1);
        m2 = new Inbook();
        m2.setAuthor("author2");
        m2.setTitle("otsikko2");
        m2.setPublisher("koulu2");
        m2.setYear(2002);
        m2.setPages(1);
    }
    
    @Test
    public void testAddBook() {
        service.addInbook(m1);
        Inbook retrieved = repository.findOne(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testDeleteBook() {
        repository.save(m1);
        Long id = m1.getId();
        service.deleteInbook(m1.getId());
        Inbook eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetTechreport() {
        repository.save(m1);
        Inbook retrieved = service.getInbook(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testListBook() {
        repository.save(m1);
        repository.save(m2);
        List<Inbook> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Inbook m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(m1);
        repository.save(m2);
        List<Inbook> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(m1);
        repository.save(m2);
        List<Inbook> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(m1);
        repository.save(m2);
        List<Inbook> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(m1);
        repository.save(m2);
        List<Inbook> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    /*
    @Test
    public void testGetBibtex() {
        repository.save(m1);
        String bibtex = service.getBibtex(m1.getId());
        assertTrue(bibtex.contains("@Book"));
        assertTrue(bibtex.contains("{"));
        assertTrue(bibtex.contains("}"));
        assertTrue(bibtex.contains(","));
        assertTrue(bibtex.contains("author1"));
    }
    */
    /*
    @Test
    public void testGetNoBibtex() {
        String bibtex = "";
        try {
            bibtex = service.getBibtex(9999999L);
        } catch(Exception e) {
            
        }
        assertTrue(bibtex.isEmpty());
    }
    */
    
}
