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

    private Book book1;
    private Book book2;

    @Autowired
    private BookletRepository bookletrepository;

    private Booklet booklet1;
    private Booklet booklet2;

    @Autowired
    private ConferenceRepository conferenceRepository;

    private Conference conference1;
    private Conference conference2;

    @Autowired
    private InbookRepository inbookRepository;

    private Inbook inbook1;
    private Inbook inbook2;

    @Autowired
    private IncollectionRepository incollectionRepository;

    private Incollection incollection1;
    private Incollection incollection2;

    @Autowired
    private InproceedingsRepository inproceedingsRepository;

    private Inproceedings inproceedings1;
    private Inproceedings inproceedings2;

    @Autowired
    private ManualRepository manualRepository;

    private Manual manual1;
    private Manual manual2;

    @Autowired
    private MastersthesisRepository mastersthesisRepository;

    private Mastersthesis mas1;
    private Mastersthesis mas2;

    @Autowired
    private MiscRepository miscRepository;

    private Misc misc1;
    private Misc misc2;

    @Autowired
    private PhdthesisRepository phdRepository;

    private Phdthesis phd1;
    private Phdthesis phd2;

    @Autowired
    private ProceedingsRepository proceedingsRepository;

    private Proceedings proceedings1;
    private Proceedings proceedings2;

    @Autowired
    private TechreportRepository techRepository;

    private Techreport techreport1;
    private Techreport techreport2;

    @Autowired
    private UnpublishedRepository unpublishedRepository;

    private Unpublished unpublished1;
    private Unpublished unpublished2;

    @Before
    public void setUp() {
        articleRepository.deleteAll();
        bookrepository.deleteAll();
        bookletrepository.deleteAll();
        conferenceRepository.deleteAll();
        inbookRepository.deleteAll();
        incollectionRepository.deleteAll();
        manualRepository.deleteAll();
        inproceedingsRepository.deleteAll();
        miscRepository.deleteAll();
        phdRepository.deleteAll();
        proceedingsRepository.deleteAll();
        mastersthesisRepository.deleteAll();
        unpublishedRepository.deleteAll();
        techRepository.deleteAll();
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
        book1.setTags(tagit1);
        book2.setTags(tagit1);
        bookrepository.save(book1);
        bookrepository.save(book2);

        List<Book> books = tagService.getBooks("nimi");
        assertTrue(!books.isEmpty());
        assertTrue(books.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(books.size() == 2);
        book2.setTags(tagit2);
        bookrepository.save(book2);
        List<Book> books2 = tagService.getBooks("joo");
        assertTrue(books2.size() == 1);
    }

    @Test
    public void BookletTagTest() {
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
        booklet1.setTags(tagit1);
        booklet2.setTags(tagit2);
        bookletrepository.save(booklet1);
        bookletrepository.save(booklet2);
        List<Booklet> books = tagService.getBooklets("nimi");
        assertTrue(!books.isEmpty());
        assertTrue(books.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(books.size() == 1);
    }

    @Test
    public void ConferenceTagTest() {
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
        conference1.setTags(tagit1);
        conference2.setTags(tagit2);
        conferenceRepository.save(conference1);
        conferenceRepository.save(conference2);
        List<Conference> conferences = tagService.getConferences("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void InbookTagTest() {
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
        inbook1.setTags(tagit1);
        inbook2.setTags(tagit1);
        inbookRepository.save(inbook1);
        inbookRepository.save(inbook2);
        List<Inbook> conferences = tagService.getInbooks("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 2);
    }

    @Test
    public void IncollectionTagTest() {
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
        incollection1.setTags(tagit1);
        incollection2.setTags(tagit1);
        incollectionRepository.save(incollection1);
        incollectionRepository.save(incollection2);
        List<Incollection> conferences = tagService.getIncollections("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 2);
    }

    @Test
    public void InproceedingsTagTest() {
        inproceedings1 = new Inproceedings();
        inproceedings1.setCitation("cite");
        inproceedings1.setAuthor("author1");
        inproceedings1.setTitle("otsikko1");
        inproceedings1.setBooktitle("koulu1");
        inproceedings1.setYear(2001);
        inproceedings1.setTags(tagit1);
        inproceedingsRepository.save(inproceedings1);
        List<Inproceedings> conferences = tagService.getInproceedings("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void ManualTagTest() {
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
        manual1.setTags(tagit2);
        manual2.setTags(tagit2);
        manualRepository.save(manual1);
        manualRepository.save(manual2);
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
        mastersthesisRepository.save(mas1);
        List<Mastersthesis> conferences = tagService.getMastersthesises("joo");
        assertTrue(conferences.isEmpty());
        List<Mastersthesis> conferences2 = tagService.getMastersthesises("name");
        assertTrue(!conferences2.isEmpty());
    }

    @Test
    public void MiscTagTest() {
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
        misc1.setTags(tagit1);
        misc2.setTags(tagit1);
        miscRepository.save(misc1);
        miscRepository.save(misc2);
        List<Misc> conferences = tagService.getMiscs("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 2);

    }

    @Test
    public void PhdthesisTagTest() {
        phd1 = new Phdthesis();
        phd1.setCitation("cite");
        phd1.setAuthor("author1");
        phd1.setTitle("otsikko1");
        phd1.setSchool("koulu1");
        phd1.setYear(2001);
        phd1.setTags(tagit1);
        phdRepository.save(phd1);
        List<Phdthesis> conferences = tagService.getPhdthesises("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void ProceedingsTagTest() {
        proceedings1 = new Proceedings();
        proceedings1.setCitation("cite");
        proceedings1.setTitle("otsikko1");
        proceedings1.setEditor("edit1");
        proceedings1.setYear(2001);
        proceedings1.setTags(tagit1);
        proceedingsRepository.save(proceedings1);
        List<Proceedings> conferences = tagService.getProceedings("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void TechreportTagTest() {
        techreport1 = new Techreport();
        techreport1.setCitation("cite");
        techreport1.setAuthor("author1");
        techreport1.setTitle("otsikko1");
        techreport1.setInstitution("koulu1");
        techreport1.setYear(2001);
        techreport1.setTags(tagit1);
        techRepository.save(techreport1);
        List<Techreport> conferences = tagService.getTechreports("nimi");
        assertTrue(!conferences.isEmpty());
        assertTrue(conferences.get(0).getTags().get(0).getName().equals("nimi"));
        assertTrue(conferences.size() == 1);
    }

    @Test
    public void UnpublishedTagTest() {
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
        unpublished1.setTags(tagit1);
        unpublished2.setTags(tagit2);
        unpublishedRepository.save(unpublished1);
        unpublishedRepository.save(unpublished2);
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
