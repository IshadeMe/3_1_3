package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getAll();
}
