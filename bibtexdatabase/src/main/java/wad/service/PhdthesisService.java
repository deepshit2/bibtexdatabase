package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Mastersthesis;
import wad.domain.Phdthesis;
import wad.repository.PhdthesisRepository;

@Service
public class PhdthesisService {

    @Autowired
    private PhdthesisRepository mastersthesisRepository;

    public List<Phdthesis> list() {
        List<Phdthesis> phdthesises = mastersthesisRepository.findAll();
        return phdthesises;
    }

    public void addPhdthesis(Phdthesis mastersthesis) {
        mastersthesisRepository.save(mastersthesis);
    }

    public void deletePhdthesis(Long id) {
        mastersthesisRepository.delete(mastersthesisRepository.findOne(id));
    }

    public Phdthesis getPhdthesis(Long id) {
        return mastersthesisRepository.findOne(id);
    }
    /*
    private String toBibtex(Mastersthesis mastersthesis) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Mastersthesis {";
        String tabs;
        Class<? extends Object> obj = mastersthesis.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            boolean ehto = (field.get(mastersthesis) != null && !field.get(mastersthesis).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += mastersthesis.getCitation() + "\n";
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
                field.get(mastersthesis));
            }
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind+1,"").toString();
        result += "}";
        return result;
    }
    
    public String getBibtex(Long id) {
        Mastersthesis mastersthesis = mastersthesisRepository.findOne(id);
        String result = "";
        try {
        result = toBibtex(mastersthesis);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
        }
    */
    public List<Phdthesis> search(String name) {
        List<Phdthesis> result = new ArrayList<>();
        List<Phdthesis> byAuthor = mastersthesisRepository.findByAuthorContaining(name);
        List<Phdthesis> byTitle = mastersthesisRepository.findByTitleContaining(name);
        List<Phdthesis> byBooktitle = mastersthesisRepository.findBySchoolContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }

}
