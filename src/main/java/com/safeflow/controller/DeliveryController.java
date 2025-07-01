package com.safeflow.controller;

import com.safeflow.domain.model.Delivery;
import com.safeflow.service.DeliveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Deliveries", description = "Deliveries Management Endpoints")
@RequestMapping("/api/safe-flow/v1/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return new ResponseEntity<>(deliveryService.getAllDeliveries(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        return deliveryService.findById(id)
                .map(delivery -> new ResponseEntity<>(delivery, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return new ResponseEntity<>(deliveryService.createDelivery(delivery), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/in-progress")
    public ResponseEntity<Void> inProcessDelivery(@PathVariable Long id, @RequestParam Long employeeId) {
        try {
            deliveryService.inProcessDelivery(id, employeeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<Void> completedDelivery(@PathVariable Long id) {
        try {
            deliveryService.completedDelivery(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Delivery>> getAllDeliveriesByState(@PathVariable String state) {
        try {
            return new ResponseEntity<>(deliveryService.getAllDeliveriesByState(state), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Delivery> getDeliveryByEmployeeId(@PathVariable Long employeeId) {
        Optional<Delivery> delivery = deliveryService.findByEmployeeId(employeeId);
        return delivery
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        if (deliveryService.findById(id).isPresent()) {
            deliveryService.deleteDelivery(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
