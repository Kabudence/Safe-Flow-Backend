package com.safeflow.service;

import com.safeflow.domain.model.Device;
import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Device> getAllSensors();
    Device createSensor(Device sensor);
    Device updateSensor(Long id, Device sensor);
    void deleteSensor(Long id);
    Optional<Device> findById(Long id);
    List<Device> getSensorByOwnerId(Long id);
    List<Device> getByDeliveryId(Long id);
}
