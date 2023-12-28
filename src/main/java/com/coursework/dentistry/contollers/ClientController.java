package com.coursework.dentistry.contollers;

import com.coursework.dentistry.models.Client;
import com.coursework.dentistry.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/main")
    public String clients(Model model) {
        List<Client> clients = (List<Client>) clientService.getAll();
        model.addAttribute("clients", clients);
        return "clients/main";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        Optional<Client> clients = clientService.getOneById(id);
        if (clients.isEmpty()) {
            return "redirect:/clients/main";
        }
        model.addAttribute("client", clients.get());
        return "clients/details";
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Client> client = clientService.getOneById(id);
        if (client.isEmpty()) {
            return "redirect:/clients/main";
        }
        model.addAttribute("client", client.get());
        return "clients/edit";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute Client client, Model model){
        clientService.save(client);
        return "redirect:/clients/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        if (clientService.existsById(id)) {
            clientService.deleteById(id);
        }
        return "redirect:/clients/main";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("client", new Client());
        return "clients/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Client client, Model model){
        clientService.save(client);
        return "redirect:/clients/main";
    }
}
