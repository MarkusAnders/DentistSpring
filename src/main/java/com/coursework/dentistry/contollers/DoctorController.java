package com.coursework.dentistry.contollers;

import com.coursework.dentistry.helpers.ImageHelper;
import com.coursework.dentistry.models.Doctor;
import com.coursework.dentistry.models.Specialization;
import com.coursework.dentistry.services.DoctorService;
import com.coursework.dentistry.services.SpecializationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecializationService specializationService;

    @GetMapping("/main")
    public String doctors(Model model) {
        List<Doctor> doctors = (List<Doctor>) doctorService.getAll();
        model.addAttribute("doctors", doctors);
        return "doctors/main";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Specialization> specializations = specializationService.getAll();
        model.addAttribute("specializations", specializations);
        model.addAttribute("doctor", new Doctor());
        return "doctors/create";
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        Optional<Doctor> doctor =
                doctorService.getOneById(id);
        if (doctor.isEmpty()) {
            return "redirect:/doctors/main";
        }
        Optional<Specialization> specialization = specializationService.getOneById(doctor.get().getSpecialization().getId());
        model.addAttribute("doctor", doctor.get());
        model.addAttribute("specialization  Id", specialization.get().getId());
        return "doctors/edit";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        Optional<Doctor> doctors = doctorService.getOneById(id);
        if (doctors.isEmpty()) {
            return "redirect:/doctors/main";
        }
        model.addAttribute("doctor", doctors.get());
        return "doctors/details";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute @Validated Doctor doctor, BindingResult bindingResult, Model model, @RequestParam MultipartFile image,  @RequestParam Long specializationId) throws IOException, URISyntaxException {
        if(bindingResult.hasErrors()) {
            List<Specialization> specializations = specializationService.getAll();
            model.addAttribute("specializations", specializations);
            return "doctors/create";
        }

        String fileName = ImageHelper.generateUniqName() + ".jpeg";
        ImageHelper.loadImage(fileName, "/doctorsPhotos/", image);
        doctor.setPhoto("/doctorsPhotos/" + fileName);

        Optional<Specialization> specialization = specializationService.getOneById(specializationId);
        if (specialization.isEmpty()) {
            return "redirect:/specializations/main";
        }

        doctor.setSpecialization(specialization.get());

        doctorService.save(doctor);
        return "redirect:/doctors/main";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute @Validated Doctor doctor, BindingResult bindingResult, @RequestParam Optional<MultipartFile> image, @RequestParam String post) throws IOException, URISyntaxException {
        if(bindingResult.hasErrors()) {
            return "doctors/edit";
        }

        Optional<Doctor> oldDoctor = doctorService.getOneById(doctor.getId());

        if(oldDoctor.isEmpty()) {
            return "redirect:/doctors/main";
        }

        if(image.get().getSize() != 0) {
            ImageHelper.deleteImage(oldDoctor.get().getPhoto());
            String fileName = ImageHelper.generateUniqName() + ".jpeg";
            ImageHelper.loadImage(fileName, "/doctorsPhotos/", image.get());
            doctor.setPhoto("/doctorsPhotos/" + fileName);
        }
        else {
            doctor.setPhoto(oldDoctor.get().getPhoto());
        }

        doctor.setSpecialization(oldDoctor.get().getSpecialization());
        doctorService.save(doctor);
        return "redirect:/doctors/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws URISyntaxException {
        if (doctorService.existsById(id)) {
            Optional<Doctor> doctor = doctorService.getOneById(id);
            doctorService.deleteById(id);
            ImageHelper.deleteImage(doctor.get().getPhoto());
        }
        return "redirect:/doctors/main";
    }
}
