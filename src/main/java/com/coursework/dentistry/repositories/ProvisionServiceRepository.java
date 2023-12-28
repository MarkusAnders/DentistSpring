package com.coursework.dentistry.repositories;

import com.coursework.dentistry.models.ProvisionService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProvisionServiceRepository extends CrudRepository<ProvisionService, Long> {
    Iterable<ProvisionService> findByNameContainingIgnoreCase(String part);

//    @Modifying
//    @Query(value = "DELETE FROM provision_services_doctors WHERE provision_service_id = :id", nativeQuery = true)
//    void deleteFromDoctorsProvisionServicesByProvisionServiceId(Long id);

}
