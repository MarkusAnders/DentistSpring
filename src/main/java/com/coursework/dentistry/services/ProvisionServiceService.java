package com.coursework.dentistry.services;

import com.coursework.dentistry.models.Doctor;
import com.coursework.dentistry.models.ProvisionService;
import com.coursework.dentistry.repositories.DoctorRepository;
import com.coursework.dentistry.repositories.ProvisionServiceRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvisionServiceService {
    @Autowired
    private ProvisionServiceRepository provisionServiceRepository;
    private DoctorRepository doctorRepository;

    public boolean existsById(Long id) {
        return provisionServiceRepository.existsById(id);
    }

    public List<ProvisionService> findAll() {
        return findAll(null);
    }

    public List<ProvisionService> findAll(@Nullable String namePart) {
        if (namePart == null) {
            return (List<ProvisionService>) provisionServiceRepository.findAll();
        }
        return (List<ProvisionService>) provisionServiceRepository.findByNameContainingIgnoreCase(namePart);
    }

    public Optional<ProvisionService> findById(Long id) {
        return provisionServiceRepository.findById(id);
    }

    public ProvisionService save(ProvisionService provisionService) {
        return provisionServiceRepository.save(provisionService);
    }

    public void deleteById(Long id) {
        provisionServiceRepository.deleteById(id);
    }

//    @Transactional
//    public void delete(ProvisionService provisionService) {
//        provisionServiceRepository.deleteFromDoctorsProvisionServicesByProvisionServiceId(provisionService.getId());
//        provisionServiceRepository.delete(provisionService);
//    }
//
//    @Transactional
//    public void deleteById(Long id) {
//        provisionServiceRepository.deleteFromDoctorsProvisionServicesByProvisionServiceId(id);
//        provisionServiceRepository.deleteById(id);
//    }

}
