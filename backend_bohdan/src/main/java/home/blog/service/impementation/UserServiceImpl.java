package home.blog.service.impementation;


import home.blog.dto.UserDTO;
import home.blog.model.Role;
import home.blog.model.User;
import home.blog.repository.RoleRepository;
import home.blog.repository.UserRepository;
import home.blog.service.RoleService;
import home.blog.service.SpringConfig.SecurityUser;
import home.blog.service.UserService;
import home.blog.utils.MappingUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final RoleService roleService;

    private final MappingUtils mappingUtils;

    public UserServiceImpl(UserRepository userRepository, MappingUtils mappingUtils, RoleRepository roleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.mappingUtils = mappingUtils;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @Override
    public User create(String email, String password, String firstName, String lastName) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setRole(roleService.findByName("blogger"));
        User user = mappingUtils.mapToUser(userDTO);
        return userRepository.save(user);
    }

    @Override
    public UserDTO delete(Long id) {
        UserDTO deletedUser = mappingUtils.mapToUserDTO(userRepository.findById(id).orElseThrow());
        userRepository.deleteById(id);
        return deletedUser;
    }

    @Override
    public UserDTO delete(UserDTO userDTO) {
        UserDTO deletedUser = userDTO;
        User user = mappingUtils.mapToUser(userDTO);
        userRepository.delete(user);
        return deletedUser;
    }

    @Override
    public UserDTO getById(Long id) {
        UserDTO userDTO = mappingUtils.mapToUserDTO(userRepository.findById(id).orElseThrow());
        return userDTO;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User updateUserNameById(Long id, String name, String lastName) {
        Optional<User> userFromDB = userRepository.findById(id);
        if (userFromDB.isPresent()) {
            User updatedUser = userFromDB.get();
            updatedUser.setName(name);
            updatedUser.setLastName(lastName);
            return userRepository.save(updatedUser);
        } else {
            User newUser = new User();
            newUser.setName(name);
            newUser.setLastName(lastName);
            return userRepository.save(newUser);
        }
    }

    @Override
    public UserDTO update(Long id) {
        UserDTO userDTO = mappingUtils.mapToUserDTO(userRepository.findById(id).orElseThrow());
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(mappingUtils::mapToUserDTO)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO updateRole(Long id, Long roleId) {
        UserDTO oldUser = getById(id);
        Optional<User> userFromDB = userRepository.findById(id);
        User user = userFromDB.get();
        Optional<Role> role = roleRepository.findById(roleId);
        Role newRole = role.get();
        user.setRole(newRole);
        userRepository.save(user);
        return oldUser;
    }

    @Override
    public String changePassword(String password) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((SecurityUser) principal).getId();
        Optional<User> userFromDB = userRepository.findById(id);
        User user = userFromDB.get();
        String oldPassword = user.getPassword();
        user.setPassword(password);
        userRepository.save(user);
        return oldPassword;
    }

    @Override
    public String[] getCurrentUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = " ID: " + ((SecurityUser) principal).getId();
        String username = " Name: " + ((SecurityUser) principal).getUsername();
        String password = " Password: " + ((SecurityUser) principal).getPassword();
        String role = " Role: " + ((SecurityUser) principal).getRole();
        String[] userInfo = {id, username, password, role};
        return userInfo;
    }
}
