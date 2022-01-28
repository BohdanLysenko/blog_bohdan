package home.blog.controller;

import home.blog.dto.PostDTO;
import home.blog.repository.PostRepository;
import home.blog.service.CommentService;
import home.blog.service.PostService;
import home.blog.service.TagService;
import home.blog.service.UserService;
import home.blog.utils.MappingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {

    final PostRepository postRepository;

    final PostService postService;

    final UserService userService;

    final CommentService commentService;

    final TagService service;

    MappingUtils mappingUtils = new MappingUtils();

    public PostController(PostRepository postRepository, PostService postService, UserService userService, CommentService commentService, TagService service) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
        this.service = service;
    }

    @GetMapping("/all")
    List<PostDTO> getAllPosts() {
        return postService.getAll();
    }

    @PreAuthorize("hasAuthority('admin') || hasAuthority('moderator') || hasAnyAuthority('blogger')")
    @PostMapping(
            value = "/create",
            produces = "application/json")
    public HttpStatus createPost(@RequestParam String title,
                                 @RequestParam String text) {
        postService.createDTO(title, text);
        return HttpStatus.OK;
    }

    @PreAuthorize("hasAuthority('admin') || hasAuthority('moderator')")
    @GetMapping("/user/{id}")
    public List<PostDTO> getPostByUserId(@PathVariable Long id) {
        return postService.getUserPosts(id);
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @DeleteMapping("/delete/all")
    public HttpStatus deleteAll() {
        postService.deleteAll();
        return HttpStatus.OK;
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public PostDTO deletePostById(@PathVariable Long id) {
        return postService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public PostDTO editPostById(@PathVariable Long id,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) String text) {
        return postService.updatePost(id, title, text);
    }

    @GetMapping("/user/all")
    public List<PostDTO> getUserPosts() {
        return postService.getUserPosts();
    }


}
