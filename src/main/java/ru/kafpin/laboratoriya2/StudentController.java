package ru.kafpin.laboratoriya2;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    private final StudentRepository students;

    public StudentController(StudentRepository students) {
        this.students = students;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/schoolboys/main";
    }

    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", students.findAll());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping("/students")
    public String save(@Valid @ModelAttribute Student student, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("students", students.findAll());
            return "students";
        }
        students.save(student);
        return "redirect:/students";
    }
}
