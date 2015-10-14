
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
import wad.domain.Inproceedings;
import wad.domain.Proceedings;
import wad.repository.ProceedingsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProceedingsServiceTest {

    @Autowired
    private ProceedingsService service;
    
    @Autowired
    private ProceedingsRepository repository;
    
    private Proceedings m1;
    private Proceedings m2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        m1 = new Proceedings();
        m1.setCitation("cite");
        m1.setTitle("otsikko1");
        m1.setEditor("edit1");
        m1.setYear(2001);
        m2 = new Proceedings();
        m2.setCitation("cite2");
        m2.setTitle("otsikko2");
        m2.setYear(2002);
        m2.setEditor("edit2");
    }
    
    @Test
    public void testAddProceedings() {
        service.addProceedings(m1);
        Proceedings retrieved = repository.findOne(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testDeleteProceedings() {
        repository.save(m1);
        Long id = m1.getId();
        service.deleteProceedings(m1.getId());
        Proceedings eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetProceedings() {
        repository.save(m1);
        Proceedings retrieved = service.getProceedings(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testListProceedings() {
        repository.save(m1);
        repository.save(m2);
        List<Proceedings> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Proceedings m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }
    
    @Test
    public void testFindByEditor() {
        repository.save(m1);
        repository.save(m2);
        List<Proceedings> articles = service.search("edit1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getEditor(), m1.getEditor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(m1);
        repository.save(m2);
        List<Proceedings> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getTitle(), m1.getTitle());
    }
   

    @Test
    public void testSearchCanFindAll() {
        repository.save(m1);
        repository.save(m2);
        List<Proceedings> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(m1);
        repository.save(m2);
        List<Proceedings> boobs = service.search("batman134134");
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