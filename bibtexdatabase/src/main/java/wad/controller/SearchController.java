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

@Controller
@RequestMapping("/")
public class SearchController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private InproceedingsRepository inproceedingsRepository;

    @Autowired
    private MastersthesisRepository mastersthesisRepository;

    @Autowired
    private TechreportRepository techreportRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String list(@RequestParam String search, Model model) {

        if (search != null) {
            List<Article> articles = new ArrayList<>();
            articles.addAll(articleRepository.findByAuthorContaining(search));
            articles.addAll(articleRepository.findByTitleContaining(search));
            model.addAttribute("articles", articles);

            List<Book> books = new ArrayList<>();
            books.addAll(bookRepository.findByAuthorContaining(search));
            books.addAll(bookRepository.findByTitleContaining(search));
            model.addAttribute("books", books);

            List<Inproceedings> inproceedings = new ArrayList<>();
            inproceedings.addAll(inproceedingsRepository.findByAuthorContaining(search));
            inproceedings.addAll(inproceedingsRepository.findByBooktitleContaining(search));
            inproceedings.addAll(inproceedingsRepository.findByTitleContaining(search));
            model.addAttribute("inproceedings", inproceedings);

            List<Mastersthesis> mastersthesises = new ArrayList<>();
            mastersthesises.addAll(mastersthesisRepository.findByAuthorContaining(search));
            mastersthesises.addAll(mastersthesisRepository.findBySchoolContaining(search));
            mastersthesises.addAll(mastersthesisRepository.findByTitleContaining(search));
            model.addAttribute("mastersthesises", mastersthesises);

            List<Techreport> techreports = new ArrayList<>();
            techreports.addAll(techreportRepository.findByAuthorContaining("name"));
            techreports.addAll(techreportRepository.findByInstitutionContaining("name"));
            techreports.addAll(techreportRepository.findByTitleContaining("name"));
            model.addAttribute("techreports", techreports);
        }
        return "index";
    }
}
