/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;


import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wad.Application;
import wad.domain.Article;
import wad.domain.Book;
import wad.domain.Booklet;
import wad.domain.Conference;
import wad.domain.Inbook;
import wad.domain.Incollection;
import wad.domain.Inproceedings;
import wad.domain.Manual;
import wad.domain.Mastersthesis;
import wad.domain.Misc;
import wad.domain.Phdthesis;
import wad.domain.Proceedings;
import wad.domain.Techreport;
import wad.domain.Unpublished;
import wad.repository.ArticleRepository;
import wad.repository.BookRepository;
import wad.repository.BookletRepository;
import wad.repository.ConferenceRepository;
import wad.repository.InbookRepository;
import wad.repository.IncollectionRepository;
import wad.repository.InproceedingsRepository;
import wad.repository.ManualRepository;
import wad.repository.MastersthesisRepository;
import wad.repository.MiscRepository;
import wad.repository.PhdthesisRepository;
import wad.repository.ProceedingsRepository;
import wad.repository.TechreportRepository;
import wad.repository.UnpublishedRepository;

/**
 *
 * @author santeri
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DownloadServiceTest {
    @Autowired
    private ArticleRepository articleRepository;
    
    private Article article1;
    private Article article2;
    
    @Autowired
    private BookRepository bookrepository;
    
    private Book m1;
    private Book m2;
    
    @Autowired
    private BookletRepository bookletrepository;
    
    private Booklet mm1;
    private Booklet mm2;
    
    @Autowired
    private ConferenceRepository crepository;
    
    private Conference mmm1;
    private Conference mmm2;
    
    @Autowired
    private InbookRepository inrepository;
    
    private Inbook im1;
    private Inbook im2;
    
    @Autowired
    private IncollectionRepository incorepository;
    
    private Incollection ccm1;
    private Incollection ccm2;
    
    @Autowired
    private InproceedingsRepository iirepository;
    
    private Inproceedings iim1;
    private Inproceedings iim2;
    
    @Autowired
    private ManualRepository marepository;
    
    private Manual mam1;
    private Manual mam2;
    
    @Autowired
    private MastersthesisRepository masrepository;

    private Mastersthesis mas1;
    private Mastersthesis mas2;
    
    @Autowired
    private MiscRepository misrepository;
    
    private Misc mis1;
    private Misc mis2;
    
    @Autowired
    private PhdthesisRepository prepository;

    private Phdthesis pm1;
    private Phdthesis pm2;
    
    @Autowired
    private ProceedingsRepository prorepository;
    
    private Proceedings prom1;
    private Proceedings prom2;
    
    @Autowired
    private TechreportRepository trepository;

    private Techreport tm1;
    private Techreport tm2;
    
    @Autowired
    private UnpublishedRepository urepository;
    
    private Unpublished um1;
    private Unpublished um2;
    
    @Autowired
    DownloadService downloadService;
    
    @Before
    public void setUp() {
        articleRepository.deleteAll();
        bookrepository.deleteAll();
        bookletrepository.deleteAll();
        crepository.deleteAll();
        inrepository.deleteAll();
        incorepository.deleteAll();
        marepository.deleteAll();
        iirepository.deleteAll();
        misrepository.deleteAll();
        prepository.deleteAll();
        prorepository.deleteAll();
        masrepository.deleteAll();
        urepository.deleteAll();
        trepository.deleteAll();
        article1 = new Article();
        article1.setCitation("artsu");
        article1.setAuthor("kirjoittaja1");
        article1.setTitle("otsikko1");
        article1.setJournal("journal");
        article1.setYear(2001);
        article1.setVolume(2);
        article2 = new Article();
        article2.setCitation("artsu2");
        article2.setAuthor("kirjoittaja2");
        article2.setTitle("otsikko2");
        article2.setJournal("journal2");
        article2.setYear(2001);
        article2.setVolume(2);
        articleRepository.save(article1);
        articleRepository.save(article2);
        
        m1 = new Book();
        m1.setCitation("kirja");
        m1.setAuthor("author1");
        m1.setTitle("otsikko1");
        m1.setPublisher("koulu1");
        m1.setYear(2001);
        m2 = new Book();
        m2.setCitation("kirja");
        m2.setAuthor("author2");
        m2.setTitle("otsikko2");
        m2.setPublisher("koulu2");
        m2.setYear(2002);
        bookrepository.save(m1);
        bookrepository.save(m2);
        
        mm1 = new Booklet();
        mm1.setCitation("citation1");
        mm1.setAuthor("author1");
        mm1.setTitle("otsikko1");
        mm1.setYear(2001);
        mm2 = new Booklet();
        mm2.setCitation("citation2");
        mm2.setAuthor("author2");
        mm2.setTitle("otsikko2");
        mm2.setYear(2002);
        bookletrepository.save(mm1);
        bookletrepository.save(mm2);
        
        mmm1 = new Conference();
        mmm1.setCitation("cite");
        mmm1.setAuthor("author1");
        mmm1.setTitle("otsikko1");
        mmm1.setBooktitle("koulu1");
        mmm1.setYear(2001);
        mmm2 = new Conference();
        mmm2.setCitation("cite2");
        mmm2.setAuthor("author2");
        mmm2.setTitle("otsikko2");
        mmm2.setBooktitle("koulu2");
        mmm2.setYear(2002);
        crepository.save(mmm1);
        crepository.save(mmm2);
        
        im1 = new Inbook();
        im1.setCitation("citation1");
        im1.setAuthor("author1");
        im1.setTitle("otsikko1");
        im1.setPublisher("koulu1");
        im1.setYear(2001);
        im1.setPages(1);
        im2 = new Inbook();
        im2.setCitation("citation2");
        im2.setAuthor("author2");
        im2.setTitle("otsikko2");
        im2.setPublisher("koulu2");
        im2.setYear(2002);
        im2.setPages(1);
        inrepository.save(im1);
        inrepository.save(im2);
        
        ccm1 = new Incollection();
        ccm1.setCitation("cite");
        ccm1.setAuthor("author1");
        ccm1.setTitle("otsikko1");
        ccm1.setBooktitle("koulu1");
        ccm1.setYear(2001);
        ccm1.setPublisher("aaa1");
        ccm2 = new Incollection();
        ccm2.setCitation("cite2");
        ccm2.setPublisher("aaa2");
        ccm2.setAuthor("author2");
        ccm2.setTitle("otsikko2");
        ccm2.setBooktitle("koulu2");
        ccm2.setYear(2002);
        incorepository.save(ccm1);
        incorepository.save(ccm2);
        
        iim1 = new Inproceedings();
        iim1.setCitation("cite");
        iim1.setAuthor("author1");
        iim1.setTitle("otsikko1");
        iim1.setBooktitle("koulu1");
        iim1.setYear(2001);
        iirepository.save(iim1);
        
        mam1 = new Manual();
        mam1.setCitation("citation1");
        mam1.setAuthor("author1");
        mam1.setTitle("otsikko1");
        mam1.setYear(2001);
        mam2 = new Manual();
        mam2.setCitation("citation2");
        mam2.setAuthor("author2");
        mam2.setTitle("otsikko2");
        mam2.setYear(2002);
        marepository.save(mam1);
        marepository.save(mam2);
        
        mas1 = new Mastersthesis();
        mas1.setCitation("cite");
        mas1.setAuthor("author1");
        mas1.setTitle("otsikko1");
        mas1.setSchool("koulu1");
        mas1.setYear(2001);
        masrepository.save(mas1);
        
        mis1 = new Misc();
        mis1.setCitation("citation1");
        mis1.setAuthor("author1");
        mis1.setTitle("otsikko1");
        mis1.setYear(2001);
        mis2 = new Misc();
        mis2.setCitation("citation2");
        mis2.setAuthor("author2");
        mis2.setTitle("otsikko2");
        mis2.setYear(2002);
        misrepository.save(mis1);
        misrepository.save(mis2);
        
        pm1 = new Phdthesis();
        pm1.setCitation("cite");
        pm1.setAuthor("author1");
        pm1.setTitle("otsikko1");
        pm1.setSchool("koulu1");
        pm1.setYear(2001);
        prepository.save(pm1);
        
        prom1 = new Proceedings();
        prom1.setCitation("cite");
        prom1.setTitle("otsikko1");
        prom1.setEditor("edit1");
        prom1.setYear(2001);
        prorepository.save(prom1);
        
        tm1 = new Techreport();
        tm1.setCitation("cite");
        tm1.setAuthor("author1");
        tm1.setTitle("olenUniikki");
        tm1.setInstitution("koulu1");
        tm1.setYear(2001);
        trepository.save(tm1);
        
        um1 = new Unpublished();
        um1.setCitation("citation1");
        um1.setAuthor("author1");
        um1.setTitle("otsikko1");
        um1.setNote("note");
        um1.setYear(2001);
        um2 = new Unpublished();
        um2.setCitation("citation2");
        um2.setAuthor("author2");
        um2.setTitle("otsikko2");
        um2.setNote("note");
        um2.setYear(2002);
        urepository.save(um1);
        urepository.save(um2);
    }
    
    @Test
    public void bibtexitJotkaSisaltavatAuthor(){
        String bibtex = downloadService.getBibtex("author");
        assertTrue(bibtex.contains("note"));
    }
    
    @Test
    public void bibtexitJoillaOnTiettyTitle(){
        TechreportService techreportService = new TechreportService();
        String bibtex = downloadService.getBibtex("olenUniikki");
        assertTrue(bibtex.equals(techreportService.getBibtex(tm1)+"\n\n"));
    }
    
    @Test
    public void toimiiAuthorillaJotaEiOle(){
        String bibtex = downloadService.getBibtex("minuaEiOle");
        assertTrue(bibtex.isEmpty());
    }
    
}
