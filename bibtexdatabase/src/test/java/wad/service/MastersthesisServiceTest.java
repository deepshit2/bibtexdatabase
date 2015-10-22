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

    private Mastersthesis masters1;
    private Mastersthesis masters2;

    @Before
    public void setUp() {
        repository.deleteAll();
        masters1 = new Mastersthesis();
        masters1.setCitation("cite");
        masters1.setAuthor("author1");
        masters1.setTitle("otsikko1");
        masters1.setSchool("koulu1");
        masters1.setYear(2001);
        masters2 = new Mastersthesis();
        masters2.setCitation("cite");
        masters2.setAuthor("author2");
        masters2.setTitle("otsikko2");
        masters2.setSchool("koulu2");
        masters2.setYear(2002);
    }

    @Test
    public void testAddThesis() {
        service.addMastersthesis(masters1);
        Mastersthesis retrieved = repository.findOne(masters1.getId());
        assertEquals(retrieved.getId(), masters1.getId());
        assertEquals(retrieved.getAuthor(), masters1.getAuthor());
        assertEquals(retrieved.getTitle(), masters1.getTitle());
    }

    @Test
    public void testDeleteThesis() {
        repository.save(masters1);
        Long id = masters1.getId();
        service.deleteMastersthesis(masters1.getId());
        Mastersthesis eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }

    @Test
    public void testGetThesis() {
        repository.save(masters1);
        Mastersthesis retrieved = service.getMastersthesis(masters1.getId());
        assertEquals(retrieved.getId(), masters1.getId());
        assertEquals(retrieved.getAuthor(), masters1.getAuthor());
        assertEquals(retrieved.getTitle(), masters1.getTitle());
    }

    @Test
    public void testListArticles() {
        repository.save(masters1);
        repository.save(masters2);
        List<Mastersthesis> kaikki = service.list();
        List<String> titles = new ArrayList();
        for (Mastersthesis m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(masters1.getTitle()));
        assertTrue(titles.contains(masters2.getTitle()));
    }

    @Test
    public void testFindByAuthor() {
        repository.save(masters1);
        repository.save(masters2);
        List<Mastersthesis> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), masters1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(masters1);
        repository.save(masters2);
        List<Mastersthesis> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), masters1.getAuthor());
    }

    @Test
    public void testFindBySchool() {
        repository.save(masters1);
        repository.save(masters2);
        List<Mastersthesis> boobs = service.search("oulu2");
        assertTrue(boobs.size() == 1);
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(masters1);
        repository.save(masters2);
        List<Mastersthesis> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(masters1);
        repository.save(masters2);
        List<Mastersthesis> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(masters1);
        String bibtex = service.getBibtex(masters1.getId());
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
