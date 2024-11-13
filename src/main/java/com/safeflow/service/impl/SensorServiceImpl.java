package com.safeflow.service.impl;

import com.safeflow.model.Sensor;
import com.safeflow.repository.SensorRepository;
import com.safeflow.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor updateSensor(Long id, Sensor sensor) {
        Optional<Sensor> existingSensor = sensorRepository.findById(id);
        if (existingSensor.isPresent()) {
            sensor.setId(id);
            return sensorRepository.save(sensor);
        }
        throw new RuntimeException("Sensor not found");
    }

    @Override
    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public Optional<Sensor> findById(Long id) {
        return sensorRepository.findById(id);
    }
}
