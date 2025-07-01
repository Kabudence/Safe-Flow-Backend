package com.safeflow.domain.model;


import com.safeflow.domain.model.valueobjects.InputDataSensor;
import com.safeflow.domain.services.validate.ValidateSensorStrategyImplements;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="records")
public class RecordLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_id", nullable = false)
    private long sensorId;

    @Column(name ="delivery_id", nullable = false)
    private long deliveryId;

    @Column(name = "gas_value")
    private float gasValue;

    @Column(name ="heart_rate_value", nullable = false)
    private Float heartRateValue;

    @Column(name = "temperature_value", nullable = false)
    private Float temperatureValue;

    @Column (name ="latitude", nullable = false)
    private double latitude;
    @Column (name ="longitude", nullable = false)
    private double longitude;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;


    public String generateDescription(String place, Device sensor, InputDataSensor inputDataSensor) {
        String sensorValidation = ValidateSensorStrategyImplements.validateSensor(inputDataSensor);
        StringBuilder description = new StringBuilder();

        description.append("Device ID: ").append(sensor.getId()).append("\n");

        if (!"OK".equals(sensorValidation)) {
            description.append("Sensor Issues: ").append(sensorValidation).append("\n");
        } else {
            description.append("All sensors are within safe limits.\n");
        }

        description.append("Sensor Safe Status: Unsafe");
        description.append("Location: ").append(place).append("\n");
        description.append("Timestamp: ").append(timestamp != null ? timestamp.toString() : "N/A").append("\n");

        return description.toString();
    }




}
