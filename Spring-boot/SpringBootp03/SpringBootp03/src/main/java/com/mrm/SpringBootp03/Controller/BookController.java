package com.mrm.SpringBootp03.Controller;

import com.mrm.SpringBootp03.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private Map<Long, Book> bookDB = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(new ArrayList<>(bookDB.values()));
    }

    @PostMapping
    public ResponseEntity<Book> creatBook(@RequestBody Book book){
        bookDB.put(book.getId(), book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        Book book = bookDB.get(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book){
        Book existing = bookDB.get(id);
        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookDB.put(id, book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/newPrice")
    public ResponseEntity<Book> partialUpdate(@PathVariable long id, @RequestBody double newPrice){
        Book existing = bookDB.get(id);
        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existing.setPrice(newPrice);
        bookDB.put(id, existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable long id){
        Book book = bookDB.get(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookDB.remove(id,book);
        return ResponseEntity.ok(book);
    }

    @Component
    public String result(){
        return "Here all task of the http methods are performed";
    }

}
