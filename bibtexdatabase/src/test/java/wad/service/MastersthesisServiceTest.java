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
import wad.domain.Mastersthesis;
import wad.repository.MastersthesisRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MastersthesisServiceTest {

    @Autowired
    private MastersthesisService service;

    @Autowired
    private MastersthesisRepository repository;

    private Mastersthesis m1;
    private Mastersthesis m2;

    @Before
    public void setUp() {
        repository.deleteAll();
        m1 = new Mastersthesis();
        m1.setCitation("cite");
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setSchool("koulu1");
        m1.setYear(2001);
        m2 = new Mastersthesis();
        m2.setCitation("cite");
        m2.setAuthor("author2");
        m2.setTitle("otsikko2");
        m2.setSchool("koulu2");
        m2.setYear(2002);
    }

    @Test
    public void testAddThesis() {
        service.addMastersthesis(m1);
        Mastersthesis retrieved = repository.findOne(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }

    @Test
    public void testDeleteThesis() {
        repository.save(m1);
        Long id = m1.getId();
        service.deleteMastersthesis(m1.getId());
        Mastersthesis eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }

    @Test
    public void testGetThesis() {
        repository.save(m1);
        Mastersthesis retrieved = service.getMastersthesis(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }

    @Test
    public void testListArticles() {
        repository.save(m1);
        repository.save(m2);
        List<Mastersthesis> kaikki = service.list();
        List<String> titles = new ArrayList();
        for (Mastersthesis m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }

    @Test
    public void testFindByAuthor() {
        repository.save(m1);
        repository.save(m2);
        List<Mastersthesis> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(m1);
        repository.save(m2);
        List<Mastersthesis> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testFindBySchool() {
        repository.save(m1);
        repository.save(m2);
        List<Mastersthesis> boobs = service.search("oulu2");
        assertTrue(boobs.size() == 1);
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(m1);
        repository.save(m2);
        List<Mastersthesis> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(m1);
        repository.save(m2);
        List<Mastersthesis> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(m1);
        String bibtex = service.getBibtex(m1.getId());
        assertTrue(bibtex.contains("@Mastersthesis"));
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
