package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Phdthesis;
import wad.service.PhdthesisService;

@Controller
@RequestMapping(value = "/phdthesises")
public class PhdthesisController {

    @Autowired
    private PhdthesisService phdthesisService;

    @RequestMapping(method = RequestMethod.POST)
    public String createPhdthesis(@ModelAttribute Phdthesis phdthesis, RedirectAttributes redirectAttributes) {
        phdthesisService.addPhdthesis(phdthesis);
        redirectAttributes.addAttribute("id", phdthesis.getId());
        redirectAttributes.addFlashAttribute("message", "New phdthesis created");
        return "redirect:/phdthesises/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!phdthesisService.list().isEmpty()) {
            model.addAttribute("phdthesises", phdthesisService.list());
        }
        return "phdthesises";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPhdthesis() {
        return "newphdthesis";
    }

    @RequestMapping(value = "/{id}/bibtex", method = RequestMethod.DELETE)
    public String deletePhdthesis(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        phdthesisService.deletePhdthesis(id);
        redirectAttributes.addFlashAttribute("message", "Phdthesis deleted");
        return "redirect:/";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Phdthesis phdthesis = phdthesisService.getPhdthesis(id);
        model.addAttribute("bibtex", phdthesisService.getBibtex(phdthesis.getId()));
        return "bibtex";
    }
}

