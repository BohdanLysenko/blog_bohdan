package home.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;
    Date modifiedDate;
    Date createDate;
    @JsonIgnore
    @ManyToOne
    Post post;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User author;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", createDate=" + createDate +
                ", post=" + post.getId() +
                ", author=" + author.getId() +
                '}';
    }
}
