package com.coursework.dentistry.services;

import com.coursework.dentistry.models.ProvisionService;
import com.coursework.dentistry.models.Reception;
import com.coursework.dentistry.repositories.DoctorRepository;
import com.coursework.dentistry.repositories.ReceptionRepository;
import com.coursework.dentistry.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptionService {
    @Autowired
    private ReceptionRepository receptionRepository;

    public void save(Reception reception) {
        receptionRepository.save(reception);
    }

    public Reception saveTo(Reception reception){
        return  receptionRepository.save(reception);
    }

    public List<Reception> getAll(){
        return (List<Reception>) receptionRepository.findAll();
    }

    public Optional<Reception> getOneById(Long id) {
        return receptionRepository.findById(id);
    }

    public void deleteById(Long id) {
        receptionRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return receptionRepository.existsById(id);
    }
}
