package home.blog.controller;

import home.blog.model.Comment;
import home.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public Comment createPostComments(@RequestParam Long id,
                                      @RequestParam String text){

        return commentService.createDTO(id, text);
    }

    @GetMapping("/{id}")
    Object getAllPostComments(@PathVariable Long id) {
        return commentService.getAllByPostId(id);
    }

}
