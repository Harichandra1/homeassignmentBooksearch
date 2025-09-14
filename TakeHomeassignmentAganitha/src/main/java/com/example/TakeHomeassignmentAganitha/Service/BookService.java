package com.example.TakeHomeassignmentAganitha.Service;

import com.example.TakeHomeassignmentAganitha.DTO.BookDTO;
import com.example.TakeHomeassignmentAganitha.DTO.BookResponse;
import com.example.TakeHomeassignmentAganitha.DTO.BookSearchResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final String BASE_URL = "https://openlibrary.org/search.json";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BookService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public BookSearchResult searchBooks(BookDTO request) throws Exception {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("title", request.getTitle());

        if (request.getAuthor() != null && !request.getAuthor().isEmpty()) {
            uriBuilder.queryParam("author", request.getAuthor());
        }

        String finalUrl = uriBuilder.toUriString();
        System.out.println("Final URL -> " + finalUrl);

        String response = restTemplate.getForObject(finalUrl, String.class);
        JsonNode root = objectMapper.readTree(response);

        int totalFound = root.has("numFound") ? root.get("numFound").asInt() : 0;
        JsonNode docs = root.get("docs");

        List<BookResponse> books = new ArrayList<>();
        if (docs != null && docs.isArray()) {
            int count = 0;
            for (JsonNode doc : docs) {
                if (count >= 3) break;

                String title = doc.has("title") ? doc.get("title").asText() : null;

                List<String> authors = new ArrayList<>();
                if (doc.has("author_name")) {
                    doc.get("author_name").forEach(a -> authors.add(a.asText()));
                }

                Integer year = doc.has("first_publish_year") ? doc.get("first_publish_year").asInt() : null;

                String coverUrl = null;
                if (doc.has("cover_i")) {
                    coverUrl = "https://covers.openlibrary.org/b/id/" + doc.get("cover_i").asInt() + "-M.jpg";
                }

                books.add(new BookResponse(title, authors, year, coverUrl));
                count++;
            }
        }

        return new BookSearchResult(totalFound, books);
    }
}
