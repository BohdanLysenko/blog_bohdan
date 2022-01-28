package home.blog.dto;

import lombok.Data;

@Data
public class TagDTO {
    Long id;
    String text;
    Long comment_id;
}
