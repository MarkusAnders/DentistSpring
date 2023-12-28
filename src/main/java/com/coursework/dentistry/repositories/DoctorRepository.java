package com.coursework.dentistry.repositories;

import com.coursework.dentistry.models.Doctor;
import com.coursework.dentistry.models.ProvisionService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Iterable<Doctor> findBySurnameContainingIgnoreCase(String part);

}
