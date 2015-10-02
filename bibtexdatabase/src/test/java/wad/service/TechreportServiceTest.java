
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
        m1 = new Techreport();
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setInstitution("koulu1");
        m1.setYear(2001);
        m2 = new Techreport();
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
        for(Techreport m : kaikki) {
            titles.add(m.getTitle());
        }
        assertTrue(titles.contains(m1.getTitle()));
        assertTrue(titles.contains(m2.getTitle()));
    }
    
}
