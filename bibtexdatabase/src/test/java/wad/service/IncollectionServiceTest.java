
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
import wad.domain.Incollection;
import wad.domain.Inproceedings;
import wad.repository.IncollectionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class IncollectionServiceTest {

    @Autowired
    private IncollectionService service;
    
    @Autowired
    private IncollectionRepository repository;
    
    private Incollection m1;
    private Incollection m2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        m1 = new Incollection();
        m1.setCitation("cite");
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setBooktitle("koulu1");
        m1.setYear(2001);
        m1.setPublisher("aaa1");
        m2 = new Incollection();
        m2.setCitation("cite2");
        m2.setPublisher("aaa2");
        m2.setAuthor("author2");
        m2.setTitle("otsikko2");
        m2.setBooktitle("koulu2");
        m2.setYear(2002);
    }
    
    @Test
    public void testAddIncollection() {
        service.addIncollection(m1);
        Incollection retrieved = repository.findOne(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testDeleteIncollection() {
        repository.save(m1);
        Long id = m1.getId();
        service.deleteIncollection(m1.getId());
        Incollection eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetIncollection() {
        repository.save(m1);
        Incollection retrieved = service.getIncollection(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testListIncollection() {
        repository.save(m1);
        repository.save(m2);
        List<Incollection> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Incollection m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(m1);
        repository.save(m2);
        List<Incollection> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(m1);
        repository.save(m2);
        List<Incollection> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testFindByBooktitle() {
        repository.save(m1);
        repository.save(m2);
        List<Incollection> boobs = service.search("oulu2");
        assertTrue(boobs.size() == 1);
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(m1);
        repository.save(m2);
        List<Incollection> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(m1);
        repository.save(m2);
        List<Incollection> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    /*
    @Test
    public void testGetBibtex() {
        repository.save(m1);
        String bibtex = service.getBibtex(m1.getId());
        assertTrue(bibtex.contains("@Inproceedings"));
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
    */
    
}
