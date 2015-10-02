
package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Book;
import wad.repository.BookRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Book> list() {
        List<Book> books = articleRepository.findAll();
        return books;
    }

    public void addBook(Book book) {
        articleRepository.save(book);
    }

    public void deleteBook(Long id) {
        articleRepository.delete(articleRepository.findOne(id));
    }

    public Book getBook(Long id) {
        return articleRepository.findOne(id);
    }
}
