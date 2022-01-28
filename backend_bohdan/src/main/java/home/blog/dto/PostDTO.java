package home.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class PostDTO {
    @JsonProperty("postId")
    Long id;
    @JsonProperty("title")
    String title;
    @JsonProperty("text")
    String text;
    @JsonProperty("authorId")
    Long Author_id;

    @JsonProperty("createdDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createDate;
    @JsonProperty("modifiedDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Date modifiedDate;
    @JsonProperty("tags")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<TagDTO> tags;
    @JsonProperty("comments")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<CommentDTO> comments;


    @Override
    public String toString() {
        return "PostDTO{" +
                ", ID of Author" + Author_id +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", comments=" + comments +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", tags=" + tags +
                '}';
    }


}
