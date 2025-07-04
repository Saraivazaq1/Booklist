package dev.saraiva.Booklist.Client;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import dev.saraiva.Booklist.Model.BookModel;

@Configuration
public class GoogleBooksApiClient {

    private final RestTemplate restTemplate;

    public GoogleBooksApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookModel searchBook(String title) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + title;
        Map<String, Object> response = restTemplate.getForObject(url.replace(" ", "%20"), Map.class);
        if (response == null || !response.containsKey("items")) return null;

        List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
        if (items.isEmpty() || items == null) return null;

        Map<String, Object> firstItem = items.get(0);
        Map<String, Object> volumeInfoMap = (Map<String, Object>) firstItem.get("volumeInfo");
        if (volumeInfoMap == null) return null;

        String bookTitle = (String) volumeInfoMap.get("title");
        List<String> authorsList = (List<String>) volumeInfoMap.get("authors");

        BookModel bookModel = new BookModel();
        bookModel.setTitle(bookTitle);
        bookModel.setAuthor(authorsList);
        System.out.println(bookModel.toString());

        return bookModel;
    }
}
