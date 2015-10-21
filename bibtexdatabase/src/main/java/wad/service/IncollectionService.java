
package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Incollection;
import wad.domain.Inproceedings;
import wad.repository.IncollectionRepository;

@Service
public class IncollectionService implements ServiceInterface<Incollection>{

    @Autowired
    private IncollectionRepository incollection;

    public List<Incollection> list() {
        List<Incollection> inproceedings = incollection.findAll();
        return inproceedings;
    }

    public void addIncollection(Incollection inproceedings) {
        incollection.save(inproceedings);
    }

    public void deleteIncollection(Long id) {
        incollection.delete(incollection.findOne(id));
    }

    public Incollection getIncollection(Long id) {
        return incollection.findOne(id);
    }
    
    private String toBibtex(Incollection incollection) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Incollection {";
        String tabs;
        Class<? extends Object> obj = incollection.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getName().equals("tags")) continue;
            boolean ehto = (field.get(incollection) != null && !field.get(incollection).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += incollection.getCitation() + "\n";
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
                field.get(incollection));
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
        Incollection inproceedings = incollection.findOne(id);
        String result = "";
        try {
        result = toBibtex(inproceedings);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
        }
    
    public String getBibtex(Incollection incollection) {
        String result = "";
        try {
            result = toBibtex(incollection);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }
    
    
    public List<Incollection> search(String name) {
        List<Incollection> result = new ArrayList<>();
        List<Incollection> byAuthor = incollection.findByAuthorContaining(name);
        List<Incollection> byTitle = incollection.findByTitleContaining(name);
        List<Incollection> byBooktitle = incollection.findByBooktitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }
    
}