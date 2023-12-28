package com.coursework.dentistry.contollers;

import com.coursework.dentistry.models.Doctor;
import com.coursework.dentistry.models.Reception;
import com.coursework.dentistry.models.ProvisionService;
import com.coursework.dentistry.services.DoctorService;
import com.coursework.dentistry.services.ReceptionService;
import com.coursework.dentistry.services.ProvisionServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/receptions")
public class ReceptionController {
    @Autowired
    private ReceptionService receptionService;
    @Autowired
    private ProvisionServiceService provisionServiceService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/main")
    public String receptions(Model model) {
        List<Reception> receptions = (List<Reception>) receptionService.getAll();
        model.addAttribute("receptions", receptions);
        return "receptions/main";
    }

    @GetMapping("/create")
    public String add(Model model){
        List<ProvisionService> provisionServices = provisionServiceService.findAll();
        model.addAttribute("provisionServices", provisionServices);
        model.addAttribute("reception", new Reception());
        return "receptions/create";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute @Validated Reception reception, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            List<ProvisionService> provisionServices = provisionServiceService.findAll();
            model.addAttribute("provisionServices", provisionServices);
            return "receptions/create";
        }

        Reception savedReception = receptionService.saveTo(reception);

        return "redirect:/receptions/doctorChoices/" + savedReception.getId();
    }

    @GetMapping("/doctorChoices/{id}")
    public String doctorChoices(Model model, @PathVariable("id") Long id){

        Optional<Reception> optionalReception = receptionService.getOneById(id);

        if (optionalReception.isEmpty()){
            return "redirect:/receptions/main";
        }

        Reception reception = optionalReception.get();

        List<Doctor> doctors = reception.getProvisionService().getDoctors();

        model.addAttribute("reception", reception);
        model.addAttribute("doctors", doctors);

        return "receptions/doctorChoices";
    }

    @PostMapping("/doctorChoices/{id}")
    public String doctorChoices(@ModelAttribute Reception reception){
        Optional<Reception> optionalReception = receptionService.getOneById(reception.getId());

        if(optionalReception.isEmpty()){
            return "redirect:/receptions/main";
        }

        Reception receptionToSave = optionalReception.get();
        receptionToSave.setDoctor(reception.getDoctor());
        Reception savedReception = receptionService.saveTo(receptionToSave);

        return "redirect:/receptions/main";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        Optional<Reception> reception = receptionService.getOneById(id);

        if (reception.isEmpty()) {
            // вернуть страницу "не найдено"
            return "redirect:/receptions/main";
        }

        model.addAttribute("reception", reception.get());

        return "receptions/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        if (receptionService.existsById(id)) {
            Optional<Reception> reception = receptionService.getOneById(id);
            receptionService.deleteById(id);
        }

        return "redirect:/receptions/main";
    }
}
