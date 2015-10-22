package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Conference;
import wad.domain.Tag;
import wad.repository.ConferenceRepository;

@Service
public class ConferenceService implements ServiceInterface<Conference> {

    @Autowired
    private ConferenceRepository conferenceRepository;

    public void addTag(Long id, Tag tag) {
        Conference conference = getConference(id);
        conference.getTags().add(tag);
        addConference(conference);
    }

    public List<Conference> list() {
        List<Conference> conference = conferenceRepository.findAll();
        return conference;
    }

    public void addConference(Conference conference) {
        conferenceRepository.save(conference);
    }

    public void deleteConference(Long id) {
        conferenceRepository.delete(conferenceRepository.findOne(id));
    }

    public Conference getConference(Long id) {
        return conferenceRepository.findOne(id);
    }

    private String toBibtex(Conference inproceedings) throws IllegalArgumentException, IllegalAccessException {
        String result = "@Conference {";
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
        Conference conference = conferenceRepository.findOne(id);
        String result = "";
        try {
            result = toBibtex(conference);
        } catch (Exception ex) {
        }
        return result;
    }

    public String getBibtex(Conference conference) {
        String result = "";
        try {
            result = toBibtex(conference);
        } catch (Exception ex) {
        }
        return result;
    }

    public List<Conference> search(String name) {
        List<Conference> result = new ArrayList<>();
        List<Conference> byAuthor = conferenceRepository.findByAuthorContaining(name);
        List<Conference> byTitle = conferenceRepository.findByTitleContaining(name);
        List<Conference> byBooktitle = conferenceRepository.findByBooktitleContaining(name);
        result.addAll(byAuthor);
        for (Conference conference : byTitle) {
            if (!result.contains(conference)) {
                result.add(conference);
            }
        }
        for (Conference conference : byBooktitle) {
            if (!result.contains(conference)) {
                result.add(conference);
            }
        }
        return result;
    }

}
