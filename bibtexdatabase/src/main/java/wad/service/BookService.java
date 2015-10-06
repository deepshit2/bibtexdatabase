
package wad.service;

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
    
    public List<Book> search(String name) {
        List<Book> result = new ArrayList<>();
        List<Book> byAuthor = bookRepository.findByAuthorContaining(name);
        List<Book> byTitle = bookRepository.findByTitleContaining(name);
        result.addAll(byAuthor);
        result.addAll(byTitle);
        return result;
    }
    
}
