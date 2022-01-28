package home.blog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String text;
    @OneToMany(mappedBy = "post")
    List<Comment> comments;
    Date createDate;
    Date modifiedDate;
    @OneToMany(mappedBy = "post")
    List<Tag> tags;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User author;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", comments=" + comments +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", tags=" + tags +
                ", author=" + author.getId() +
                '}';
    }
}
