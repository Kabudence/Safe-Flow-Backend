package com.safeflow.service;

import com.safeflow.domain.model.Delivery;
import com.safeflow.domain.model.valueobjects.DeliveryState;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<Delivery> getAllDeliveries();
    Delivery createDelivery(Delivery delivery);
    Optional<Delivery> findById(Long id);
    void deleteDelivery(Long id);
    void inProcessDelivery(Long id,Long employeeId);
    void completedDelivery(Long id);
    List<Delivery> getAllDeliveriesByState( String state);
    Optional<Delivery> findByEmployeeId(Long employeeId);
// New delete method
}
