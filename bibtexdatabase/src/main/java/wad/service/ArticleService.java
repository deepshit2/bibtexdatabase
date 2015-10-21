package wad.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Article;
import wad.domain.Tag;
import wad.repository.ArticleRepository;

@Service
public class ArticleService implements ServiceInterface<Article> {

    @Autowired
    private ArticleRepository articleRepository;

    public void addTag(Long articleId, Tag tag) {
        Article a = getArticle(articleId);
        a.getTags().add(tag);
        addArticle(a);
    }

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

    private String toBibtex(Article article) throws IllegalArgumentException, IllegalAccessException {
        String result = "@Article {";
        String tabs;
        Class<? extends Object> obj = article.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("tags")) {
                continue;
            }
            boolean ehto = (field.get(article) != null && !field.get(article).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += article.getCitation() + "\n";
                continue;
            }
            if (ehto) {
                if (field.getName().length() < 8) {
                    tabs = "\t\t\t";
                } else {
                    tabs = "\t\t";
                }
                result += String.format("%s%s=\t\t\"%s\",\n",
                        field.getName(),
                        tabs,
                        field.get(article));
            }
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind + 1, "").toString();
        result += "}";
        result = aakkosetBibtexMuotoon(result);
        return result;
    }

    private String aakkosetBibtexMuotoon(String result) {
        result = result.replace("ä", "{\\\"a}");
        result = result.replace("ö", "{\\\"o}");
        result = result.replace("å", "{\\aa}");
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

    public String getBibtex(Article article) {
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
