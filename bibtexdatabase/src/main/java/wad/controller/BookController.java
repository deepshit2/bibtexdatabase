
package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Book;
import wad.repository.BookRepository;

@Controller
@RequestMapping(value="/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public String createBook(RedirectAttributes redirectAttributes, @ModelAttribute Book book){
        bookRepository.save(book);
        redirectAttributes.addAttribute("id", book.getId());
        redirectAttributes.addFlashAttribute("message", "New book created");
        return "redirect:/books/{id}";
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBook() {
        return "newbook";
    }

    
    @RequestMapping(value="/{id}/delete", method = RequestMethod.DELETE)
    public String deleteBook(RedirectAttributes redirectAttributes, @PathVariable Long id){
        bookRepository.delete(id);
        redirectAttributes.addFlashAttribute("message", "Book deleted");
        return "redirect:/books";
    }
}
