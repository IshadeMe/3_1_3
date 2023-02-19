package ru.kata.spring.boot_security.demo.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {
    final UserService userService;
    final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAdminPage(Model model){
        return "admin/panel";
    }

    @GetMapping("/users")
    public String showAll(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("users/{id}/update")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAll());
        return "admin/update";
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult br, @PathVariable("id") int id) {
        if (br.hasErrors()) {
            System.out.println(br.getAllErrors());
            return "admin/update";
        }
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAll());
        return "admin/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            return "admin/create";
        }
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
}
