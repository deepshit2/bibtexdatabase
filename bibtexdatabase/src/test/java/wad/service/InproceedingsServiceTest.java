
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
import wad.repository.InproceedingsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class InproceedingsServiceTest {

    @Autowired
    private InproceedingsService service;
    
    @Autowired
    private InproceedingsRepository repository;
    
    private Inproceedings m1;
    private Inproceedings m2;
    
    @Before
    public void setUp() {
        m1 = new Inproceedings();
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setBooktitle("koulu1");
        m1.setYear(2001);
        m2 = new Inproceedings();
        m2.setAuthor("author2");
        m2.setTitle("otsikko2");
        m2.setBooktitle("koulu2");
        m2.setYear(2002);
    }
    
    @Test
    public void testAddInproceedings() {
        service.addInproceedings(m1);
        Inproceedings retrieved = repository.findOne(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testDeleteInproceedings() {
        repository.save(m1);
        Long id = m1.getId();
        service.deleteInproceedings(m1.getId());
        Inproceedings eiOle = repository.findOne(id);
        assertTrue(eiOle == null);
    }
    
    @Test
    public void testGetInproceedings() {
        repository.save(m1);
        Inproceedings retrieved = service.getInproceedings(m1.getId());
        assertEquals(retrieved.getId(), m1.getId());
        assertEquals(retrieved.getAuthor(), m1.getAuthor());
        assertEquals(retrieved.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testListInproceedings() {
        repository.save(m1);
        repository.save(m2);
        List<Inproceedings> kaikki = service.list();
        List<String> titles = new ArrayList();
        for(Inproceedings m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }
    
    
}
