
package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Article;
import wad.repository.ArticleRepository;
import wad.service.ArticleService;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleService articleService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String createArticle(RedirectAttributes redirectAttributes, @ModelAttribute Article article){
        articleRepository.save(article);
        redirectAttributes.addFlashAttribute("id", article.getId());
        redirectAttributes.addFlashAttribute("message", "New article created");
        return "redirect:/articles/new";
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAdded(@PathVariable Long id, Model model){
        model.addAttribute("article",articleRepository.findOne(id));
        return "article";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle() {
        return "newarticle";
    }
    
    @RequestMapping(value="/{id}/delete", method = RequestMethod.DELETE)
    public String deleteArticle(RedirectAttributes redirectAttributes, @PathVariable Long id){
        articleRepository.delete(id);
        redirectAttributes.addFlashAttribute("message", "Article deleted");
        return "redirect:/articles";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Article article = articleRepository.findOne(id);
        model.addAttribute("bibtex", articleService.getBibtex(article.getId()));
        return "bibtex";
    }
}
