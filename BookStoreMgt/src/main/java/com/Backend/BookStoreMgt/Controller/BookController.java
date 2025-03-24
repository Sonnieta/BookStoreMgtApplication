package com.Backend.BookStoreMgt.Controller;

import com.Backend.BookStoreMgt.Entity.Book;
import com.Backend.BookStoreMgt.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ✅ Retrieve paginated & sorted books (Both ADMIN & USER can access)
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,  // Default: First page
            @RequestParam(defaultValue = "10") int size, // Default: 10 books per page
            @RequestParam(defaultValue = "title") String sortBy // Default: Sort by title
    ) {
        return ResponseEntity.ok(bookService.getAllBooks(page, size, sortBy));
    }

    // ✅ Retrieve a specific book by ID (Both ADMIN & USER can access)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // ✅ Add a new book (Only ADMIN can create)
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    // ✅ Update an existing book (Only ADMIN can update)
    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Book book = bookService.getBookById(id); // Throws exception if not found
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        book.setPublishedDate(bookDetails.getPublishedDate());

        return ResponseEntity.ok(bookService.saveBook(book));
    }

    // ✅ Delete a book (Only ADMIN can delete)
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.getBookById(id); // Ensure book exists (throws exception if not found)
        bookService.deleteBook(id);

        return ResponseEntity.ok("Book deleted successfully!");
    }
}
