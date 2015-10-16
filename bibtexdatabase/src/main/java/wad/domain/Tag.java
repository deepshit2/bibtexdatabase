
package wad.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Tag extends AbstractPersistable<Long> {
    
    @NotBlank
    @Column(unique=true)
    private String name;
    
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Article> articles;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Book> books;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Booklet> booklets;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Conference> conferences;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Inbook> inbooks;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Incollection> incollections;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Inproceedings> inproceedings;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Proceedings> proceedings;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Manual> manuals;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Mastersthesis> mastersthesises;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Misc> miscs;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Phdthesis> phdthesises;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Techreport> techreports;
    @ManyToMany(mappedBy = "tags", fetch=FetchType.EAGER)
    private List<Unpublished> unpublisheds;
    
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Booklet> getBooklets() {
        return booklets;
    }

    public void setBooklets(List<Booklet> booklets) {
        this.booklets = booklets;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

    public List<Inbook> getInbooks() {
        return inbooks;
    }

    public void setInbooks(List<Inbook> inbooks) {
        this.inbooks = inbooks;
    }

    public List<Incollection> getIncollections() {
        return incollections;
    }

    public void setIncollections(List<Incollection> incollections) {
        this.incollections = incollections;
    }

    public List<Inproceedings> getInproceedings() {
        return inproceedings;
    }

    public void setInproceedings(List<Inproceedings> inproceedings) {
        this.inproceedings = inproceedings;
    }

    public List<Proceedings> getProceedings() {
        return proceedings;
    }

    public void setProceedings(List<Proceedings> proceedings) {
        this.proceedings = proceedings;
    }

    public List<Manual> getManuals() {
        return manuals;
    }

    public void setManuals(List<Manual> manuals) {
        this.manuals = manuals;
    }

    public List<Mastersthesis> getMastersthesises() {
        return mastersthesises;
    }

    public void setMastersthesises(List<Mastersthesis> mastersthesises) {
        this.mastersthesises = mastersthesises;
    }

    public List<Misc> getMiscs() {
        return miscs;
    }

    public void setMiscs(List<Misc> miscs) {
        this.miscs = miscs;
    }

    public List<Phdthesis> getPhdthesises() {
        return phdthesises;
    }

    public void setPhdthesises(List<Phdthesis> phdthesises) {
        this.phdthesises = phdthesises;
    }

    public List<Techreport> getTechreports() {
        return techreports;
    }

    public void setTechreports(List<Techreport> techreports) {
        this.techreports = techreports;
    }

    public List<Unpublished> getUnpublisheds() {
        return unpublisheds;
    }

    public void setUnpublisheds(List<Unpublished> unpublisheds) {
        this.unpublisheds = unpublisheds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
