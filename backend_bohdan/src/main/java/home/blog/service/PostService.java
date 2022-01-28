package home.blog.service;

import home.blog.dto.PostDTO;
import home.blog.model.Post;
import home.blog.model.User;

import java.util.List;
import java.util.NoSuchElementException;


public interface PostService {
    Post createDTO(String title, String text);

    PostDTO delete(Long id) throws NoSuchElementException;

    PostDTO getById(Long id) throws NoSuchElementException;

    Post update(Long id, Post post);

    List<PostDTO> getAll();

    User getUserById(Long id);

    List<PostDTO> getUserPosts();

    List<PostDTO> getUserPosts(Long id);

    void deleteAll();

    PostDTO updatePost(Long id, String title, String text);
}