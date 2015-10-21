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

    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    public String find(@RequestParam String searchtext, Model model) {
        model.addAttribute("books", bookService.search(searchtext));
        model.addAttribute("articles", articleService.search(searchtext));
        model.addAttribute("inproceedings", inproceedingsService.search(searchtext));
        model.addAttribute("mastersthesises", mastersthesisService.search(searchtext));
        model.addAttribute("techreports", techreportService.search(searchtext));
        model.addAttribute("booklets", bookletService.search(searchtext));
        model.addAttribute("conferences", conferenceService.search(searchtext));
        model.addAttribute("inbooks", inbookService.search(searchtext));
        model.addAttribute("incollections", incollectionService.search(searchtext));
        model.addAttribute("manuals", manualService.search(searchtext));
        model.addAttribute("miscs", miscService.search(searchtext));
        model.addAttribute("phdtheses", phdthesisService.search(searchtext));
        model.addAttribute("proceedings", proceedingsService.search(searchtext));
        model.addAttribute("unpublished", unpublishedService.search(searchtext));
        model.addAttribute("downloadmessage",searchtext);
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
        model.addAttribute("downloadmessage",tag);
        return "index";
    }
}
