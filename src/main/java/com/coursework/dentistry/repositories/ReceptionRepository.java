package com.coursework.dentistry.repositories;

import com.coursework.dentistry.models.ProvisionService;
import com.coursework.dentistry.models.Reception;
import org.springframework.data.repository.CrudRepository;

public interface ReceptionRepository extends CrudRepository<Reception, Long> {
}
