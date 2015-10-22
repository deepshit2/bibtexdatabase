
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
import wad.domain.Inbook;
import wad.domain.Manual;
import wad.repository.ManualRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ManualServiceTest {
    
    @Autowired
    private ManualService service;
    
    @Autowired
    private ManualRepository repository;
    
    private Manual manual1;
    private Manual manual2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        manual1 = new Manual();
        manual1.setCitation("citation");
        manual1.setAuthor("author1");
        manual1.setTitle("otsikko1");
        manual1.setYear(2001);
        manual2 = new Manual();
        manual2.setCitation("citation");
        manual2.setAuthor("author2");
        manual2.setTitle("otsikko2");
        manual2.setYear(2002);
    }
    
    @Test
    public void testManual() {
        service.addManual(manual1);
        Manual retrieved = repository.findOne(manual1.getId());
        assertEquals(retrieved.getId(), manual1.getId());
        assertEquals(retrieved.getAuthor(), manual1.getAuthor());
        assertEquals(retrieved.getTitle(), manual1.getTitle());
    }
    
    @Test
    public void testDeleteManual() {
        repository.save(manual1);
        Long id = manual1.getId();
        service.deleteManual(manual1.getId());
        Manual eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetManual() {
        repository.save(manual1);
        Manual retrieved = service.getManual(manual1.getId());
        assertEquals(retrieved.getId(), manual1.getId());
        assertEquals(retrieved.getAuthor(), manual1.getAuthor());
        assertEquals(retrieved.getTitle(), manual1.getTitle());
    }
    
    @Test
    public void testListManual() {
        repository.save(manual1);
        repository.save(manual2);
        List<Manual> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Manual m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(manual1.getTitle()));
        assertTrue(titles.contains(manual2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(manual1);
        repository.save(manual2);
        List<Manual> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), manual1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(manual1);
        repository.save(manual2);
        List<Manual> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), manual1.getAuthor());
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(manual1);
        repository.save(manual2);
        List<Manual> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(manual1);
        repository.save(manual2);
        List<Manual> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    
    @Test
    public void testGetBibtex() {
        repository.save(manual1);
        String bibtex = service.getBibtex(manual1.getId());
        assertTrue(bibtex.contains("@Manual"));
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
