package com.coursework.dentistry.repositories;

import com.coursework.dentistry.models.ProvisionService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProvisionServiceRepository extends CrudRepository<ProvisionService, Long> {
    Iterable<ProvisionService> findByNameContainingIgnoreCase(String part);

}
