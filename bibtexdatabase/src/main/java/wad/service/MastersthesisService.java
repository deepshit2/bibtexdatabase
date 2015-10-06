package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Mastersthesis;
import wad.repository.MastersthesisRepository;

@Service
public class MastersthesisService {

    @Autowired
    private MastersthesisRepository mastersthesisRepository;

    public List<Mastersthesis> list() {
        List<Mastersthesis> mastersthesises = mastersthesisRepository.findAll();
        return mastersthesises;
    }

    public void addMastersthesis(Mastersthesis mastersthesis) {
        mastersthesisRepository.save(mastersthesis);
    }

    public void deleteMastersthesis(Long id) {
        mastersthesisRepository.delete(mastersthesisRepository.findOne(id));
    }

    public Mastersthesis getMastersthesis(Long id) {
        return mastersthesisRepository.findOne(id);
    }

    public List<Mastersthesis> search(String name) {
        List<Mastersthesis> result = new ArrayList<>();
        List<Mastersthesis> byAuthor = mastersthesisRepository.findByAuthorContaining(name);
        List<Mastersthesis> byTitle = mastersthesisRepository.findByTitleContaining(name);
        List<Mastersthesis> byBooktitle = mastersthesisRepository.findBySchoolContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }

}
