package com.safeflow.domain.services.validate;

import com.safeflow.domain.model.valueobjects.InputDataSensor;

public class ValidateBpmSensorType implements ValidateSensorStrategy {

    @Override
    public boolean validateSensorType(InputDataSensor input) {
        Float value = input.heartRateValue();
        return value != null && value >= 40.0 && value <= 160.0;
    }
}
