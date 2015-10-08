
package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Inproceedings;
import wad.domain.Proceedings;
import wad.repository.ProceedingsRepository;


@Service
public class ProceedingsService {

    @Autowired
    private ProceedingsRepository inproceedingsRepository;

    public List<Proceedings> list() {
        List<Proceedings> inproceedings = inproceedingsRepository.findAll();
        return inproceedings;
    }

    public void addProceedings(Proceedings inproceedings) {
        inproceedingsRepository.save(inproceedings);
    }

    public void deleteProceedings(Long id) {
        inproceedingsRepository.delete(inproceedingsRepository.findOne(id));
    }

    public Proceedings getProceedings(Long id) {
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
    public List<Proceedings> search(String name) {
        List<Proceedings> result = new ArrayList<>();
        List<Proceedings> byTitle = inproceedingsRepository.findByTitleContaining(name);
        List<Proceedings> byEditor = inproceedingsRepository.findByEditorContaining(name);
        result.addAll(byEditor);
        result.addAll(byTitle);
        return result;
    }
    
}