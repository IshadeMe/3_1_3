package ru.kata.spring.boot_security.demo.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService{

    final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Role> getAll() {
        return repository.getAll();
    }
}
