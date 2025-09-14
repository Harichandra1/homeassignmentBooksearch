package com.example.TakeHomeassignmentAganitha.DTO;

import java.util.List;

public class BookSearchResult {
    private Integer totalFound;
    private List<BookResponse> books;

    public BookSearchResult(Integer totalFound, List<BookResponse> books) {
        this.totalFound = totalFound;
        this.books = books;
    }

    // getters and setters
    public Integer getTotalFound() { return totalFound; }
    public void setTotalFound(Integer totalFound) { this.totalFound = totalFound; }

    public List<BookResponse> getBooks() { return books; }
    public void setBooks(List<BookResponse> books) { this.books = books; }
}
