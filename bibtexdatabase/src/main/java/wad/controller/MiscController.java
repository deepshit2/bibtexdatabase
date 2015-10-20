package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Misc;
import wad.service.MiscService;

@Controller
@RequestMapping(value = "/miscs")
public class MiscController {

    @Autowired
    private MiscService miscService;

    @RequestMapping(method = RequestMethod.POST)
    public String createMisc(@ModelAttribute Misc misc, RedirectAttributes redirectAttributes) {
        miscService.addMisc(misc);
        redirectAttributes.addAttribute("id", misc.getId());
        redirectAttributes.addFlashAttribute("message", "New misc created");
        return "redirect:/miscs/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!miscService.list().isEmpty()) {
            model.addAttribute("miscs", miscService.list());
        }
        return "miscs";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMisc() {
        return "newmisc";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteMisc(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        miscService.deleteMisc(id);
        redirectAttributes.addFlashAttribute("message", "Misc deleted");
        return "redirect:/miscs";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Misc misc = miscService.getMisc(id);
        model.addAttribute("bibtex", miscService.getBibtex(misc.getId()));
        return "bibtex";
    }
}

