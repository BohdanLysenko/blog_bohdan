package home.blog.service.impementation;


import home.blog.dto.PostDTO;
import home.blog.model.Post;
import home.blog.model.User;
import home.blog.repository.PostRepository;
import home.blog.repository.UserRepository;
import home.blog.service.CommentService;
import home.blog.service.PostService;
import home.blog.service.SpringConfig.SecurityUser;
import home.blog.utils.MappingUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CommentService commentService;

    MappingUtils mappingUtils = new MappingUtils();

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, CommentService commentService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentService = commentService;
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Override
    public PostDTO updatePost(Long id, String title, String text) {
        PostDTO updatedPost = getById(id);
        Post post = postRepository.findById(id).orElseThrow();
        if (title != null) {
            post.setTitle(title);
        }
        if (text != null) {
            post.setText(text);
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        post.setModifiedDate(date);
        postRepository.save(post);
        return updatedPost;
    }

    @Override
    public Post createDTO(String title, String text) {
        PostDTO postDTO = new PostDTO();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        postDTO.setAuthor_id(((SecurityUser) principal).getId());
        postDTO.setTitle(title);
        postDTO.setText(text);
        postDTO.setCreateDate(date);
        postDTO.setModifiedDate(null);
        postDTO.setComments(new ArrayList<>());
        postDTO.setTags(new ArrayList<>());
        Post post = mappingUtils.mapToPost(postDTO);
        post.setAuthor(userRepository.findById(postDTO.getAuthor_id()).orElseThrow());
        return postRepository.save(post);
    }

    @Override
    public List<PostDTO> getUserPosts() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((SecurityUser) principal).getId();
        ArrayList<PostDTO> posts2 = new ArrayList<>(postRepository.findAll().stream()
                .map(mappingUtils::mapToPostDTO)
                .collect(Collectors.toList()));
        ArrayList<PostDTO> postsOfAuthor = new ArrayList<>();
        for (PostDTO postDto : posts2) {
            if (postDto.getAuthor_id().equals(id)) {
                postsOfAuthor.add(postDto);
            }
        }
        return postsOfAuthor;
    }

    @Override
    public List<PostDTO> getUserPosts(Long id) {
        ArrayList<PostDTO> posts2 = new ArrayList<>(postRepository.findAll().stream()
                .map(mappingUtils::mapToPostDTO)
                .collect(Collectors.toList()));
        ArrayList<PostDTO> postsOfAuthor = new ArrayList<>();
        for (PostDTO postDto : posts2) {
            if (postDto.getAuthor_id().equals(id)) {
                postsOfAuthor.add(postDto);
            }
        }
        return postsOfAuthor;
    }

    @Override
    public PostDTO delete(Long id) {
        PostDTO deletedPost = getById(id);
        postRepository.deleteById(id);
        return deletedPost;
    }

    @Override
    public PostDTO getById(Long id) {
        return mappingUtils.mapToPostDTO(postRepository.findById(id).orElseThrow());
    }

    @Override
    public Post update(Long id, Post post) {
        return null;
    }

    @Override
    public List<PostDTO> getAll() {
        ArrayList<PostDTO> posts = new ArrayList<>(postRepository.findAll().stream()
                .map(mappingUtils::mapToPostDTO)
                .collect(Collectors.toList()));
        return posts;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
