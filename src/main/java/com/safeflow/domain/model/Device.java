package com.safeflow.domain.model;

import com.safeflow.domain.model.valueobjects.InputDataSensor;
import com.safeflow.domain.services.validate.ValidateSensorStrategyImplements;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sensors")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "safe", nullable = false)
     private boolean safe;

    
    public Device updateSafeState(InputDataSensor inputDataSensor) {

        this.safe = ValidateSensorStrategyImplements.isValid(inputDataSensor);
        return this;
    }


//    @Column(name= "sensor_type", nullable = false)
//    private SensorType sensorType;

//    @Column(name= "api_key", nullable = false)
//    private String apiKey;


//    public Device stateSensor(InputDataSensor entrada) {
//        this.safe = ValidateSensorStrategyImplements.validateSensor(entrada, this.sensorType);
//        return this;
//    }




//    @Column(name = "gas_value", nullable = true)
//    private Float gasValue;//queda

//    @Column(name = "temp", nullable = true)
//    private Float temp;//queda

//    @Column(name = "bpm", nullable = true)
//    private Float bpm;//queda





//    @Column (name ="latitude", nullable = false)
//    private double latitude;
//    @Column (name ="longitude", nullable = false)
//    private double longitude;


}
