package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Incollection;
import wad.service.IncollectionService;

@Controller
@RequestMapping(value = "/incollections")
public class IncollectionController {

    @Autowired
    private IncollectionService incollectionService;

    @RequestMapping(method = RequestMethod.POST)
    public String createIncollection(@ModelAttribute Incollection incollection, RedirectAttributes redirectAttributes) {
        incollectionService.addIncollection(incollection);
        redirectAttributes.addAttribute("id", incollection.getId());
        redirectAttributes.addFlashAttribute("message", "New incollection created");
        return "redirect:/incollections/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!incollectionService.list().isEmpty()) {
            model.addAttribute("incollections", incollectionService.list());
        }
        return "incollections";
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public String getAdded(@PathVariable Long id, Model model) {
//        model.addAttribute("incollection", incollectionService.getIncollection(id));
//        return "incollection";
//    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newIncollection() {
        return "newincollection";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteIncollection(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        incollectionService.deleteIncollection(id);
        redirectAttributes.addFlashAttribute("message", "Incollection deleted");
        return "redirect:/incollections";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Incollection incollection = incollectionService.getIncollection(id);
        model.addAttribute("bibtex", incollectionService.getBibtex(incollection.getId()));
        return "bibtex";
    }
}

