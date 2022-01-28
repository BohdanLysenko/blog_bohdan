package home.blog.service;

import home.blog.dto.CommentDTO;
import home.blog.dto.PostDTO;
import home.blog.model.Comment;

import java.util.List;
import java.util.NoSuchElementException;

public interface CommentService {
    Comment createDTO(Long id,String text);

    boolean delete(Long id);

    Comment findById(Long id);

    Comment update(Long id, Comment comment);

    List<Comment> getAll();

    Comment getAuthorById(Long id);

    List<CommentDTO> getAllByPostId(Long id) throws NoSuchElementException;

    List<CommentDTO> findAllByPost(PostDTO postDTO);
}
