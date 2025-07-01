package com.safeflow.domain.services.validate;

import com.safeflow.domain.model.valueobjects.InputDataSensor;

public interface ValidateSensorStrategy {

    public boolean validateSensorType(InputDataSensor input);
}
