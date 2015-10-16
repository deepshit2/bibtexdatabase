package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Proceedings;
import wad.service.ProceedingsService;

@Controller
@RequestMapping(value = "/proceedings")
public class ProceedingsController {

    @Autowired
    private ProceedingsService proceedingsService;

    @RequestMapping(method = RequestMethod.POST)
    public String createProceedings(@ModelAttribute Proceedings proceedings, RedirectAttributes redirectAttributes) {
        proceedingsService.addProceedings(proceedings);
        redirectAttributes.addFlashAttribute("id", proceedings.getId());
        redirectAttributes.addFlashAttribute("message", "New proceeding created");
        return "redirect:/proceedings/new";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!proceedingsService.list().isEmpty()) {
            model.addAttribute("proceedings", proceedingsService.list());
        }
        return "proceedings";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAdded(@PathVariable Long id, Model model) {
        model.addAttribute("proceeding", proceedingsService.getProceedings(id));
        return "proceeding";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProceedings() {
        return "newproceeding";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteProceedings(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        proceedingsService.deleteProceedings(id);
        redirectAttributes.addFlashAttribute("message", "Proceeding deleted");
        return "redirect:/proceedings";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Proceedings proceedings = proceedingsService.getProceedings(id);
        model.addAttribute("bibtex", proceedingsService.getBibtex(proceedings.getId()));
        return "bibtex";
    }
}