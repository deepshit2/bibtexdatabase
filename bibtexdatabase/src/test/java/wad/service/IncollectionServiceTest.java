
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
    
    private Incollection i1;
    private Incollection i2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        i1 = new Incollection();
        i1.setCitation("cite");
        i1.setAuthor("author1");
        i1.setTitle("otsikko1");
        i1.setBooktitle("koulu1");
        i1.setYear(2001);
        i1.setPublisher("aaa1");
        i2 = new Incollection();
        i2.setCitation("cite2");
        i2.setPublisher("aaa2");
        i2.setAuthor("author2");
        i2.setTitle("otsikko2");
        i2.setBooktitle("koulu2");
        i2.setYear(2002);
    }
    
    @Test
    public void testAddIncollection() {
        service.addIncollection(i1);
        Incollection retrieved = repository.findOne(i1.getId());
        assertEquals(retrieved.getId(), i1.getId());
        assertEquals(retrieved.getAuthor(), i1.getAuthor());
        assertEquals(retrieved.getTitle(), i1.getTitle());
    }
    
    @Test
    public void testDeleteIncollection() {
        repository.save(i1);
        Long id = i1.getId();
        service.deleteIncollection(i1.getId());
        Incollection eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetIncollection() {
        repository.save(i1);
        Incollection retrieved = service.getIncollection(i1.getId());
        assertEquals(retrieved.getId(), i1.getId());
        assertEquals(retrieved.getAuthor(), i1.getAuthor());
        assertEquals(retrieved.getTitle(), i1.getTitle());
    }
    
    @Test
    public void testListIncollection() {
        repository.save(i1);
        repository.save(i2);
        List<Incollection> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Incollection m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(i1.getTitle()));
        assertTrue(titles.contains(i2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(i1);
        repository.save(i2);
        List<Incollection> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), i1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(i1);
        repository.save(i2);
        List<Incollection> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), i1.getAuthor());
    }

    @Test
    public void testFindByBooktitle() {
        repository.save(i1);
        repository.save(i2);
        List<Incollection> boobs = service.search("oulu2");
        assertTrue(boobs.size() == 1);
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(i1);
        repository.save(i2);
        List<Incollection> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(i1);
        repository.save(i2);
        List<Incollection> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(i1);
        String bibtex = service.getBibtex(i1.getId());
        assertTrue(bibtex.contains("@Incollection"));
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
