package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Phdthesis;
import wad.domain.Tag;
import wad.repository.PhdthesisRepository;

@Service
public class PhdthesisService implements ServiceInterface<Phdthesis> {

    @Autowired
    private PhdthesisRepository phdthesisRepository;

    public void addTag(Long id, Tag tag) {
        Phdthesis phdthesis = getPhdthesis(id);
        phdthesis.getTags().add(tag);
        addPhdthesis(phdthesis);
    }
    
    public List<Phdthesis> list() {
        List<Phdthesis> phdthesises = phdthesisRepository.findAll();
        return phdthesises;
    }

    public void addPhdthesis(Phdthesis mastersthesis) {
        phdthesisRepository.save(mastersthesis);
    }

    public void deletePhdthesis(Long id) {
        phdthesisRepository.delete(phdthesisRepository.findOne(id));
    }

    public Phdthesis getPhdthesis(Long id) {
        return phdthesisRepository.findOne(id);
    }

    private String toBibtex(Phdthesis thesis) throws IllegalArgumentException, IllegalAccessException {
        String result = "@Phdthesis {";
        String tabs;
        Class<? extends Object> obj = thesis.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("tags")) {
                continue;
            }
            boolean ehto = (field.get(thesis) != null && !field.get(thesis).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += thesis.getCitation() + "\n";
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
                        field.get(thesis));
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
        Phdthesis mastersthesis = phdthesisRepository.findOne(id);
        String result = "";
        try {
            result = toBibtex(mastersthesis);
        } catch (Exception ex) {
        }
        return result;
    }

    public String getBibtex(Phdthesis phdthesis) {
        String result = "";
        try {
            result = toBibtex(phdthesis);
        } catch (Exception ex) {
        }
        return result;
    }

    public List<Phdthesis> search(String name) {
        List<Phdthesis> result = new ArrayList<>();
        List<Phdthesis> byAuthor = phdthesisRepository.findByAuthorContaining(name);
        List<Phdthesis> byTitle = phdthesisRepository.findByTitleContaining(name);
        List<Phdthesis> bySchool = phdthesisRepository.findBySchoolContaining(name);
        result.addAll(byAuthor);
        for (Phdthesis phdthesis : byTitle) {
            if (!result.contains(phdthesis)) {
                result.add(phdthesis);
            }
        }
        for (Phdthesis phdthesis : bySchool) {
            if (!result.contains(phdthesis)) {
                result.add(phdthesis);
            }
        }
        return result;
    }

}
