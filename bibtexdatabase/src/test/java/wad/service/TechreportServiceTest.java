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
import wad.domain.Techreport;
import wad.repository.TechreportRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TechreportServiceTest {

    @Autowired
    private TechreportService service;

    @Autowired
    private TechreportRepository repository;

    private Techreport m1;
    private Techreport m2;

    @Before
    public void setUp() {
        repository.deleteAll();
        m1 = new Techreport();
        m1.setCitation("cite");
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setInstitution("koulu1");
        m1.setYear(2001);
        m2 = new Techreport();
        m2.setCitation("cite");
        m2.setAuthor("author2");
        m2.setTitle("otsikko2");
        m2.setInstitution("koulu2");
        m2.setYear(2002);
    }

    @Test
    public void testAddTechreport() {
        service.addTechreport(m1);
        Techreport retrieved = repository.findOne(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }

    @Test
    public void testDeleteTechreport() {
        repository.save(m1);
        Long id = m1.getId();
        service.deleteTechreport(m1.getId());
        Techreport eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }

    @Test
    public void testGetTechreport() {
        repository.save(m1);
        Techreport retrieved = service.getTechreport(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }

    @Test
    public void testListArticles() {
        repository.save(m1);
        repository.save(m2);
        List<Techreport> kaikki = service.list();
        List<String> titles = new ArrayList();
        for (Techreport m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }

    @Test
    public void testFindByAuthor() {
        repository.save(m1);
        repository.save(m2);
        List<Techreport> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(m1);
        repository.save(m2);
        List<Techreport> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), m1.getAuthor());
    }

    @Test
    public void testFindByInstitution() {
        repository.save(m1);
        repository.save(m2);
        List<Techreport> boobs = service.search("oulu2");
        assertTrue(boobs.size() == 1);
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(m1);
        repository.save(m2);
        List<Techreport> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(m1);
        repository.save(m2);
        List<Techreport> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(m1);
        String bibtex = service.getBibtex(m1.getId());
        assertTrue(bibtex.contains("@Techreport"));
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
