package kz.iitu.lab2.web;

import java.util.List;
import kz.iitu.lab2.domain.User;
import kz.iitu.lab2.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private static final List<String> STATUSES = List.of("ACTIVE", "INACTIVE", "BLOCKED");

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("statuses", STATUSES);
        return "user";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @PutMapping("/users/{id}")
    public String update(@PathVariable Long id, @ModelAttribute User formUser) {
        userRepository.findById(id).ifPresent(existing -> {
            existing.setName(formUser.getName());
            existing.setAge(formUser.getAge());
            existing.setStatus(formUser.getStatus());
            userRepository.save(existing);
        });
        return "redirect:/users";
    }

    @PatchMapping("/users/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        userRepository.findById(id).ifPresent(existing -> {
            existing.setStatus(status);
            userRepository.save(existing);
        });
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
        return "redirect:/users";
    }
}
