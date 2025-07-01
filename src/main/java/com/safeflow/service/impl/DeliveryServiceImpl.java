package com.safeflow.service.impl;
import com.safeflow.domain.model.Delivery;
import com.safeflow.domain.model.valueobjects.DeliveryState;
import com.safeflow.repository.DeliveryRepository;
import com.safeflow.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery createDelivery(Delivery delivery) {
        delivery.setState(DeliveryState.PENDING);
        return deliveryRepository.save(delivery);
    }

    @Override
    public Optional<Delivery> findById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public void inProcessDelivery(Long id, Long employeeId) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);
        if (deliveryOptional.isPresent()) {
            Delivery delivery = deliveryOptional.get();
            delivery.setState(DeliveryState.IN_PROGRESS);
            delivery.setEmployeeId(employeeId);
            deliveryRepository.save(delivery);
        } else {
            throw new RuntimeException("Delivery not found");
        }

    }

    @Override
    public void completedDelivery(Long id) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);
        if (deliveryOptional.isPresent()) {
            Delivery delivery = deliveryOptional.get();
            delivery.setState(DeliveryState.COMPLETED);
            deliveryRepository.save(delivery);
        } else {
            throw new RuntimeException("Delivery not found");
        }

    }

    @Override
    public List<Delivery> getAllDeliveriesByState(String state) {

        DeliveryState deliveryState;
        try {
            deliveryState = DeliveryState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid delivery state: " + state);
        }
        return deliveryRepository.findAllByState(deliveryState);

    }


    @Override
    public Optional<Delivery> findByEmployeeId(Long employeeId) {
        return deliveryRepository.findByEmployeeId(employeeId);
    }
}
