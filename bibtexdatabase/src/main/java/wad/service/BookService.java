
package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Book;
import wad.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.delete(bookRepository.findOne(id));
    }

    public Book getBook(Long id) {
        return bookRepository.findOne(id);
    }
    
    private String toBibtex(Book book) throws IllegalArgumentException, IllegalAccessException{
        String result = "@Book {";
        Class<? extends Object> obj = book.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            if (field.get(book) == null || field.get(book).toString().isEmpty())
                continue;
            if (field.getName().equals("citation")) {
                result += book.getCitation() + "\n";
                continue;
            }
            result += String.format("%s\t\t=\t\t\"%s\",\n",
                field.getName(),
                field.get(book)
            );
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind+1,"").toString();
        result += "}";
        return result;
    }
    
    public String getBibtex(Long id) {
        Book book = bookRepository.findOne(id);
        String result = "";
        try {
        result = toBibtex(book);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
            return result;
        }
    
    public List<Book> search(String name) {
        List<Book> result = new ArrayList<>();
        List<Book> byAuthor = bookRepository.findByAuthorContaining(name);
        List<Book> byTitle = bookRepository.findByTitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        return result;
    }
    
}
