package com.safeflow.service.impl;

import com.safeflow.domain.model.Service;
import com.safeflow.repository.ServiceRepository;
import com.safeflow.service.ServiceServicio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServicioImpl implements ServiceServicio {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<Service> findServiceByOwnerId(Long ownerId) {
        return serviceRepository.findServiceByOwnerId(ownerId);
    }
}
