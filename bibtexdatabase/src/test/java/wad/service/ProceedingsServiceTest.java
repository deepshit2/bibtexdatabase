
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
    
    private Proceedings proceedings1;
    private Proceedings proceedings2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        proceedings1 = new Proceedings();
        proceedings1.setCitation("cite");
        proceedings1.setTitle("otsikko1");
        proceedings1.setEditor("edit1");
        proceedings1.setYear(2001);
        proceedings2 = new Proceedings();
        proceedings2.setCitation("cite2");
        proceedings2.setTitle("otsikko2");
        proceedings2.setYear(2002);
        proceedings2.setEditor("edit2");
    }
    
    @Test
    public void testAddProceedings() {
        service.addProceedings(proceedings1);
        Proceedings retrieved = repository.findOne(proceedings1.getId());
        assertEquals(retrieved.getId(), proceedings1.getId());
        assertEquals(retrieved.getTitle(), proceedings1.getTitle());
    }
    
    @Test
    public void testDeleteProceedings() {
        repository.save(proceedings1);
        Long id = proceedings1.getId();
        service.deleteProceedings(proceedings1.getId());
        Proceedings eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetProceedings() {
        repository.save(proceedings1);
        Proceedings retrieved = service.getProceedings(proceedings1.getId());
        assertEquals(retrieved.getId(), proceedings1.getId());
        assertEquals(retrieved.getTitle(), proceedings1.getTitle());
    }
    
    @Test
    public void testListProceedings() {
        repository.save(proceedings1);
        repository.save(proceedings2);
        List<Proceedings> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Proceedings m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(proceedings1.getTitle()));
        assertTrue(titles.contains(proceedings2.getTitle()));
    }
    
    @Test
    public void testFindByEditor() {
        repository.save(proceedings1);
        repository.save(proceedings2);
        List<Proceedings> articles = service.search("edit1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getEditor(), proceedings1.getEditor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(proceedings1);
        repository.save(proceedings2);
        List<Proceedings> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getTitle(), proceedings1.getTitle());
    }
   

    @Test
    public void testSearchCanFindAll() {
        repository.save(proceedings1);
        repository.save(proceedings2);
        List<Proceedings> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(proceedings1);
        repository.save(proceedings2);
        List<Proceedings> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(proceedings1);
        String bibtex = service.getBibtex(proceedings1.getId());
        assertTrue(bibtex.contains("@Proceedings"));
        assertTrue(bibtex.contains("{"));
        assertTrue(bibtex.contains("}"));
        assertTrue(bibtex.contains(","));
        assertTrue(bibtex.contains("edit"));
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
