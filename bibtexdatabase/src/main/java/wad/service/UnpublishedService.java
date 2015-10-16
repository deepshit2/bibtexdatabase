
package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Unpublished;
import wad.repository.UnpublishedRepository;

@Service
public class UnpublishedService {

    @Autowired
    private UnpublishedRepository repo;

    public List<Unpublished> list() {
        List<Unpublished> books = repo.findAll();
        return books;
    }

    public void addUnpublished(Unpublished book) {
        repo.save(book);
    }

    public void deleteUnpublished(Long id) {
        repo.delete(repo.findOne(id));
    }

    public Unpublished getUnpublished(Long id) {
        return repo.findOne(id);
    }
    
    private String toBibtex(Unpublished book) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Unpublished {";
        String tabs;
        Class<? extends Object> obj = book.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getName().equals("tags")) continue;
            boolean ehto = (field.get(book) != null && !field.get(book).toString().isEmpty());
//            if (ehto && field.getName().equals("citation")) {
//                result += book.getCitation() + "\n";
//                continue;
//            }
            if(ehto) {
                if (field.getName().length()<8)
                    tabs="\t\t\t";
                else
                    tabs="\t\t";
                result += String.format("%s%s=\t\t\"%s\",\n",
                field.getName(),
                tabs,
                field.get(book));
            }
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind+1,"").toString();
        result += "}";
        return result;
    }
    
    public String getBibtex(Long id) {
        Unpublished book = repo.findOne(id);
        String result = "";
        try {
        result = toBibtex(book);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
    }
    
    public List<Unpublished> search(String name) {
        List<Unpublished> result = new ArrayList<>();
        List<Unpublished> byAuthor = repo.findByAuthorContaining(name);
        List<Unpublished> byTitle = repo.findByTitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        return result;
    }
    
}
