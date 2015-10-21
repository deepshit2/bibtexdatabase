package wad.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Book;
import wad.domain.Tag;
import wad.repository.BookRepository;

@Service
public class BookService implements ServiceInterface<Book> {

    @Autowired
    private BookRepository bookRepository;

    public void addTag(Long bookId, Tag tag) {
        Book book = getBook(bookId);
        book.getTags().add(tag);
        addBook(book);
    }

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

    private String toBibtex(Book book) throws IllegalArgumentException, IllegalAccessException {
        String result = "@Book {";
        String tabs;
        Class<? extends Object> obj = book.getClass();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("tags")) {
                continue;
            }
            boolean ehto = (field.get(book) != null && !field.get(book).toString().isEmpty());
            if (ehto && field.getName().equals("citation")) {
                result += book.getCitation() + "\n";
                continue;
            }
            if (ehto) {
                if (field.getName().length() < 8) {
                    tabs = "\t\t\t";
                } else {
                    tabs = "\t\t";
                }
                result += String.format("%s%s=\t\t\"%s\",\n",
                        field.getName(),
                        tabs,
                        field.get(book));
            }
        }
        int ind = result.lastIndexOf(",");
        result = new StringBuilder(result).replace(ind, ind + 1, "").toString();
        result += "}";
        result = aakkosetBibtexMuotoon(result);
        return result;
    }

    private String aakkosetBibtexMuotoon(String result) {
        result = result.replace("ä", "{\\\"a}");
        result = result.replace("ö", "{\\\"o}");
        result = result.replace("å", "{\\aa}");
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

    public String getBibtex(Book book) {
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
