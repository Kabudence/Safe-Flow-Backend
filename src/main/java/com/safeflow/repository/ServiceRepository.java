package com.safeflow.repository;

import com.safeflow.domain.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findServiceByOwnerId(Long ownerId);
}
