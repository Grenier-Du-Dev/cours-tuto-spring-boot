package com.blog.app.service;

import com.blog.app.model.Role;
import com.blog.app.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;



    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }


    public Role getRoleByName(String  name){
        return roleRepository.findByRoleName(name).orElse(null);
    }

    public Role saveRole(Role role){
        return  roleRepository.save(role);
    }


    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }


    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

}
