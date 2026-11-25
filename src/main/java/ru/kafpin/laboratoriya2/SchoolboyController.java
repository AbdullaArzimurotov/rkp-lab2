package ru.kafpin.laboratoriya2;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/schoolboys")
public class SchoolboyController {
    private final SchoolboyRepository schoolboys;

    public SchoolboyController(SchoolboyRepository schoolboys) {
        this.schoolboys = schoolboys;
    }

    @GetMapping({"", "/main"})
    public String mainPage(Model model) {
        model.addAttribute("schoolboys", schoolboys.findAll());
        return "main";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("selectedSchoolboy", find(id));
        return "details";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("schoolboy", new Schoolboy());
        return "form";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("schoolboy", find(id));
        return "form";
    }

    @PostMapping("/save")
    public String saveSchoolboy(@Valid @ModelAttribute Schoolboy schoolboy, BindingResult errors) {
        if (schoolboy.getId() != null) {
            find(schoolboy.getId());
        }
        if (errors.hasErrors()) {
            return "form";
        }
        schoolboys.save(schoolboy);
        return "redirect:/schoolboys/main";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        schoolboys.delete(find(id));
        return "redirect:/schoolboys/main";
    }

    private Schoolboy find(Long id) {
        return schoolboys.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Школьник не найден."));
    }
}
