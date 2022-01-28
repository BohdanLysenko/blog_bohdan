package home.blog.service;

import home.blog.dto.RoleDTO;
import home.blog.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleService {
    RoleDTO findById(Long id);

    RoleDTO findByName(String name) throws NoSuchElementException;

    List<Role> getAll();
}
