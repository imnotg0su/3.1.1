package com.gubaevpavel.spring.boot311.controller;

import com.gubaevpavel.spring.boot311.model.User;
import com.gubaevpavel.spring.boot311.service.RoleService;
import com.gubaevpavel.spring.boot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String allUsers (Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.userById(id));
        model.addAttribute("roles", userService.userById(id).getRoles());
        return "user";
    }

    @GetMapping("/newUser")
    public String newUser (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.allRoles());
        return "newUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute ("user") User user, @RequestParam(value = "roleBox") String [] roleBox) {
        userService.add(user);
        user.setRoles(roleService.getRoleSet(roleBox));
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable int id) {
        model.addAttribute("user", userService.userById(id));
        model.addAttribute("roles", roleService.allRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser (@ModelAttribute User user, @RequestParam(value = "roleBox") String [] roleBox) {
        user.setRoles(roleService.getRoleSet(roleBox));
        userService.edit(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable int id) {
        userService.delete(id);
        return "redirect:/";
    }
}

