
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
import wad.domain.Booklet;
import wad.domain.Misc;
import wad.repository.MiscRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MiscServiceTest {
    
    @Autowired
    private MiscService service;
    
    @Autowired
    private MiscRepository repository;
    
    private Misc misc1;
    private Misc misc2;
    
    @Before
    public void setUp() {
        repository.deleteAll();
        misc1 = new Misc();
        misc1.setCitation("citation");
        misc1.setAuthor("author1");
        misc1.setTitle("otsikko1");
        misc1.setYear(2001);
        misc2 = new Misc();
        misc2.setCitation("citation");
        misc2.setAuthor("author2");
        misc2.setTitle("otsikko2");
        misc2.setYear(2002);
    }
    
    @Test
    public void testAddMisc() {
        service.addMisc(misc1);
        Misc retrieved = repository.findOne(misc1.getId());
        assertEquals(retrieved.getId(), misc1.getId());
        assertEquals(retrieved.getAuthor(), misc1.getAuthor());
        assertEquals(retrieved.getTitle(), misc1.getTitle());
    }
    
    @Test
    public void testDeleteMisc() {
        repository.save(misc1);
        Long id = misc1.getId();
        service.deleteMisc(misc1.getId());
        Misc eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetMisc() {
        repository.save(misc1);
        Misc retrieved = service.getMisc(misc1.getId());
        assertEquals(retrieved.getId(), misc1.getId());
        assertEquals(retrieved.getAuthor(), misc1.getAuthor());
        assertEquals(retrieved.getTitle(), misc1.getTitle());
    }
    
    @Test
    public void testListMisc() {
        repository.save(misc1);
        repository.save(misc2);
        List<Misc> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Misc m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(misc1.getTitle()));
        assertTrue(titles.contains(misc2.getTitle()));
    }
    
    @Test
    public void testFindByAuthor() {
        repository.save(misc1);
        repository.save(misc2);
        List<Misc> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), misc1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(misc1);
        repository.save(misc2);
        List<Misc> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), misc1.getAuthor());
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(misc1);
        repository.save(misc2);
        List<Misc> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(misc1);
        repository.save(misc2);
        List<Misc> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }
    
    @Test
    public void testGetBibtex() {
        repository.save(misc1);
        String bibtex = service.getBibtex(misc1.getId());
        assertTrue(bibtex.contains("@Misc"));
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
