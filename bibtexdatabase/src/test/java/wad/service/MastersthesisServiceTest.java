
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
        m1 = new Mastersthesis();
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setSchool("koulu1");
        m1.setYear(2001);
        m2 = new Mastersthesis();
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
        for(Mastersthesis m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }
    
}
