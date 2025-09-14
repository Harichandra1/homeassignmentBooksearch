package com.example.TakeHomeassignmentAganitha.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Data
public class BookResponse {
    private String title;
    private List<String> authors;
    private Integer firstPublishYear;
    private String coverImageUrl;

    public BookResponse(String title, List<String> authors, Integer firstPublishYear, String coverImageUrl) {
        this.title = title;
        this.authors = authors;
        this.firstPublishYear = firstPublishYear;
        this.coverImageUrl = coverImageUrl;
    }


}
