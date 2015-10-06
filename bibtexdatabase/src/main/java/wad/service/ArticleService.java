package wad.service;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
    
    private String toBibtex(Article article) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Article {\n";
        Class<? extends Object> obj = article.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            if (field.get(article) == null || field.get(article).toString().isEmpty())
                continue;
            result += String.format("%s\t\t=\t\t\"%s\",\n",
                field.getName(),
                field.get(article)
            );
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind+1,"").toString();
        result += "}";
        return result;
    }
    
    public String getBibtex(Long id) {
        Article article = articleRepository.findOne(id);
        String result = "";
        try {
        result = toBibtex(article);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
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

