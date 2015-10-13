package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Article;
import wad.domain.Book;
import wad.domain.Booklet;
import wad.domain.Conference;
import wad.domain.Inbook;
import wad.domain.Incollection;
import wad.domain.Inproceedings;
import wad.domain.Manual;
import wad.domain.Mastersthesis;
import wad.domain.Misc;
import wad.domain.Phdthesis;
import wad.domain.Proceedings;
import wad.domain.Techreport;
import wad.domain.Unpublished;
import wad.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepo;

    public List<Book> getBooks(String keyword) {
        return tagRepo.findByName(keyword).getBooks();
    }

    public List<Article> getArticles(String keyword) {
        return tagRepo.findByName(keyword).getArticles();
    }

    public List<Booklet> getBooklets(String keyword) {
        return tagRepo.findByName(keyword).getBooklets();
    }

    public List<Conference> getConferences(String keyword) {
        return tagRepo.findByName(keyword).getConferences();
    }

    public List<Inbook> getInbooks(String keyword) {
        return tagRepo.findByName(keyword).getInbooks();
    }

    public List<Incollection> getIncollections(String keyword) {
        return tagRepo.findByName(keyword).getIncollections();
    }

    public List<Inproceedings> getInproceedings(String keyword) {
        return tagRepo.findByName(keyword).getInproceedings();
    }

    public List<Proceedings> getProceedings(String keyword) {
        return tagRepo.findByName(keyword).getProceedings();
    }

    public List<Manual> getManuals(String keyword) {
        return tagRepo.findByName(keyword).getManuals();
    }

    public List<Mastersthesis> getMastersthesises(String keyword) {
        return tagRepo.findByName(keyword).getMastersthesises();
    }

    public List<Phdthesis> getPhdthesises(String keyword) {
        return tagRepo.findByName(keyword).getPhdthesises();
    }

    public List<Techreport> getTechreports(String keyword) {
        return tagRepo.findByName(keyword).getTechreports();
    }

    public List<Unpublished> getUnpublished(String keyword) {
        return tagRepo.findByName(keyword).getUnpublisheds();
    }

    public List<Misc> getMiscs(String keyword) {
        return tagRepo.findByName(keyword).getMiscs();
    }

}
