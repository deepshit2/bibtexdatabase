package wad.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Article;
import wad.domain.Book;
import wad.domain.Inproceedings;
import wad.domain.Mastersthesis;
import wad.domain.Techreport;
import wad.repository.ArticleRepository;
import wad.repository.BookRepository;
import wad.repository.InproceedingsRepository;
import wad.repository.MastersthesisRepository;
import wad.repository.TechreportRepository;
import wad.service.ArticleService;
import wad.service.BookService;
import wad.service.BookletService;
import wad.service.ConferenceService;
import wad.service.InbookService;
import wad.service.IncollectionService;
import wad.service.InproceedingsService;
import wad.service.ManualService;
import wad.service.MastersthesisService;
import wad.service.MiscService;
import wad.service.PhdthesisService;
import wad.service.ProceedingsService;
import wad.service.TagService;
import wad.service.TechreportService;
import wad.service.UnpublishedService;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private InproceedingsService inproceedingsService;

    @Autowired
    private MastersthesisService mastersthesisService;

    @Autowired
    private TechreportService techreportService;

    @Autowired
    private BookletService bookletService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private InbookService inbookService;

    @Autowired
    private IncollectionService incollectionService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private MiscService miscService;

    @Autowired
    private PhdthesisService phdthesisService;

    @Autowired
    private ProceedingsService proceedingsService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UnpublishedService unpublishedService;

    @RequestMapping(method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    public String find(@RequestParam String search, Model model) {
        model.addAttribute("books", bookService.search(search));
        model.addAttribute("articles", articleService.search(search));
        model.addAttribute("inproceedings", inproceedingsService.search(search));
        model.addAttribute("mastersthesises", mastersthesisService.search(search));
        model.addAttribute("techreports", techreportService.search(search));
        model.addAttribute("booklets", bookletService.search(search));
        model.addAttribute("conferences", conferenceService.search(search));
        model.addAttribute("inbooks", inbookService.search(search));
        model.addAttribute("incollections", incollectionService.search(search));
        model.addAttribute("manuals", manualService.search(search));
        model.addAttribute("miscs", miscService.search(search));
        model.addAttribute("phdtheses", phdthesisService.search(search));
        model.addAttribute("proceedings", proceedingsService.search(search));
        model.addAttribute("unpublished", unpublishedService.search(search));
        return "index";
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public String findByTag(@RequestParam String tag, Model model) {
        model.addAttribute("books", tagService.getBooks(tag));
        model.addAttribute("articles", tagService.getArticles(tag));
        model.addAttribute("inproceedings", tagService.getInproceedings(tag));
        model.addAttribute("mastersthesises", tagService.getMastersthesises(tag));
        model.addAttribute("techreports", tagService.getTechreports(tag));
        model.addAttribute("booklets", tagService.getBooklets(tag));
        model.addAttribute("conferences", tagService.getConferences(tag));
        model.addAttribute("inbooks", tagService.getInbooks(tag));
        model.addAttribute("incollections", tagService.getIncollections(tag));
        model.addAttribute("manuals", tagService.getManuals(tag));
        model.addAttribute("miscs", tagService.getMiscs(tag));
        model.addAttribute("phdtheses", tagService.getPhdthesises(tag));
        model.addAttribute("proceedings", tagService.getProceedings(tag));
        model.addAttribute("unpublished", tagService.getUnpublished(tag));
        return "index";
    }
}
