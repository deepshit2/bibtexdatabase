package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Article;
import wad.domain.Book;
import wad.domain.Booklet;
import wad.domain.Tag;
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
@RequestMapping("tags")
public class TagController {

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

    @RequestMapping(value = "/{type}/{id}/bibtex", method = RequestMethod.POST)
    public String addTag(@RequestParam String tag, @PathVariable String type, @PathVariable Long id) {
        Tag newTag = tagService.findByName(tag);
        if (newTag == null) {
            newTag = new Tag();
            newTag.setName(tag);
            tagService.saveTag(newTag);
        }
        if (type.equals("articles")) {
            articleService.addTag(id, newTag);
        } else if (type.equals("books")) {
            bookService.addTag(id, newTag);
        } else if (type.equals("booklets")) {
            bookletService.addTag(id, newTag);
        } else if (type.equals("conferences")) {
            conferenceService.addTag(id, newTag);
        } else if (type.equals("inbooks")) {
            inbookService.addTag(id, newTag);
        } else if (type.equals("incollections")) {
            incollectionService.addTag(id, newTag);
        } else if (type.equals("inproceedings")) {
            inproceedingsService.addTag(id, newTag);
        } else if (type.equals("manuals")) {
            manualService.addTag(id, newTag);
        } else if (type.equals("mastersthesises")) {
            mastersthesisService.addTag(id, newTag);
        } else if (type.equals("miscs")) {
            miscService.addTag(id, newTag);
        } else if (type.equals("phdthesises")) {
            phdthesisService.addTag(id, newTag);
        } else if (type.equals("proceedings")) {
            proceedingsService.addTag(id, newTag);
        } else if (type.equals("techreports")) {
            techreportService.addTag(id, newTag);
        } else if (type.equals("unpublisheds")) {
            unpublishedService.addTag(id, newTag);
        }
        return "redirect:/{type}/{id}/bibtex";
    }

}
