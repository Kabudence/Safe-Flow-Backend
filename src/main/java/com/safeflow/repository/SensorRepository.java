package com.safeflow.repository;

import com.safeflow.domain.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Device, Long> {

    List<Device> getSensorByOwnerId(Long id);

}
