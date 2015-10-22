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
    private BookRepository bookRepo;
    
    private Book book1;
    private Book book2;
    
    @Autowired
    private BookletRepository bookletRepo;
    
    private Booklet booklet1;
    private Booklet booklet2;
    
    @Autowired
    private ConferenceRepository conferenceRepo;
    
    private Conference conference1;
    private Conference conference2;
    
    @Autowired
    private InbookRepository inbookRepo;
    
    private Inbook inbook1;
    private Inbook inbook2;
    
    @Autowired
    private IncollectionRepository incollectionRepo;
    
    private Incollection incollection1;
    private Incollection incollection2;
    
    @Autowired
    private InproceedingsRepository inproceedingsRepo;
    
    private Inproceedings inpro1;
    private Inproceedings inpro2;
    
    @Autowired
    private ManualRepository manualRepository;
    
    private Manual manual1;
    private Manual manual2;
    
    @Autowired
    private MastersthesisRepository mastersthesisRepo;

    private Mastersthesis masters1;
    private Mastersthesis masters2;
    
    @Autowired
    private MiscRepository miscRepo;
    
    private Misc misc1;
    private Misc misc2;
    
    @Autowired
    private PhdthesisRepository phdRepo;

    private Phdthesis phd1;
    private Phdthesis phd2;
    
    @Autowired
    private ProceedingsRepository proceedingsRepo;
    
    private Proceedings proceedings1;
    private Proceedings proceedings2;
    
    @Autowired
    private TechreportRepository techRepo;

    private Techreport techreport1;
    private Techreport techreport2;
    
    @Autowired
    private UnpublishedRepository unpublishedRepo;
    
    private Unpublished unpublished1;
    private Unpublished unpublished2;
    
    @Autowired
    DownloadService downloadService;
    
    @Before
    public void setUp() {
        articleRepository.deleteAll();
        bookRepo.deleteAll();
        bookletRepo.deleteAll();
        conferenceRepo.deleteAll();
        inbookRepo.deleteAll();
        incollectionRepo.deleteAll();
        manualRepository.deleteAll();
        inproceedingsRepo.deleteAll();
        miscRepo.deleteAll();
        phdRepo.deleteAll();
        proceedingsRepo.deleteAll();
        mastersthesisRepo.deleteAll();
        unpublishedRepo.deleteAll();
        techRepo.deleteAll();
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
        
        book1 = new Book();
        book1.setCitation("kirja");
        book1.setAuthor("author1");
        book1.setTitle("otsikko1");
        book1.setPublisher("koulu1");
        book1.setYear(2001);
        book2 = new Book();
        book2.setCitation("kirja");
        book2.setAuthor("author2");
        book2.setTitle("otsikko2");
        book2.setPublisher("koulu2");
        book2.setYear(2002);
        bookRepo.save(book1);
        bookRepo.save(book2);
        
        booklet1 = new Booklet();
        booklet1.setCitation("citation1");
        booklet1.setAuthor("author1");
        booklet1.setTitle("otsikko1");
        booklet1.setYear(2001);
        booklet2 = new Booklet();
        booklet2.setCitation("citation2");
        booklet2.setAuthor("author2");
        booklet2.setTitle("otsikko2");
        booklet2.setYear(2002);
        bookletRepo.save(booklet1);
        bookletRepo.save(booklet2);
        
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
        conferenceRepo.save(conference1);
        conferenceRepo.save(conference2);
        
        inbook1 = new Inbook();
        inbook1.setCitation("citation1");
        inbook1.setAuthor("author1");
        inbook1.setTitle("otsikko1");
        inbook1.setPublisher("koulu1");
        inbook1.setYear(2001);
        inbook1.setPages(1);
        inbook2 = new Inbook();
        inbook2.setCitation("citation2");
        inbook2.setAuthor("author2");
        inbook2.setTitle("otsikko2");
        inbook2.setPublisher("koulu2");
        inbook2.setYear(2002);
        inbook2.setPages(1);
        inbookRepo.save(inbook1);
        inbookRepo.save(inbook2);
        
        incollection1 = new Incollection();
        incollection1.setCitation("cite");
        incollection1.setAuthor("author1");
        incollection1.setTitle("otsikko1");
        incollection1.setBooktitle("koulu1");
        incollection1.setYear(2001);
        incollection1.setPublisher("aaa1");
        incollection2 = new Incollection();
        incollection2.setCitation("cite2");
        incollection2.setPublisher("aaa2");
        incollection2.setAuthor("author2");
        incollection2.setTitle("otsikko2");
        incollection2.setBooktitle("koulu2");
        incollection2.setYear(2002);
        incollectionRepo.save(incollection1);
        incollectionRepo.save(incollection2);
        
        inpro1 = new Inproceedings();
        inpro1.setCitation("cite");
        inpro1.setAuthor("author1");
        inpro1.setTitle("otsikko1");
        inpro1.setBooktitle("koulu1");
        inpro1.setYear(2001);
        inproceedingsRepo.save(inpro1);
        
        manual1 = new Manual();
        manual1.setCitation("citation1");
        manual1.setAuthor("author1");
        manual1.setTitle("otsikko1");
        manual1.setYear(2001);
        manual2 = new Manual();
        manual2.setCitation("citation2");
        manual2.setAuthor("author2");
        manual2.setTitle("otsikko2");
        manual2.setYear(2002);
        manualRepository.save(manual1);
        manualRepository.save(manual2);
        
        masters1 = new Mastersthesis();
        masters1.setCitation("cite");
        masters1.setAuthor("author1");
        masters1.setTitle("otsikko1");
        masters1.setSchool("koulu1");
        masters1.setYear(2001);
        mastersthesisRepo.save(masters1);
        
        misc1 = new Misc();
        misc1.setCitation("citation1");
        misc1.setAuthor("author1");
        misc1.setTitle("otsikko1");
        misc1.setYear(2001);
        misc2 = new Misc();
        misc2.setCitation("citation2");
        misc2.setAuthor("author2");
        misc2.setTitle("otsikko2");
        misc2.setYear(2002);
        miscRepo.save(misc1);
        miscRepo.save(misc2);
        
        phd1 = new Phdthesis();
        phd1.setCitation("cite");
        phd1.setAuthor("author1");
        phd1.setTitle("otsikko1");
        phd1.setSchool("koulu1");
        phd1.setYear(2001);
        phdRepo.save(phd1);
        
        proceedings1 = new Proceedings();
        proceedings1.setCitation("cite");
        proceedings1.setTitle("otsikko1");
        proceedings1.setEditor("edit1");
        proceedings1.setYear(2001);
        proceedingsRepo.save(proceedings1);
        
        techreport1 = new Techreport();
        techreport1.setCitation("cite");
        techreport1.setAuthor("author1");
        techreport1.setTitle("olenUniikki");
        techreport1.setInstitution("koulu1");
        techreport1.setYear(2001);
        techRepo.save(techreport1);
        
        unpublished1 = new Unpublished();
        unpublished1.setCitation("citation1");
        unpublished1.setAuthor("author1");
        unpublished1.setTitle("otsikko1");
        unpublished1.setNote("note");
        unpublished1.setYear(2001);
        unpublished2 = new Unpublished();
        unpublished2.setCitation("citation2");
        unpublished2.setAuthor("author2");
        unpublished2.setTitle("otsikko2");
        unpublished2.setNote("note");
        unpublished2.setYear(2002);
        unpublishedRepo.save(unpublished1);
        unpublishedRepo.save(unpublished2);
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
        assertTrue(bibtex.equals(techreportService.getBibtex(techreport1)+"\n\n"));
    }
    
    @Test
    public void toimiiAuthorillaJotaEiOle(){
        String bibtex = downloadService.getBibtex("minuaEiOle");
        assertTrue(bibtex.isEmpty());
    }
    
}
