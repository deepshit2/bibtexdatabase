/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Techreport;
import wad.service.TechreportService;

@Controller
@RequestMapping(value = "/techreports")
public class TechreportController {

    @Autowired
    private TechreportService techreportService;

    @RequestMapping(method = RequestMethod.POST)
    public String createTechreport(@ModelAttribute Techreport techreport, RedirectAttributes redirectAttributes) {
        techreportService.addTechreport(techreport);
        redirectAttributes.addAttribute("id", techreport.getId());
        redirectAttributes.addFlashAttribute("message", "New techreport created");
        return "redirect:/techreports/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!techreportService.list().isEmpty()) {
            model.addAttribute("techreports", techreportService.list());
        }
        return "techreports";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTechreport() {
        return "newtechreport";
    }

    @RequestMapping(value = "/{id}/bibtex", method = RequestMethod.DELETE)
    public String deleteTechreport(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        techreportService.deleteTechreport(id);
        redirectAttributes.addFlashAttribute("message", "Techreport deleted");
        return "redirect:/";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Techreport techreport = techreportService.getTechreport(id);
        model.addAttribute("tags",techreport.getTags());
        model.addAttribute("bibtex", techreportService.getBibtex(techreport.getId()));
        return "bibtex";
    }
}
