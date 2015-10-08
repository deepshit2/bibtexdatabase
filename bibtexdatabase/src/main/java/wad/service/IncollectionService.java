
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
public class IncollectionService {

    @Autowired
    private IncollectionRepository inproceedingsRepository;

    public List<Incollection> list() {
        List<Incollection> inproceedings = inproceedingsRepository.findAll();
        return inproceedings;
    }

    public void addIncollection(Incollection inproceedings) {
        inproceedingsRepository.save(inproceedings);
    }

    public void deleteIncollection(Long id) {
        inproceedingsRepository.delete(inproceedingsRepository.findOne(id));
    }

    public Incollection getIncollection(Long id) {
        return inproceedingsRepository.findOne(id);
    }
    
    /*
    private String toBibtex(Inproceedings inproceedings) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Inproceedings {";
        String tabs;
        Class<? extends Object> obj = inproceedings.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            boolean ehto = (field.get(inproceedings) != null && !field.get(inproceedings).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += inproceedings.getCitation() + "\n";
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
                field.get(inproceedings));
            }
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind+1,"").toString();
        result += "}";
        return result;
    }
    
    public String getBibtex(Long id) {
        Inproceedings inproceedings = inproceedingsRepository.findOne(id);
        String result = "";
        try {
        result = toBibtex(inproceedings);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
        }
    */
    
    public List<Incollection> search(String name) {
        List<Incollection> result = new ArrayList<>();
        List<Incollection> byAuthor = inproceedingsRepository.findByAuthorContaining(name);
        List<Incollection> byTitle = inproceedingsRepository.findByTitleContaining(name);
        List<Incollection> byBooktitle = inproceedingsRepository.findByBooktitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }
    
}