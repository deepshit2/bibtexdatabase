
package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Conference;
import wad.domain.Inproceedings;
import wad.repository.ConferenceRepository;


@Service
public class ConferenceService implements ServiceInterface<Conference> {

    @Autowired
    private ConferenceRepository inproceedingsRepository;

    public List<Conference> list() {
        List<Conference> inproceedings = inproceedingsRepository.findAll();
        return inproceedings;
    }

    public void addConference(Conference inproceedings) {
        inproceedingsRepository.save(inproceedings);
    }

    public void deleteConference(Long id) {
        inproceedingsRepository.delete(inproceedingsRepository.findOne(id));
    }

    public Conference getConference(Long id) {
        return inproceedingsRepository.findOne(id);
    }
    
    
    private String toBibtex(Conference inproceedings) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Conference {";
        String tabs;
        Class<? extends Object> obj = inproceedings.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getName().equals("tags")) continue;
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
        Conference inproceedings = inproceedingsRepository.findOne(id);
        String result = "";
        try {
        result = toBibtex(inproceedings);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
    }
    
    public String getBibtex(Conference conference) {
        String result = "";
        try {
            result = toBibtex(conference);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }
    
    public List<Conference> search(String name) {
        List<Conference> result = new ArrayList<>();
        List<Conference> byAuthor = inproceedingsRepository.findByAuthorContaining(name);
        List<Conference> byTitle = inproceedingsRepository.findByTitleContaining(name);
        List<Conference> byBooktitle = inproceedingsRepository.findByBooktitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }
    
}