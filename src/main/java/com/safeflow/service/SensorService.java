package com.safeflow.service;

import com.safeflow.model.Sensor;
import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Sensor> getAllSensors();
    Sensor createSensor(Sensor sensor);
    Sensor updateSensor(Long id, Sensor sensor);
    void deleteSensor(Long id);
    Optional<Sensor> findById(Long id);  // New method
}
