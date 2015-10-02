package wad.service;

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
}