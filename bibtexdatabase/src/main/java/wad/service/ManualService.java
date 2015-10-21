package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Manual;
import wad.repository.ManualRepository;

@Service
public class ManualService implements ServiceInterface<Manual> {

    @Autowired
    private ManualRepository repo;

    public List<Manual> list() {
        List<Manual> items = repo.findAll();
        return items;
    }

    public void addManual(Manual item) {
        repo.save(item);
    }

    public void deleteManual(Long id) {
        repo.delete(repo.findOne(id));
    }

    public Manual getManual(Long id) {
        return repo.findOne(id);
    }

    private String toBibtex(Manual manual) throws IllegalArgumentException, IllegalAccessException {
        String result = "@Manual {";
        String tabs;
        Class<? extends Object> obj = manual.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("tags")) {
                continue;
            }
            boolean ehto = (field.get(manual) != null && !field.get(manual).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += manual.getCitation() + "\n";
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
                        field.get(manual));
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
        Manual item = repo.findOne(id);
        String result = "";
        try {
            result = toBibtex(item);
        } catch (Exception ex) {
        }
        return result;
    }

    public String getBibtex(Manual manual) {
        String result = "";
        try {
            result = toBibtex(manual);
        } catch (Exception ex) {
        }
        return result;
    }

    public List<Manual> search(String name) {
        List<Manual> result = new ArrayList<>();
        List<Manual> byAuthor = repo.findByAuthorContaining(name);
        List<Manual> byTitle = repo.findByTitleContaining(name);
        result.addAll(byAuthor);
        for (Manual manual : byTitle) {
            if (!result.contains(manual)) {
                result.add(manual);
            }
        };
        return result;
    }

}
