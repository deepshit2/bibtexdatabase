
package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Inproceedings;
import wad.repository.InproceedingsRepository;


@Service
public class InproceedingsService {

    @Autowired
    private InproceedingsRepository inproceedingsRepository;

    public List<Inproceedings> list() {
        List<Inproceedings> inproceedings = inproceedingsRepository.findAll();
        return inproceedings;
    }

    public void addInproceedings(Inproceedings inproceedings) {
        inproceedingsRepository.save(inproceedings);
    }

    public void deleteInproceedings(Long id) {
        inproceedingsRepository.delete(inproceedingsRepository.findOne(id));
    }

    public Inproceedings getInproceedings(Long id) {
        return inproceedingsRepository.findOne(id);
    }
    
    private String toBibtex(Inproceedings inproceedings) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Inproceedings {";
        Class<? extends Object> obj = inproceedings.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            boolean ehto = (field.get(inproceedings) != null && !field.get(inproceedings).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += inproceedings.getCitation() + "\n";
                continue;
            }
            if(ehto) {
                result += String.format("%s\t\t\t=\t\t\"%s\",\n",
                field.getName(),
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
    
    public List<Inproceedings> search(String name) {
        List<Inproceedings> result = new ArrayList<>();
        List<Inproceedings> byAuthor = inproceedingsRepository.findByAuthorContaining(name);
        List<Inproceedings> byTitle = inproceedingsRepository.findByTitleContaining(name);
        List<Inproceedings> byBooktitle = inproceedingsRepository.findByBooktitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }
    
}