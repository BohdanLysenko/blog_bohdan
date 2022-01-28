package home.blog.service.impementation;


import home.blog.dto.CommentDTO;
import home.blog.dto.PostDTO;
import home.blog.model.Comment;
import home.blog.model.Post;
import home.blog.model.User;
import home.blog.repository.CommentRepository;
import home.blog.repository.PostRepository;
import home.blog.repository.UserRepository;
import home.blog.service.CommentService;
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
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    MappingUtils mappingUtils = new MappingUtils();


    @Override
    public Comment createDTO(Long id, String text) {
        CommentDTO commentDTO = new CommentDTO();
        long millis = System.currentTimeMillis();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date date = new Date(millis);
        commentDTO.setText(text);
        commentDTO.setPost_id(id);
        commentDTO.setAuthor_id(((SecurityUser) principal).getId());
        commentDTO.setCreateDate(date);
        commentDTO.setModifiedDate(null);
        Post post = postRepository.findById(commentDTO.getPost_id()).orElseThrow();
        User user = userRepository.findById(commentDTO.getAuthor_id()).orElseThrow();
        Comment comment = mappingUtils.mapToComment(commentDTO);
        comment.setPost(post);
        comment.setAuthor(user);
        return commentRepository.save(comment);
    }

    @Override
    public boolean delete(Long id) {
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CommentDTO> getAllByPostId(Long id) {
        ArrayList<CommentDTO> comments = new ArrayList<>(commentRepository.findAll().stream()
                .map(mappingUtils::mapToCommentDTO)
                .collect(Collectors.toList()));
        ArrayList<CommentDTO> commentsOfPost = new ArrayList<>();
        for (CommentDTO commentDTO : comments) {
            if (commentDTO.getPost_id().equals(id)) {
                commentsOfPost.add(commentDTO);
            }
        }
        return commentsOfPost;
    }

    @Override
    public List<CommentDTO> findAllByPost(PostDTO postDTO) {
        Post post = mappingUtils.mapToPost(postDTO);
        List comments = new ArrayList<>(commentRepository.findAllByPost(post).stream().map(mappingUtils::mapToCommentDTO).collect(Collectors.toList()));
        return comments;
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public Comment update(Long id, Comment comment) {
        return null;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getAuthorById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }
}


