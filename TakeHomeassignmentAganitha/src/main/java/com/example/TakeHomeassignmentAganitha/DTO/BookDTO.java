package com.example.TakeHomeassignmentAganitha.DTO;

import jakarta.validation.constraints.NotBlank;

public class BookDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String author;  // optional

    // getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
