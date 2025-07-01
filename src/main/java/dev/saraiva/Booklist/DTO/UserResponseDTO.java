package dev.saraiva.Booklist.DTO;

import java.util.List;

import dev.saraiva.Booklist.Model.BookModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private List<BookModel> books;
}
