package com.safeflow.service;

import com.safeflow.model.Service;

import java.util.List;

public interface ServiceServicio {
    List<Service> getAllServices();
    Service createService(Service service);
    void deleteService(Long id);
}