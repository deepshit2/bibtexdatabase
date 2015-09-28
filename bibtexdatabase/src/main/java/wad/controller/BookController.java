
package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Book;
import wad.repository.BookRepository;

@Controller
@RequestMapping(value="/books")
public class BookController {
    
    @Autowired
    BookRepository bookRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public String createBook(@ModelAttribute("book") Book book){
        bookRepository.save(book);
        return "redirect:/books";
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    
}
