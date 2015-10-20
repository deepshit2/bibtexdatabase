
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Manual extends AbstractPersistable<Long> {
    
    /*
    Technical documentation.
    Required fields: title
    Optional fields: author, organization, address, edition, month, year, note, key
    */
    
    @NotBlank
    private String citation;
    
    @NotBlank
    private String title;
    
    private String author, organization, address, note, key;
    private Integer edition, month, year;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Tag> tags;

    public List<Tag> getTags() {
        if(tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
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

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }
    
    
    
    
}
