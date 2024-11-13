package com.safeflow.service;

import com.safeflow.model.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<Delivery> getAllDeliveries();
    Delivery createDelivery(Delivery delivery);
    Optional<Delivery> findById(Long id);  // New method
}
