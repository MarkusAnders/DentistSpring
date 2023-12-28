package com.coursework.dentistry.services;

import com.coursework.dentistry.models.Specialization;
import com.coursework.dentistry.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    public void save(Specialization specialization){
        specializationRepository.save(specialization);
    }

    public List<Specialization> getAll(){
        return (List<Specialization>) specializationRepository.findAll();
    }

    public Optional<Specialization> getOneById(Long id) {
        return specializationRepository.findById(id);
    }

    public void deleteById(Long id) {
        specializationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return specializationRepository.existsById(id);
    }
}
