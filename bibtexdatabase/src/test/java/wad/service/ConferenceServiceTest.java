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
import wad.domain.Conference;
import wad.repository.ConferenceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ConferenceServiceTest {

    @Autowired
    private ConferenceService service;

    @Autowired
    private ConferenceRepository repository;

    private Conference conference1;
    private Conference conference2;

    @Before
    public void setUp() {
        repository.deleteAll();
        conference1 = new Conference();
        conference1.setCitation("cite");
        conference1.setAuthor("author1");
        conference1.setTitle("otsikko1");
        conference1.setBooktitle("koulu1");
        conference1.setYear(2001);
        conference2 = new Conference();
        conference2.setCitation("cite2");
        conference2.setAuthor("author2");
        conference2.setTitle("otsikko2");
        conference2.setBooktitle("koulu2");
        conference2.setYear(2002);
    }

    @Test
    public void testAddConference() {
        service.addConference(conference1);
        Conference retrieved = repository.findOne(conference1.getId());
        assertEquals(retrieved.getId(), conference1.getId());
        assertEquals(retrieved.getAuthor(), conference1.getAuthor());
        assertEquals(retrieved.getTitle(), conference1.getTitle());
    }

    @Test
    public void testDeleteConference() {
        repository.save(conference1);
        Long id = conference1.getId();
        service.deleteConference(conference1.getId());
        Conference eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }

    @Test
    public void testGetConference() {
        repository.save(conference1);
        Conference retrieved = service.getConference(conference1.getId());
        assertEquals(retrieved.getId(), conference1.getId());
        assertEquals(retrieved.getAuthor(), conference1.getAuthor());
        assertEquals(retrieved.getTitle(), conference1.getTitle());
    }

    @Test
    public void testListConference() {
        repository.save(conference1);
        repository.save(conference2);
        List<Conference> kaikki = service.list();
        List<String> titles = new ArrayList();
        for (Conference m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(conference1.getTitle()));
        assertTrue(titles.contains(conference2.getTitle()));
    }

    @Test
    public void testFindByAuthor() {
        repository.save(conference1);
        repository.save(conference2);
        List<Conference> articles = service.search("thor1");
        assertTrue(articles.size() == 1);
        assertEquals(articles.get(0).getAuthor(), conference1.getAuthor());
    }

    @Test
    public void testFindByTitle() {
        repository.save(conference1);
        repository.save(conference2);
        List<Conference> boobs = service.search("kko1");
        assertTrue(boobs.size() == 1);
        assertEquals(boobs.get(0).getAuthor(), conference1.getAuthor());
    }

    @Test
    public void testFindByBooktitle() {
        repository.save(conference1);
        repository.save(conference2);
        List<Conference> boobs = service.search("oulu2");
        assertTrue(boobs.size() == 1);
    }

    @Test
    public void testSearchCanFindAll() {
        repository.save(conference1);
        repository.save(conference2);
        List<Conference> boobs = service.search("ikko");
        assertTrue(boobs.size() == 2);
    }

    @Test
    public void nonExistCantBeFound() {
        repository.save(conference1);
        repository.save(conference2);
        List<Conference> boobs = service.search("batman134134");
        assertTrue(boobs.isEmpty());
    }

    @Test
    public void testGetBibtex() {
        repository.save(conference1);
        String bibtex = service.getBibtex(conference1.getId());
        assertTrue(bibtex.contains("@Conference"));
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
        } catch (Exception e) {

        }
        assertTrue(bibtex.isEmpty());
    }

}
