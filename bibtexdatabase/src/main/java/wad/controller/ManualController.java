package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Manual;
import wad.service.ManualService;

@Controller
@RequestMapping(value = "/manuals")
public class ManualController {

    @Autowired
    private ManualService manualService;

    @RequestMapping(method = RequestMethod.POST)
    public String createManual(@ModelAttribute Manual manual, RedirectAttributes redirectAttributes) {
        manualService.addManual(manual);
        redirectAttributes.addAttribute("id", manual.getId());
        redirectAttributes.addFlashAttribute("message", "New manual created");
        return "redirect:/manuals/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!manualService.list().isEmpty()) {
            model.addAttribute("manuals", manualService.list());
        }
        return "manuals";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newManual() {
        return "newmanual";
    }

    @RequestMapping(value = "/{id}/bibtex", method = RequestMethod.DELETE)
    public String deleteManual(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        manualService.deleteManual(id);
        redirectAttributes.addFlashAttribute("message", "Manual deleted");
        return "redirect:/";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Manual manual = manualService.getManual(id);
        model.addAttribute("tags", manual.getTags());
        model.addAttribute("bibtex", manualService.getBibtex(manual.getId()));
        return "bibtex";
    }
}

