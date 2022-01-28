package home.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class CommentDTO {
    @JsonIgnore
    Long id;
    @JsonProperty("text")
    String text;
    @JsonProperty("modifiedDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Date modifiedDate;
    @JsonProperty("createdDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createDate;
    @JsonProperty("postId")
    Long post_id;
    @JsonProperty("authorId")
    Long author_id;
}
