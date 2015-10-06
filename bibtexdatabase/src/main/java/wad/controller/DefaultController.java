package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.service.ArticleService;
import wad.service.BookService;
import wad.service.InproceedingsService;
import wad.service.MastersthesisService;
import wad.service.TechreportService;

@Controller
public class DefaultController {

     
    @Autowired
    BookService bookService;
    
    @Autowired
    ArticleService articleService;
    
    @Autowired
    InproceedingsService inproceedingsService;
    
    @Autowired
    MastersthesisService mastersthesisService;

    @Autowired
    TechreportService techreportService;
    
    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String listAll(Model model){
        model.addAttribute("articles",articleService.list());
        model.addAttribute("books",bookService.list());
        model.addAttribute("inproceedings", inproceedingsService.list());
        model.addAttribute("mastersthesis", mastersthesisService.list());
        model.addAttribute("techreports", techreportService.list());
        return "index";
    }
}
