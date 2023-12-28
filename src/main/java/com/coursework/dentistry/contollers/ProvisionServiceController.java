package com.coursework.dentistry.contollers;

import com.coursework.dentistry.helpers.ImageHelper;
import com.coursework.dentistry.models.Doctor;
import com.coursework.dentistry.models.ProvisionService;
import com.coursework.dentistry.services.DoctorService;
import com.coursework.dentistry.services.ProvisionServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/provisionServices")
public class ProvisionServiceController {

    @Autowired
    private ProvisionServiceService provisionServiceService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/main")
    public String index(Model model) {
        model.addAttribute("provisionServices", provisionServiceService.findAll());
        return "provisionServices/main";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        Optional<ProvisionService> provisionService = provisionServiceService.findById(id);

        if (provisionService.isEmpty()) {
            // вернуть страницу "не найдено"
            return "redirect:/provisionServices/main";
        }

        model.addAttribute("provisionService", provisionService.get());

        return "provisionServices/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("provisionService", new ProvisionService());
        return "provisionServices/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Validated ProvisionService provisionService, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "provisionServices/edit";
        }
        ProvisionService savedProvisionService = provisionServiceService.save(provisionService);
        return "redirect:/provisionServices/doctorChoices/" + savedProvisionService.getId();
    }

    @GetMapping("/doctorChoices/{id}")
    public String doctorChoices(Model model,
                               @PathVariable("id") Long id,
                               @RequestParam(value = "filter", required = false) String filter) {
        Optional<ProvisionService> provisionService = provisionServiceService.findById(id);

        if (provisionService.isEmpty()) {
            // вернуть страницу "не найдено"
            return "redirect:/provisionServices/main";
        }

        model.addAttribute("provisionService", provisionService.get());
        model.addAttribute("doctors", doctorService.findAll(filter));

        return "provisionServices/doctorChoices";
    }

    @PostMapping("/doctorChoices/{id}")
    public String doctorChoices(@ModelAttribute("provisionService") ProvisionService provisionServiceDoctors) {
        Optional<ProvisionService> optionalProvisionService = provisionServiceService.findById(provisionServiceDoctors.getId());

        if (optionalProvisionService.isEmpty()) {
            return "redirect:/provisionServices/main";
        }

        ProvisionService provisionServiceToSave = optionalProvisionService.get();

        provisionServiceToSave.setDoctors(provisionServiceDoctors.getDoctors());

        provisionServiceService.save(provisionServiceToSave);

        return "redirect:/provisionServices/main";
    }


    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        Optional<ProvisionService> provisionService =
                provisionServiceService.findById(id);
        if (provisionService.isEmpty()) {
            return "redirect:/provisionServices/main";
        }
        model.addAttribute("provisionService", provisionService.get());
        return "provisionServices/edit";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute @Validated ProvisionService provisionService, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "provisionServices/edit";
        }
        Optional<ProvisionService> oldProvisionService = provisionServiceService.findById(provisionService.getId());
        if (oldProvisionService.isEmpty()) {
            return "redirect:/provisionServices/main";
        }
        provisionService.setDoctors(oldProvisionService.get().getDoctors());
        provisionServiceService.save(provisionService);
        return "redirect:/provisionServices/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        if (provisionServiceService.existsById(id)) {
            Optional<ProvisionService> provisionService = provisionServiceService.findById(id);
            provisionServiceService.deleteById(id);
        }

        return "redirect:/provisionServices/main";
    }
}
