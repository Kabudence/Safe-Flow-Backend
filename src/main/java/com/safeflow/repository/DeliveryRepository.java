package com.safeflow.repository;

import com.safeflow.domain.model.Delivery;
import com.safeflow.domain.model.valueobjects.DeliveryState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findByEmployeeId(Long employeeId);
    List<Delivery> findAllByState(DeliveryState state);

}
