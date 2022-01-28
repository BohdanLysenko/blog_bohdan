package home.blog.service;

import home.blog.dto.UserDTO;
import home.blog.model.User;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    User create(String email, String password, String firstName, String lastName);

    UserDTO delete(Long id) throws NoSuchElementException;

    UserDTO delete(UserDTO userDTO);

    void deleteAll();

    UserDTO getById(Long id) throws NoSuchElementException;

    UserDTO update(Long id);

    List<UserDTO> getAll();

    User updateUserNameById(Long id, String name, String lastName);

    UserDTO updateRole(Long UserId, Long roleId);

    String changePassword(String password);

    String[] getCurrentUserInfo();
}
