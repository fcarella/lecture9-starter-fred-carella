package csd214.app.controllers;

import csd214.app.entities.BookEntity;
import csd214.app.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    // Constructor Injection
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ==========================================
    // 1. READ (List all books)
    // ==========================================
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-list";
    }

    // ==========================================
    // 2. CREATE (Show the empty form)
    // ==========================================
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // We pack a blank, empty BookEntity into the model.
        // The Thymeleaf form will bind to this empty object!
        model.addAttribute("book", new BookEntity());
        return "book-form";
    }

    // ==========================================
    // 3. SAVE (Handles BOTH Create and Update!)
    // ==========================================
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") BookEntity book) {
        // Because BookEntity inherits from ProductEntity, we sync the title to the parent's "name" field
        book.setName(book.getTitle());

        // If the book has no ID, JPA creates a new row (INSERT).
        // If the book already has an ID, JPA updates the existing row (UPDATE).
        bookRepository.save(book);

        // "redirect:/" tells the browser to immediately make a new GET request to /books
        return "redirect:/books";
    }

    // ==========================================
    // 4. UPDATE (Show the form with existing data)
    // ==========================================
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        // Find the book by its ID, or return null if it doesn't exist
        BookEntity book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            model.addAttribute("book", book); // Pack the existing book into the model
            return "book-form"; // Send them to the form page
        }
        return "redirect:/books";
    }

    // ==========================================
    // 5. DELETE
    // ==========================================
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}

