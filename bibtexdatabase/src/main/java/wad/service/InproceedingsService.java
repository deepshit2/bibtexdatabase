
package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Inproceedings;
import wad.repository.InproceedingsRepository;


@Service
public class InproceedingsService {

    @Autowired
    private InproceedingsRepository inproceedingsRepository;

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
    
    public List<Inproceedings> search(String name) {
        List<Inproceedings> result = new ArrayList<>();
        List<Inproceedings> byAuthor = inproceedingsRepository.findByAuthorContaining(name);
        List<Inproceedings> byTitle = inproceedingsRepository.findByTitleContaining(name);
        List<Inproceedings> byBooktitle = inproceedingsRepository.findByBooktitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        result.addAll(byBooktitle);
        return result;
    }
    
}