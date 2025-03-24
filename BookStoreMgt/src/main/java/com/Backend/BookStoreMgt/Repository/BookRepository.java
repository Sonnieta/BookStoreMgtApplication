package com.Backend.BookStoreMgt.Repository;


import com.Backend.BookStoreMgt.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
