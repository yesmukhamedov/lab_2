package kz.iitu.lab2.controller;

import kz.iitu.lab2.model.User;
import kz.iitu.lab2.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String listUsers(@RequestParam(name = "editId", required = false) Long editId, Model model) {
        User formUser = new User();
        boolean editMode = false;

        if (editId != null) {
            User existingUser = userRepository.findById(editId).orElse(null);
            if (existingUser != null) {
                formUser = existingUser;
                editMode = true;
            }
        }

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("formUser", formUser);
        model.addAttribute("editMode", editMode);
        return "users";
    }

    @PostMapping("/users")
    public String createUser(User formUser) {
        formUser.setId(null);
        userRepository.save(formUser);
        return "redirect:/users";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, User formUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return "redirect:/users";
        }

        existingUser.setName(formUser.getName());
        existingUser.setAge(formUser.getAge());
        existingUser.setStatus(formUser.getStatus());
        userRepository.save(existingUser);
        return "redirect:/users";
    }

    @PatchMapping("/users/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam("status") String status) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setStatus(status);
            userRepository.save(existingUser);
        }
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
