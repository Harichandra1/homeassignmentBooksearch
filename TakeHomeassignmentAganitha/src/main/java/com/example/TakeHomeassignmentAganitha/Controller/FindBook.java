package com.example.TakeHomeassignmentAganitha.Controller;

import com.example.TakeHomeassignmentAganitha.DTO.BookDTO;
import com.example.TakeHomeassignmentAganitha.DTO.BookResponse;
import com.example.TakeHomeassignmentAganitha.DTO.BookSearchResult;
import com.example.TakeHomeassignmentAganitha.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/Book")
public class FindBook {

    @Autowired
    private final BookService bookService;

    public FindBook(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/search")
    public BookSearchResult searchBooks(@Validated @RequestBody BookDTO request) throws Exception {
        return bookService.searchBooks(request);
    }

    @GetMapping("/j")
    public String fdd(){
        return "hello";
    }

}
