package com.coursework.dentistry.contollers;

import com.coursework.dentistry.models.Client;
import com.coursework.dentistry.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
