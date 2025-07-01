package com.safeflow.service.impl;

import com.safeflow.domain.model.Delivery;
import com.safeflow.domain.model.Device;
import com.safeflow.domain.model.valueobjects.DeliveryState;
import com.safeflow.repository.DeliveryRepository;
import com.safeflow.repository.EmployeeRepository;
import com.safeflow.repository.SensorRepository;
import com.safeflow.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.safeflow.domain.model.valueobjects.DeliveryState.PENDING;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Device> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Device createSensor(Device sensor) {
        employeeRepository.findById(sensor.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner does not exist"));
        return sensorRepository.save(sensor);
    }

    @Override
    public Device updateSensor(Long id, Device sensor) {
        Optional<Device> existingSensor = sensorRepository.findById(id);
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
    public Optional<Device> findById(Long id) {
        return sensorRepository.findById(id);
    }

    @Override
    public List<Device> getSensorByOwnerId(Long id) {
        return sensorRepository.getSensorByOwnerId(id);
    }

    @Override
    public List<Device> getByDeliveryId(Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isEmpty()) {
            throw new RuntimeException("Delivery not found");
        }
        DeliveryState deliveryState = delivery.get().getState();
        if (deliveryState == PENDING  || deliveryState == DeliveryState.COMPLETED) {
            throw new RuntimeException("Cannot retrieve sensors for a pending or completed delivery");
        }
        Long employeeId = delivery.get().getEmployeeId();
        if (employeeId == null) {
            throw new RuntimeException("Delivery has no assigned employee");
        }
        return sensorRepository.getSensorByOwnerId(employeeId);    }
}
