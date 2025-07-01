package com.safeflow.service;

import com.safeflow.domain.model.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentService {
    List<Incident> getAllIncidents();
    Incident createIncident(Incident incident);
    void deleteIncident(Long id);
    Optional<Incident> findById(Long id);
    List<Incident> findIncidentsByDeliveryId(Long id);
}
