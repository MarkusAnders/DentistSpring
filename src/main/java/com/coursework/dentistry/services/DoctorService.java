package com.coursework.dentistry.services;

import com.coursework.dentistry.models.Doctor;
import com.coursework.dentistry.repositories.DoctorRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public List<Doctor> getAll(){
        return (List<Doctor>) doctorRepository.findAll();
    }

    public List<Doctor> findAll(@Nullable String namePart) {
        if (namePart == null) {
            return (List<Doctor>)doctorRepository.findAll();
        }
        return (List<Doctor>)doctorRepository.findBySurnameContainingIgnoreCase(namePart);
    }

    public Optional<Doctor> getOneById(Long id) {
        return doctorRepository.findById(id);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return doctorRepository.existsById(id);
    }
}
