
package wad.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
    
    private String toBibtex(Article article) throws IllegalArgumentException, IllegalAccessException{
        String result = "";
        Class<? extends Object> obj = article.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            result += String.format("%s =       \"%s\", \n",
                field.getName(),
                field.get(article)
            );
        }
        return result;
    }
    
    public String getBibtex(Long id) {
        Article article = articleRepository.findOne(id);
        String result = "";
        try {
        result = toBibtex(article);
        } catch (Exception ex) {
        }
            return result;
        }
    }
