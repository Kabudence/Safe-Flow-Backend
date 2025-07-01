package com.safeflow.service.impl;

import com.safeflow.domain.model.Delivery;
import com.safeflow.domain.model.Incident;
import com.safeflow.domain.model.RecordLog;
import com.safeflow.domain.model.Device;
import com.safeflow.domain.model.valueobjects.DeliveryState;
import com.safeflow.domain.model.valueobjects.InputDataSensor;
import com.safeflow.repository.*;
import com.safeflow.service.RecordService;
import com.safeflow.service.dto.RecordLogSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private GeolocationServiceImpl geolocationService;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<RecordLog> getAllRecordsByDeliveryId(Long id) {
        return recordRepository.findByDeliveryId(id);
    }

    @Override
    public Long timeDifferenceInMinutesByDeliveryId(Long id) {
        List<RecordLog> records = recordRepository.findByDeliveryId(id);

        if (records.isEmpty()) {
            return 0L;
        }
        //dto which returns a comparison of the first and last record timestamp
        return RecordLogSummary.calculateMinutesBetween(records);
    }

    @Override
    public RecordLog createRecord(RecordLog record) {
        Optional<Delivery> delivery = deliveryRepository.findById(record.getDeliveryId());
        if (delivery.isEmpty()) {
            throw new RuntimeException("Delivery not found");
        }
        DeliveryState state = delivery.get().getState();
        if (state == DeliveryState.PENDING || state == DeliveryState.COMPLETED) {
            throw new RuntimeException("Delivery cannot accept records in this state: " + state);
        }


        //generacion de input para strategy
        InputDataSensor inputDataSensor = new InputDataSensor(
                record.getGasValue(),
                record.getTemperatureValue(),
                record.getHeartRateValue(),
                record.getLatitude(),
                record.getLongitude()
        );
        Device sensor = sensorRepository.findById(record.getSensorId())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));
        //validacion de estado de sensor
        sensor.updateSafeState(inputDataSensor);

        if (!sensor.isSafe()) {

            //actualizacion de estado de sensor a inseguro para front
            sensorRepository.save(sensor);
            //generacion de incidente
            String place = geolocationService.getDisplayNameFromCoordinates(record.getLatitude(), record.getLongitude());
            String description = record.generateDescription(place, sensor, inputDataSensor);

            Incident incident = Incident.builder()
                    .incidentPlace(place)
                    .date(record.getTimestamp())
                    .description(description)
                    .serviceId(delivery.get().getId())
                    .build();

            incidentRepository.save(incident);
            //IMPLEMENTAR NOTIFICACION

        }
        //guardado de record
        return recordRepository.save(record);
    }

}
