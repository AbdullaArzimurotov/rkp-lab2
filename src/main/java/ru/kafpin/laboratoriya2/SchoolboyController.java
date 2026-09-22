package ru.kafpin.laboratoriya2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/schoolboys")
public class SchoolboyController {

    @Autowired
    private SchoolboyRepository schoolboyRepository;

    // 1. Barcha o'quvchilarni jadvalda ko'rsatish
    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("schoolboys", schoolboyRepository.findAll());
        return "main";
    }

    // 2. Bitta o'quvchi haqida batafsil ma'lumot
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        Optional<Schoolboy> schoolboy = schoolboyRepository.findById(id);
        if (schoolboy.isEmpty()) {
            return "redirect:/schoolboys/main";
        }
        model.addAttribute("selectedSchoolboy", schoolboy.get());
        return "details";
    }

    // 3. Yangi o'quvchi qo'shish formasi
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("schoolboy", new Schoolboy());
        return "form";
    }

    // 4. Yangi ma'lumotni yoki tahrirni saqlash
    @PostMapping("/save")
    public String saveSchoolboy(@ModelAttribute Schoolboy schoolboy) {
        schoolboyRepository.save(schoolboy);
        return "redirect:/schoolboys/main";
    }

    // 5. O'zgartirish (tahrirlash) formasi
    @GetMapping("/update/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Optional<Schoolboy> schoolboy = schoolboyRepository.findById(id);
        if (schoolboy.isEmpty()) {
            return "redirect:/schoolboys/main";
        }
        model.addAttribute("schoolboy", schoolboy.get());
        return "form";
    }

    // 6. O'quvchini bazadan o'chirish
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (schoolboyRepository.existsById(id)) {
            schoolboyRepository.deleteById(id);
        }
        return "redirect:/schoolboys/main";
    }
}