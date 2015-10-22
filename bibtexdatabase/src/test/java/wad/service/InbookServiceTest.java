
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
    
    private Inbook inbook1;
    private Inbook inbook2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        inbook1 = new Inbook();
        inbook1.setCitation("citation");
        inbook1.setAuthor("author1");
        inbook1.setTitle("otsikko1");
        inbook1.setPublisher("koulu1");
        inbook1.setYear(2001);
        inbook1.setPages(1);
        inbook2 = new Inbook();
        inbook2.setCitation("citation");
        inbook2.setAuthor("author2");
        inbook2.setTitle("otsikko2");
        inbook2.setPublisher("koulu2");
        inbook2.setYear(2002);
        inbook2.setPages(1);
    }
    
    @Test
    public void testAddBook() {
        service.addInbook(inbook1);
        Inbook retrieved = repository.findOne(inbook1.getId());
        assertEquals(retrieved.getId(), inbook1.getId());
        assertEquals(retrieved.getAuthor(), inbook1.getAuthor());
        assertEquals(retrieved.getTitle(), inbook1.getTitle());
    }
    
    @Test
    public void testDeleteBook() {
        repository.save(inbook1);
        Long id = inbook1.getId();
        service.deleteInbook(inbook1.getId());
        Inbook eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetTechreport() {
        repository.save(inbook1);
        Inbook retrieved = service.getInbook(inbook1.getId());
        assertEquals(retrieved.getId(), inbook1.getId());
        assertEquals(retrieved.getAuthor(), inbook1.getAuthor());
        assertEquals(retrieved.getTitle(), inbook1.getTitle());
    }
    
    @Test
    public void testListBook() {
        repository.save(inbook1);
        repository.save(inbook2);
        List<Inbook> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Inbook m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(inbook1.getTitle()));
        assertTrue(titles.contains(inbook2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(inbook1);
        repository.save(inbook2);
        List<Inbook> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), inbook1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(inbook1);
        repository.save(inbook2);
        List<Inbook> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), inbook1.getAuthor());
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(inbook1);
        repository.save(inbook2);
        List<Inbook> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(inbook1);
        repository.save(inbook2);
        List<Inbook> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(inbook1);
        String bibtex = service.getBibtex(inbook1.getId());
        assertTrue(bibtex.contains("@Inbook"));
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
