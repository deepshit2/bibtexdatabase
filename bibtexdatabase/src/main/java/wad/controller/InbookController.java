package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Inbook;
import wad.service.InbookService;

@Controller
@RequestMapping(value = "/inbooks")
public class InbookController {

    @Autowired
    private InbookService inbookService;

    @RequestMapping(method = RequestMethod.POST)
    public String createInbook(@ModelAttribute Inbook inbook, RedirectAttributes redirectAttributes) {
        inbookService.addInbook(inbook);
        redirectAttributes.addFlashAttribute("id", inbook.getId());
        redirectAttributes.addFlashAttribute("message", "New inbook created");
        return "redirect:/inbooks/new";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!inbookService.list().isEmpty()) {
            model.addAttribute("inbooks", inbookService.list());
        }
        return "inbooks";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAdded(@PathVariable Long id, Model model) {
        model.addAttribute("inbook", inbookService.getInbook(id));
        return "inbook";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newInbook() {
        return "newinbook";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteInbook(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        inbookService.deleteInbook(id);
        redirectAttributes.addFlashAttribute("message", "Inbook deleted");
        return "redirect:/inbooks";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Inbook inbook = inbookService.getInbook(id);
        model.addAttribute("bibtex", inbookService.getBibtex(inbook.getId()));
        return "bibtex";
    }
}
