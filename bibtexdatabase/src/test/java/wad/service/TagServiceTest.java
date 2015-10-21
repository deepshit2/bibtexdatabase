package wad.service;

import java.util.ArrayList;
import java.util.List;
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
import wad.domain.Tag;
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
import wad.repository.TagRepository;
import wad.repository.TechreportRepository;
import wad.repository.UnpublishedRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TagServiceTest {

    private Article article1;
    private Article article2;
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;
    List<Tag> tagit1;
    List<Tag> tagit2;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private TagService tagService;

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
        tagRepo.deleteAll();
        tag1 = new Tag();
        tag1.setName("nimi");
        tag3 = new Tag();
        tag3.setName("name");
        tag2 = new Tag();
        tag2.setName("joo");
        tagit1 = new ArrayList<>();
        tagit2 = new ArrayList<>();
        tagRepo.save(tag1);
        tagRepo.save(tag2);
        tagRepo.save(tag3);
        tagit1.add(tag1);
        tagit1.add(tag3);
        tagit2.add(tag2);
    }

    @Test
    public void ArticleTagTest() {
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
        article1.setTags(tagit1);
        article2.setTags(tagit1);

        articleRepository.save(article1);
        articleRepository.save(article2);

        List<Article> articles = tagService.getArticles("nimi");
        assertTrue(!articles.isEmpty());
        assertTrue(articles.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(articles.size() == 2);
    }

    @Test
    public void BookTagTest() {
        bookrepository.deleteAll();
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
        m1.setTags(tagit1);
        m2.setTags(tagit1);
        bookrepository.save(m1);
        bookrepository.save(m2);

        List<Book> books = tagService.getBooks("nimi");
        assertTrue(!books.isEmpty());
        assertTrue(books.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(books.size() == 2);
        m2.setTags(tagit2);
        bookrepository.save(m2);
        List<Book> books2 = tagService.getBooks("joo");
        assertTrue(books2.size() == 1);
    }

    @Test
    public void BookletTagTest() {
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
        mm1.setTags(tagit1);
        mm2.setTags(tagit2);
        bookletrepository.save(mm1);
        bookletrepository.save(mm2);
        List<Booklet> books = tagService.getBooklets("nimi");
        assertTrue(!books.isEmpty());
        assertTrue(books.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(books.size() == 1);
    }

    @Test
    public void ConferenceTagTest() {
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
        mmm1.setTags(tagit1);
        mmm2.setTags(tagit2);
        crepository.save(mmm1);
        crepository.save(mmm2);
        List<Conference> conferences = tagService.getConferences("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void InbookTagTest() {
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
        im1.setTags(tagit1);
        im2.setTags(tagit1);
        inrepository.save(im1);
        inrepository.save(im2);
        List<Inbook> conferences = tagService.getInbooks("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 2);
    }

    @Test
    public void IncollectionTagTest() {
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
        ccm1.setTags(tagit1);
        ccm2.setTags(tagit1);
        incorepository.save(ccm1);
        incorepository.save(ccm2);
        List<Incollection> conferences = tagService.getIncollections("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 2);
    }

    @Test
    public void InproceedingsTagTest() {
        iim1 = new Inproceedings();
        iim1.setCitation("cite");
        iim1.setAuthor("author1");
        iim1.setTitle("otsikko1");
        iim1.setBooktitle("koulu1");
        iim1.setYear(2001);
        iim1.setTags(tagit1);
        iirepository.save(iim1);
        List<Inproceedings> conferences = tagService.getInproceedings("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void ManualTagTest() {
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
        mam1.setTags(tagit2);
        mam2.setTags(tagit2);
        marepository.save(mam1);
        marepository.save(mam2);
        List<Manual> conferences = tagService.getManuals("joo");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("joo"));
        assertTrue(conferences.size() == 2);

    }

    @Test
    public void MastersTagTest() {
        mas1 = new Mastersthesis();
        mas1.setCitation("cite");
        mas1.setAuthor("author1");
        mas1.setTitle("otsikko1");
        mas1.setSchool("koulu1");
        mas1.setYear(2001);
        mas1.setTags(tagit1);
        masrepository.save(mas1);
        List<Mastersthesis> conferences = tagService.getMastersthesises("joo");
        assertTrue(conferences.isEmpty());
        List<Mastersthesis> conferences2 = tagService.getMastersthesises("name");
        assertTrue(!conferences2.isEmpty());
    }

    @Test
    public void MiscTagTest() {
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
        mis1.setTags(tagit1);
        mis2.setTags(tagit1);
        misrepository.save(mis1);
        misrepository.save(mis2);
        List<Misc> conferences = tagService.getMiscs("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 2);

    }

    @Test
    public void PhdthesisTagTest() {
        pm1 = new Phdthesis();
        pm1.setCitation("cite");
        pm1.setAuthor("author1");
        pm1.setTitle("otsikko1");
        pm1.setSchool("koulu1");
        pm1.setYear(2001);
        pm1.setTags(tagit1);
        prepository.save(pm1);
        List<Phdthesis> conferences = tagService.getPhdthesises("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void ProceedingsTagTest() {
        prom1 = new Proceedings();
        prom1.setCitation("cite");
        prom1.setTitle("otsikko1");
        prom1.setEditor("edit1");
        prom1.setYear(2001);
        prom1.setTags(tagit1);
        prorepository.save(prom1);
        List<Proceedings> conferences = tagService.getProceedings("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void TechreportTagTest() {
        tm1 = new Techreport();
        tm1.setCitation("cite");
        tm1.setAuthor("author1");
        tm1.setTitle("otsikko1");
        tm1.setInstitution("koulu1");
        tm1.setYear(2001);
        tm1.setTags(tagit1);
        trepository.save(tm1);
        List<Techreport> conferences = tagService.getTechreports("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void UnpublishedTagTest() {
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
        um1.setTags(tagit1);
        um2.setTags(tagit2);
        urepository.save(um1);
        urepository.save(um2);
        List<Unpublished> conferences = tagService.getUnpublished("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void emptyTagRepoTest() {
        String tag = "tag";
        tagService.getArticles(tag);
        tagService.getInproceedings(tag);
        tagService.getBooks(tag);
        tagService.getMastersthesises(tag);
        tagService.getTechreports(tag);
        tagService.getBooklets(tag);
        tagService.getConferences(tag);
        tagService.getInbooks(tag);
        tagService.getIncollections(tag);
        tagService.getManuals(tag);
        tagService.getMiscs(tag);
        tagService.getPhdthesises(tag);
        tagService.getProceedings(tag);
        tagService.getUnpublished(tag);
        List<Article> articles = tagService.getArticles("wagwge");
        assertTrue(articles.isEmpty());
    }

}
