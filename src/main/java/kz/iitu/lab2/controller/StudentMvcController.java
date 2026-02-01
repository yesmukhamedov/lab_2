package kz.iitu.lab2.controller;

import jakarta.validation.Valid;
import kz.iitu.lab2.dto.StudentForm;
import kz.iitu.lab2.entity.Student;
import kz.iitu.lab2.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentMvcController {

    private final StudentService studentService;

    public StudentMvcController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "students/create";
    }

    @PostMapping
    public String create(@Valid StudentForm studentForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "students/create";
        }
        studentService.create(toEntity(studentForm));
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Student student = studentService.getById(id);
        StudentForm form = new StudentForm();
        form.setFullName(student.getFullName());
        form.setEmail(student.getEmail());
        form.setEnrollmentYear(student.getEnrollmentYear());
        model.addAttribute("student", student);
        model.addAttribute("studentForm", form);
        return "students/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid StudentForm studentForm, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentService.getById(id));
            return "students/edit";
        }
        studentService.update(id, toEntity(studentForm));
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }

    private Student toEntity(StudentForm form) {
        Student student = new Student();
        student.setFullName(form.getFullName());
        student.setEmail(form.getEmail());
        student.setEnrollmentYear(form.getEnrollmentYear());
        return student;
    }
}
