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
import wad.domain.Phdthesis;
import wad.repository.PhdthesisRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PhdthesisServiceTest {

    @Autowired
    private PhdthesisService service;

    @Autowired
    private PhdthesisRepository repository;

    private Phdthesis phd1;
    private Phdthesis phd2;

    @Before
    public void setUp() {
        repository.deleteAll();
        phd1 = new Phdthesis();
        phd1.setCitation("cite");
        phd1.setAuthor("author1");
        phd1.setTitle("otsikko1");
        phd1.setSchool("koulu1");
        phd1.setYear(2001);
        phd2 = new Phdthesis();
        phd2.setCitation("cite");
        phd2.setAuthor("author2");
        phd2.setTitle("otsikko2");
        phd2.setSchool("koulu2");
        phd2.setYear(2002);
    }

    @Test
    public void testAddThesis() {
        service.addPhdthesis(phd1);
        Phdthesis retrieved = repository.findOne(phd1.getId());
        assertEquals(retrieved.getId(), phd1.getId());
        assertEquals(retrieved.getAuthor(), phd1.getAuthor());
        assertEquals(retrieved.getTitle(), phd1.getTitle());
    }

    @Test
    public void testDeleteThesis() {
        repository.save(phd1);
        Long id = phd1.getId();
        service.deletePhdthesis(phd1.getId());
        Phdthesis eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }

    @Test
    public void testGetThesis() {
        repository.save(phd1);
        Phdthesis retrieved = service.getPhdthesis(phd1.getId());
        assertEquals(retrieved.getId(), phd1.getId());
        assertEquals(retrieved.getAuthor(), phd1.getAuthor());
        assertEquals(retrieved.getTitle(), phd1.getTitle());
    }

    @Test
    public void testListArticles() {
        repository.save(phd1);
        repository.save(phd2);
        List<Phdthesis> kaikki = service.list();
        List<String> titles = new ArrayList();
        for (Phdthesis m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(phd1.getTitle()));
        assertTrue(titles.contains(phd2.getTitle()));
    }

    @Test
    public void testFindByAuthor() {
        repository.save(phd1);
        repository.save(phd2);
        List<Phdthesis> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), phd1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(phd1);
        repository.save(phd2);
        List<Phdthesis> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), phd1.getAuthor());
    }

    @Test
    public void testFindBySchool() {
        repository.save(phd1);
        repository.save(phd2);
        List<Phdthesis> boobs = service.search("oulu2");
        assertTrue(boobs.size() == 1);
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(phd1);
        repository.save(phd2);
        List<Phdthesis> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(phd1);
        repository.save(phd2);
        List<Phdthesis> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }

    @Test
    public void testGetBibtex() {
        repository.save(phd1);
        String bibtex = service.getBibtex(phd1.getId());
        assertTrue(bibtex.contains("@Phdthesis"));
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
