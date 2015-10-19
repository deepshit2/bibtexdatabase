package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.service.ArticleService;
import wad.service.BookService;
import wad.service.BookletService;
import wad.service.InbookService;
import wad.service.IncollectionService;
import wad.service.InproceedingsService;
import wad.service.ManualService;
import wad.service.MastersthesisService;
import wad.service.MiscService;
import wad.service.PhdthesisService;
import wad.service.ProceedingsService;
import wad.service.TechreportService;
import wad.service.UnpublishedService;

@Controller
public class DefaultController {

    @Autowired
    BookService bookService;

    @Autowired
    ArticleService articleService;
    
    @Autowired
    BookletService bookletService;
    
    @Autowired
    InbookService inbookService;

    @Autowired
    IncollectionService incollectionService;
    
    @Autowired
    InproceedingsService inproceedingsService;

    @Autowired
    ManualService manualService;
    
    @Autowired
    MastersthesisService mastersthesisService;

    @Autowired
    MiscService miscService;
    
    @Autowired
    PhdthesisService phdthesisService;
    
    @Autowired
    ProceedingsService proceedingsService;
    
    @Autowired
    TechreportService techreportService;
    
    @Autowired
    UnpublishedService unpublishedService;
    
    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String listAll(Model model) {
        model.addAttribute("articles", articleService.list());
        model.addAttribute("books", bookService.list());
        model.addAttribute("booklets", bookletService.list());
        model.addAttribute("inbooks", inbookService.list());
        model.addAttribute("incollections", incollectionService.list());
        model.addAttribute("inproceedings", inproceedingsService.list());
        model.addAttribute("manuals", manualService.list());
        model.addAttribute("mastersthesises", mastersthesisService.list());
        model.addAttribute("miscs", miscService.list());
        model.addAttribute("proceedings", proceedingsService.list());
        model.addAttribute("techreports", techreportService.list());
        model.addAttribute("unpublisheds", unpublishedService.list());
        return "index";
    }
    
}
