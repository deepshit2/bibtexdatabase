package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Unpublished;
import wad.service.UnpublishedService;

@Controller
@RequestMapping(value = "/unpublisheds")
public class UnpublishedController {

    @Autowired
    private UnpublishedService unpublishedService;

    @RequestMapping(method = RequestMethod.POST)
    public String createUnpublished(@ModelAttribute Unpublished unpublished, RedirectAttributes redirectAttributes) {
        unpublishedService.addUnpublished(unpublished);
        redirectAttributes.addFlashAttribute("id", unpublished.getId());
        redirectAttributes.addFlashAttribute("message", "New unpublished created");
        return "redirect:/unpublisheds/new";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!unpublishedService.list().isEmpty()) {
            model.addAttribute("unpublisheds", unpublishedService.list());
        }
        return "unpublisheds";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAdded(@PathVariable Long id, Model model) {
        model.addAttribute("unpublished", unpublishedService.getUnpublished(id));
        return "unpublished";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUnpublished() {
        return "newunpublished";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteUnpublished(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        unpublishedService.deleteUnpublished(id);
        redirectAttributes.addFlashAttribute("message", "Unpublished deleted");
        return "redirect:/unpublisheds";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Unpublished unpublished = unpublishedService.getUnpublished(id);
        model.addAttribute("bibtex", unpublishedService.getBibtex(unpublished.getId()));
        return "bibtex";
    }
}

