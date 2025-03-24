package com.Backend.BookStoreMgt.Service;

import com.Backend.BookStoreMgt.Entity.Book;
import com.Backend.BookStoreMgt.Exception.BookNotFoundException;
import com.Backend.BookStoreMgt.Repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> getAllBooks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        Page<Book> bookPage = bookRepository.findAll(pageable);

        // ✅ If the requested page is out of range, return the last available page
        if (bookPage.isEmpty() && page > 0) {
            int lastPage = Math.max(0, bookPage.getTotalPages() - 1); // Ensure it's non-negative
            pageable = PageRequest.of(lastPage, size, Sort.by(Sort.Direction.ASC, sortBy));
            bookPage = bookRepository.findAll(pageable);
        }

        return bookPage;
    }}