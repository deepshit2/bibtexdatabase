
package wad.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Misc extends AbstractPersistable<Long> {
    
    /*
    For use when nothing else fits.
    Required fields: none
    Optional fields: author, title, howpublished, month, year, note, key
    */
    
    private String title;
    private String author, howpublished, note, key;
    private Integer month, year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {
        this.howpublished = howpublished;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    
    
    
}
