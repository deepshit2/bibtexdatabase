
package wad.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Book extends AbstractPersistable<Long> {
    
    private String author, title, publisher, address, isbn, note;
    private Integer year, volume, edition, month, series;
    
    
}
