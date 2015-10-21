
package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Inbook;
import wad.domain.Tag;
import wad.repository.InbookRepository;

@Service
public class InbookService implements ServiceInterface<Inbook>{
    
    @Autowired
    InbookRepository repo;
    
    public void addTag(Long id, Tag tag) {
        Inbook inbook = getInbook(id);
        inbook.getTags().add(tag);
        addInbook(inbook);
    }
    
    public List<Inbook> list() {
        List<Inbook> items = repo.findAll();
        return items;
    }
    
    public void addInbook(Inbook item) {
        repo.save(item);
    }
    
    public void deleteInbook(Long id) {
        repo.delete(repo.findOne(id));
    }

    public Inbook getInbook(Long id) {
        return repo.findOne(id);
    }
    
    private String toBibtex(Inbook book) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Inbook {";
        String tabs;
        Class<? extends Object> obj = book.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getName().equals("tags")) continue;
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
        Inbook item = repo.findOne(id);
        String result = "";
        try {
        result = toBibtex(item);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
    }
    
    public String getBibtex(Inbook inbook) {
        String result = "";
        try {
        result = toBibtex(inbook);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
    }
    
    
    public List<Inbook> search(String name) {
        List<Inbook> result = new ArrayList<>();
        List<Inbook> byAuthor = repo.findByAuthorContaining(name);
        List<Inbook> byTitle = repo.findByTitleContaining(name);
        List<Inbook> byPublisher = repo.findByPublisherContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byPublisher);
        return result;
    }
    
    
}
