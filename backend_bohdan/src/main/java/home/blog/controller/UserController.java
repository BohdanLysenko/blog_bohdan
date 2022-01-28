package home.blog.controller;

import home.blog.dto.UserDTO;
import home.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(
            value = "/create",
            produces = "application/json")
    public HttpStatus registerUser(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName) {
        userService.create(email, password, firstName, lastName);
        return HttpStatus.OK;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping(
            value = "/update/role/{id}",
            produces = "application/json")
    public UserDTO changeRole(@PathVariable Long id,
                              @RequestParam Long roleId) {
        return userService.updateRole(id, roleId);
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }


    @PreAuthorize("hasAuthority('admin') || hasAuthority('moderator')")
    @DeleteMapping("/delete/all")
    public HttpStatus deleteAll() {
        userService.deleteAll();
        return HttpStatus.OK;
    }


    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/all")
    public List<UserDTO> userList() {
        return new ArrayList<>(userService.getAll());
    }

    @GetMapping("/user")
    public String[] getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }

    @PostMapping("/update/password")
    public String changeCurrentPassword(@RequestParam String password) {
        return userService.changePassword(password);
    }
}