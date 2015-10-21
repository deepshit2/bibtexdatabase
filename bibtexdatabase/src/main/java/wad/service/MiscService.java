package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Misc;
import wad.domain.Tag;
import wad.repository.MiscRepository;

@Service
public class MiscService implements ServiceInterface<Misc> {

    @Autowired
    private MiscRepository repo;

    public void addTag(Long id, Tag tag) {
        Misc misc = getMisc(id);
        misc.getTags().add(tag);
        addMisc(misc);
    }
    
    public List<Misc> list() {
        List<Misc> books = repo.findAll();
        return books;
    }

    public void addMisc(Misc book) {
        repo.save(book);
    }

    public void deleteMisc(Long id) {
        repo.delete(repo.findOne(id));
    }

    public Misc getMisc(Long id) {
        return repo.findOne(id);
    }

    private String toBibtex(Misc misc) throws IllegalArgumentException, IllegalAccessException {
        String result = "@Misc {";
        String tabs;
        Class<? extends Object> obj = misc.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("tags")) {
                continue;
            }
            boolean ehto = (field.get(misc) != null && !field.get(misc).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += misc.getCitation() + "\n";
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
                        field.get(misc));
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
        Misc book = repo.findOne(id);
        String result = "";
        try {
            result = toBibtex(book);
        } catch (Exception ex) {
        }
        return result;
    }

    public String getBibtex(Misc misc) {
        String result = "";
        try {
            result = toBibtex(misc);
        } catch (Exception ex) {
        }
        return result;
    }

    public List<Misc> search(String name) {
        List<Misc> result = new ArrayList<>();
        List<Misc> byAuthor = repo.findByAuthorContaining(name);
        List<Misc> byTitle = repo.findByTitleContaining(name);
        result.addAll(byAuthor);
        for (Misc misc : byTitle) {
            if (!result.contains(misc)) {
                result.add(misc);
            }
        }
        return result;
    }

}
