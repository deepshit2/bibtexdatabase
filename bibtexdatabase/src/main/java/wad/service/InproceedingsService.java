package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Inproceedings;
import wad.domain.Tag;
import wad.repository.InproceedingsRepository;

@Service
public class InproceedingsService implements ServiceInterface<Inproceedings> {

    @Autowired
    private InproceedingsRepository inproceedingsRepository;

    public void addTag(Long id, Tag tag) {
        Inproceedings inproceedings = getInproceedings(id);
        inproceedings.getTags().add(tag);
        addInproceedings(inproceedings);
    }
    
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

    private String toBibtex(Inproceedings inproceedings) throws IllegalArgumentException, IllegalAccessException {
        String result = "@Inproceedings {";
        String tabs;
        Class<? extends Object> obj = inproceedings.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("tags")) {
                continue;
            }
            boolean ehto = (field.get(inproceedings) != null && !field.get(inproceedings).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += inproceedings.getCitation() + "\n";
                continue;
            }
            if (ehto) {
                if (field.getName().length() < 8) {
                    tabs = "\t\t\t";
                } else {
                    tabs = "\t\t";
                }
                result += String.format("%s%s=\t\t\"%s\",\n",
                        field.getName(),
                        tabs,
                        field.get(inproceedings));
            }
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind + 1, "").toString();
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
        Inproceedings inproceedings = inproceedingsRepository.findOne(id);
        String result = "";
        try {
            result = toBibtex(inproceedings);
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public String getBibtex(Inproceedings inproceedings) {
        String result = "";
        try {
            result = toBibtex(inproceedings);
        } catch (Exception ex) {
        }
        return result;
    }

    public List<Inproceedings> search(String name) {
        List<Inproceedings> result = new ArrayList<>();
        List<Inproceedings> byAuthor = inproceedingsRepository.findByAuthorContaining(name);
        List<Inproceedings> byTitle = inproceedingsRepository.findByTitleContaining(name);
        List<Inproceedings> byBooktitle = inproceedingsRepository.findByBooktitleContaining(name);
        result.addAll(byAuthor);
        for (Inproceedings inproceedings : byTitle) {
            if (!result.contains(inproceedings)) {
                result.add(inproceedings);
            }
        }
        for (Inproceedings inproceedings : byBooktitle) {
            if (!result.contains(inproceedings)) {
                result.add(inproceedings);
            }
        }
        return result;
    }

}
