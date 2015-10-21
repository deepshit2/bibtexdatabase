
package wad.domain;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TagTest {

    Tag tag;
    
    @Before
    public void setUp() {
        tag = new Tag();
        tag.setName("nimi");
    }
    
    public TagTest() {
    }
    
    @Test
    public void testaaAlustamattomatListat() {
        assertTrue(tag.getArticles().isEmpty());
        assertTrue(tag.getBooks().isEmpty());
        assertTrue(tag.getBooklets().isEmpty());
        assertTrue(tag.getConferences().isEmpty());
        assertTrue(tag.getInbooks().isEmpty());
        assertTrue(tag.getIncollections().isEmpty());
        assertTrue(tag.getInproceedings().isEmpty());
        assertTrue(tag.getProceedings().isEmpty());
        assertTrue(tag.getManuals().isEmpty());
        assertTrue(tag.getMastersthesises().isEmpty());
        assertTrue(tag.getMiscs().isEmpty());
        assertTrue(tag.getPhdthesises().isEmpty());
        assertTrue(tag.getTechreports().isEmpty());
        assertTrue(tag.getUnpublisheds().isEmpty());
    }
    
    @Test
    public void testaaAlustetutListat() {
        tag.setArticles(null);
        tag.setBooks(null);
        tag.setBooklets(null);
        tag.setConferences(null);
        tag.setInbooks(null);
        tag.setIncollections(null);
        tag.setInproceedings(null);
        tag.setProceedings(null);
        tag.setManuals(null);
        tag.setMastersthesises(null);
        tag.setMiscs(null);
        tag.setPhdthesises(null);
        tag.setTechreports(null);
        tag.setUnpublisheds(null);
        assertTrue(tag.getArticles().isEmpty());
        assertTrue(tag.getBooks().isEmpty());
        assertTrue(tag.getBooklets().isEmpty());
        assertTrue(tag.getConferences().isEmpty());
        assertTrue(tag.getInbooks().isEmpty());
        assertTrue(tag.getIncollections().isEmpty());
        assertTrue(tag.getInproceedings().isEmpty());
        assertTrue(tag.getProceedings().isEmpty());
        assertTrue(tag.getManuals().isEmpty());
        assertTrue(tag.getMastersthesises().isEmpty());
        assertTrue(tag.getMiscs().isEmpty());
        assertTrue(tag.getPhdthesises().isEmpty());
        assertTrue(tag.getTechreports().isEmpty());
        assertTrue(tag.getUnpublisheds().isEmpty());
        
        
    }
    
    
}
