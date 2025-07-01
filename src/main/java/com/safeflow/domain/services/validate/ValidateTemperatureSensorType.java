package com.safeflow.domain.services.validate;

import com.safeflow.domain.model.valueobjects.InputDataSensor;

public class ValidateTemperatureSensorType implements ValidateSensorStrategy {

    @Override
    public boolean validateSensorType(InputDataSensor input) {
        Float value = input.temperatureValue();

        return value != null && value >= -50.0 && value <= 100.0;
    }
}
