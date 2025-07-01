package com.safeflow.controller;

import com.safeflow.domain.model.Device;
import com.safeflow.service.SensorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Sensors", description = "Sensor Management Endpoints")
@RequestMapping("/api/safeflow/v1/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<Device>> getAllSensors() {
        return new ResponseEntity<>(sensorService.getAllSensors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getSensorById(@PathVariable Long id) {
        return sensorService.findById(id)
                .map(sensor -> new ResponseEntity<>(sensor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Device> createSensor(@RequestBody Device sensor) {
        return new ResponseEntity<>(sensorService.createSensor(sensor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateSensor(@PathVariable Long id, @RequestBody Device sensor) {
        return new ResponseEntity<>(sensorService.updateSensor(id, sensor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Device>> getSensorsByOwnerId(@PathVariable Long ownerId) {
        List<Device> sensors = sensorService.getSensorByOwnerId(ownerId);
        return new ResponseEntity<>(sensors, HttpStatus.OK);
    }

    @GetMapping("/delivery/{deliveryId}")
    public ResponseEntity<List<Device>> getSensorsByDeliveryId(@PathVariable Long deliveryId) {
        List<Device> sensors = sensorService.getByDeliveryId(deliveryId);
        return new ResponseEntity<>(sensors, HttpStatus.OK);
    }
}