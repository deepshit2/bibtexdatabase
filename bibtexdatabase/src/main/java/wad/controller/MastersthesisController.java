package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Mastersthesis;
import wad.service.MastersthesisService;

@Controller
@RequestMapping(value = "/mastersthesises")
public class MastersthesisController {

    @Autowired
    private MastersthesisService mastersthesisService;

    @RequestMapping(method = RequestMethod.POST)
    public String createMastersthesis(@ModelAttribute Mastersthesis mastersthesis, RedirectAttributes redirectAttributes) {
        mastersthesisService.addMastersthesis(mastersthesis);
        redirectAttributes.addAttribute("id", mastersthesis.getId());
        redirectAttributes.addFlashAttribute("message", "New mastersthesis created");
        return "redirect:/mastersthesises/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!mastersthesisService.list().isEmpty()) {
            model.addAttribute("mastersthesises", mastersthesisService.list());
        }
        return "mastersthesises";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMastersthesis() {
        return "newmastersthesis";
    }

    @RequestMapping(value = "/{id}/bibtex", method = RequestMethod.DELETE)
    public String deleteMastersthesis(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        mastersthesisService.deleteMastersthesis(id);
        redirectAttributes.addFlashAttribute("message", "Mastersthesis deleted");
        return "redirect:/";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Mastersthesis mastersthesis = mastersthesisService.getMastersthesis(id);
        model.addAttribute("tags", mastersthesis.getTags());
        model.addAttribute("bibtex", mastersthesisService.getBibtex(mastersthesis.getId()));
        return "bibtex";
    }
}
