package com.safeflow.repository;

import com.safeflow.domain.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findIncidentByServiceId(Long serviceId);
}
