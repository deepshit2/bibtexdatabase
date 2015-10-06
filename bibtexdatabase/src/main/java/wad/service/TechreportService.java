package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Techreport;
import wad.repository.TechreportRepository;

@Service
public class TechreportService {

    @Autowired
    private TechreportRepository techreportRepository;

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
