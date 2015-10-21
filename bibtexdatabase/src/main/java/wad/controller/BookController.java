package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.domain.Book;
import wad.service.BookService;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST)
    public String createBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookService.addBook(book);
        redirectAttributes.addAttribute("id", book.getId());
        redirectAttributes.addFlashAttribute("message", "New book created");
        return "redirect:/books/{id}/bibtex";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        if (!bookService.list().isEmpty()) {
            model.addAttribute("books", bookService.list());
        }
        return "books";
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBook() {
        return "newbook";
    }

    @RequestMapping(value = "/{id}/bibtex", method = RequestMethod.DELETE)
    public String deleteBook(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "Book deleted");
        return "redirect:/";
    }
    
    @RequestMapping(value="/{id}/bibtex", method = RequestMethod.GET)
    public String getBibtex(@PathVariable Long id, Model model){
        Book book = bookService.getBook(id);
        model.addAttribute("tags", book.getTags());
        model.addAttribute("bibtex", bookService.getBibtex(book.getId()));
        return "bibtex";
    }
}
