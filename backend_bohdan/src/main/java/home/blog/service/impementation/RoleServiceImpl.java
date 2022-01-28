package home.blog.service.impementation;


import home.blog.dto.RoleDTO;
import home.blog.model.Role;
import home.blog.repository.RoleRepository;
import home.blog.service.RoleService;
import home.blog.utils.MappingUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final MappingUtils mappingUtils;

    private final RoleRepository roleRepository;

    RoleServiceImpl(RoleRepository roleRepository, MappingUtils mappingUtils) {
        this.roleRepository = roleRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public RoleDTO findById(Long id) {
        RoleDTO roleDTO = mappingUtils.mapToRoleDTO(roleRepository.findById(id).orElseThrow());
        return roleDTO;
    }

    @Override
    public RoleDTO findByName(String name) {
        RoleDTO roleDTO = mappingUtils.mapToRoleDTO(roleRepository.findByRoleName(name));
        return roleDTO;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public List<Role> getAllSortedByDescOrder(){
        return roleRepository.findAll(Sort.by(Sort.Direction.DESC,"roleName"));
    }
}
