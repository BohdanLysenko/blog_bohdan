package home.blog.utils;

import home.blog.dto.*;
import home.blog.model.*;
import home.blog.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Transactional
@Service
//спросить через new или service
public class MappingUtils {


    public RoleDTO mapToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }

    public Role mapToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }


    public TagDTO mapToTagDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setText(tag.getText());
        return tagDTO;
    }

    public Tag mapToTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setText(tagDTO.getText());
        return tag;
    }

    public Comment mapToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setModifiedDate(commentDTO.getModifiedDate());
        comment.setCreateDate(commentDTO.getCreateDate());
        return comment;
    }


    public CommentDTO mapToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        commentDTO.setModifiedDate(comment.getModifiedDate());
        commentDTO.setCreateDate(comment.getCreateDate());
        commentDTO.setPost_id(comment.getPost().getId());
        commentDTO.setAuthor_id(comment.getAuthor().getId());
        return commentDTO;
    }


    public UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(mapToRoleDTO(user.getRole()));
        return userDTO;
    }

    public User mapToUser(UserDTO userDTO) {
        User user = new User();
        // user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(mapToRole(userDTO.getRole()));
        return user;
    }

    public PostDTO mapToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setText(post.getText());
        postDTO.setComments(post.getComments().stream().map(this::mapToCommentDTO).collect(Collectors.toList()));
        postDTO.setCreateDate(post.getCreateDate());
        postDTO.setModifiedDate(post.getModifiedDate());
        postDTO.setTags(post.getTags().stream().map(this::mapToTagDTO).collect(Collectors.toList()));
        postDTO.setAuthor_id(post.getAuthor().getId());
        return postDTO;
    }

    public Post mapToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setComments(postDTO.getComments().stream().map(this::mapToComment).collect(Collectors.toList()));
        post.setCreateDate(postDTO.getCreateDate());
        post.setModifiedDate(postDTO.getModifiedDate());
        post.setTags(postDTO.getTags().stream().map(this::mapToTag).collect(Collectors.toList()));
        return post;
    }

}