package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Conference;
import wad.service.ConferenceService;

@Controller
@RequestMapping(value = "/conferences")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping(method = RequestMethod.POST)
    public String createConference(@ModelAttribute Conference conference, RedirectAttributes redirectAttributes) {
        conferenceService.addConference(conference);
        redirectAttributes.addAttribute("id", conference.getId());
        redirectAttributes.addFlashAttribute("message", "New conference created");
        return "redirect:/conferences/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!conferenceService.list().isEmpty()) {
            model.addAttribute("conferences", conferenceService.list());
        }
        return "conferences";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newConference() {
        return "newconference";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteConference(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        conferenceService.deleteConference(id);
        redirectAttributes.addFlashAttribute("message", "Conference deleted");
        return "redirect:/conferences";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Conference conference = conferenceService.getConference(id);
        model.addAttribute("bibtex", conferenceService.getBibtex(conference.getId()));
        return "bibtex";
    }
}
