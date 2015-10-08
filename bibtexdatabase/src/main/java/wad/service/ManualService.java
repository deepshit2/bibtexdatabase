
package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Inbook;
import wad.domain.Manual;
import wad.repository.ManualRepository;

@Service
public class ManualService {
    
    @Autowired
    private ManualRepository repo;
    
    public List<Manual> list() {
        List<Manual> items = repo.findAll();
        return items;
    }
    
    public void addManual(Manual item) {
        repo.save(item);
    }
    
    public void deleteManual(Long id) {
        repo.delete(repo.findOne(id));
    }

    public Manual getManual(Long id) {
        return repo.findOne(id);
    }
    
    /* TODO Inbook-version
    private String toBibtex(Book book) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Book {";
        String tabs;
        Class<? extends Object> obj = book.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            boolean ehto = (field.get(book) != null && !field.get(book).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += book.getCitation() + "\n";
                continue;
            }
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
    */
    /*
    public String getBibtex(Long id) {
        Inbook item = repo.findOne(id);
        String result = "";
        try {
        result = toBibtex(item);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
    }
    */
    
    public List<Manual> search(String name) {
        List<Manual> result = new ArrayList<>();
        List<Manual> byAuthor = repo.findByAuthorContaining(name);
        List<Manual> byTitle = repo.findByTitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        return result;
    }
    
    
}
