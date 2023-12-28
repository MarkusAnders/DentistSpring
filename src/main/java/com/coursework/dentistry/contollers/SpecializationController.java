package com.coursework.dentistry.contollers;

import com.coursework.dentistry.models.Specialization;
import com.coursework.dentistry.services.SpecializationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/specializations")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @GetMapping("/main")
    public String index(Model model) {
        List<Specialization> specializations = specializationService.getAll();
        model.addAttribute("specializations", specializations);
        return "specializations/main";
    }

    @GetMapping("/create")
    public String add(Model model){
        model.addAttribute("specialization", new Specialization());
        return "specializations/create";
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        Optional<Specialization> specialization =
                specializationService.getOneById(id);
        if (specialization.isEmpty()) {
            return "redirect:/specializations/main";
        }

        model.addAttribute("specialization", specialization.get());
        return "specializations/edit";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        Optional<Specialization> optionalSpecialization =
                specializationService.getOneById(id);
        if (optionalSpecialization.isEmpty()) {
            return "redirect:/specializations/main";
        }
        model.addAttribute("specialization", optionalSpecialization.get());
        return "specializations/details";
    }


    @PostMapping("/create")
    public String add(@ModelAttribute @Validated Specialization specialization, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "specializations/create";
        }
        specializationService.save(specialization);
        return "redirect:/specializations/main";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute @Validated Specialization specialization, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "specializations/edit";
        }
        Optional<Specialization> oldSpecialization = specializationService.getOneById(specialization.getId());
        if (oldSpecialization.isEmpty()) {
            return "redirect:/specializations/main";
        }
        specialization.setDoctors(oldSpecialization.get().getDoctors());
        specializationService.save(specialization);
        return "redirect:/specializations/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (specializationService.existsById(id)) {
            specializationService.deleteById(id);
        }
        return "redirect:/specializations/main";
    }
}

