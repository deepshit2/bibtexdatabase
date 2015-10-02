package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Inproceedings;
import wad.service.InproceedingsService;

@Controller
@RequestMapping(value = "/inproceedings")
public class InproceedingsController {

    @Autowired
    private InproceedingsService inproceedingsService;

    @RequestMapping(method = RequestMethod.POST)
    public String createInproceedings(@ModelAttribute Inproceedings inproceedings, RedirectAttributes redirectAttributes) {
        inproceedingsService.addInproceedings(inproceedings);
        redirectAttributes.addAttribute("id", inproceedings.getId());
        redirectAttributes.addFlashAttribute("message", "New inproceeding created");
        return "redirect:/inproceedings/{id}";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!inproceedingsService.list().isEmpty()) {
            model.addAttribute("inproceedings", inproceedingsService.list());
        }
        return "inproceedings";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAdded(@PathVariable Long id, Model model) {
        model.addAttribute("inproceeding", inproceedingsService.getInproceedings(id));
        return "inproceeding";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newInproceedings() {
        return "newinproceeding";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteInproceedings(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        inproceedingsService.deleteInproceedings(id);
        redirectAttributes.addFlashAttribute("message", "Inproceeding deleted");
        return "redirect:/inproceedings";
    }
}
