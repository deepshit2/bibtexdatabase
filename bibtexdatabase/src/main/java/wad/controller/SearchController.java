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
import wad.service.InproceedingsService;
import wad.service.MastersthesisService;
import wad.service.TechreportService;

@Controller
@RequestMapping("/")
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

    @RequestMapping(method = RequestMethod.POST)
    public String list(@RequestParam String search, Model model) {
        model.addAttribute("articles", articleService.search(search));
        model.addAttribute("books", bookService.search(search));
        model.addAttribute("inproceedings", inproceedingsService.search(search));
        model.addAttribute("mastersthesises", mastersthesisService.search(search));
        model.addAttribute("techreports", techreportService.search(search));
        return "index";
    }
}
