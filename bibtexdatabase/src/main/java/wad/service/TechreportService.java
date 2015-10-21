package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Tag;
import wad.domain.Techreport;
import wad.repository.TechreportRepository;

@Service
public class TechreportService implements ServiceInterface<Techreport> {

    @Autowired
    private TechreportRepository techreportRepository;

    public void addTag(Long id, Tag tag) {
        Techreport techreport = getTechreport(id);
        techreport.getTags().add(tag);
        addTechreport(techreport);
    }
    
    public List<Techreport> list() {
        List<Techreport> techreports = techreportRepository.findAll();
        return techreports;
    }

    public void addTechreport(Techreport techreport) {
        techreportRepository.save(techreport);
    }

    public void deleteTechreport(Long id) {
        techreportRepository.delete(techreportRepository.findOne(id));
    }

    public Techreport getTechreport(Long id) {
        return techreportRepository.findOne(id);
    }
    
    private String toBibtex(Techreport techreport) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Techreport {";
        String tabs;
        Class<? extends Object> obj = techreport.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getName().equals("tags")) continue;
            boolean ehto = (field.get(techreport) != null && !field.get(techreport).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += techreport.getCitation() + "\n";
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
                field.get(techreport));
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
        Techreport techreport = techreportRepository.findOne(id);
        String result = "";
        try {
        result = toBibtex(techreport);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
        }
    
    public String getBibtex(Techreport techreport) {
        String result = "";
        try {
            result = toBibtex(techreport);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

    public List<Techreport> search(String name) {
        List<Techreport> result = new ArrayList<>();
        List<Techreport> byAuthor = techreportRepository.findByAuthorContaining(name);
        List<Techreport> byTitle = techreportRepository.findByTitleContaining(name);
        List<Techreport> byBooktitle = techreportRepository.findByInstitutionContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }

}
