package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Article;
import wad.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> list() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.delete(articleRepository.findOne(id));
    }

    public Article getArticle(Long id) {
        return articleRepository.findOne(id);
    }

    public List<Article> search(String name) {
        List<Article> result = new ArrayList<>();
        List<Article> byAuthor = articleRepository.findByAuthorContaining(name);
        List<Article> byTitle = articleRepository.findByTitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        return result;
    }

}
