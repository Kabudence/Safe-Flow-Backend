package com.safeflow.domain.services.validate;

import com.safeflow.domain.model.valueobjects.InputDataSensor;

public class ValidateSensorStrategyImplements {

    private static final ValidateGasSensorType gasValidator = new ValidateGasSensorType();
    private static final ValidateTemperatureSensorType tempValidator = new ValidateTemperatureSensorType();
    private static final ValidateCoordinatesSensorType gpsValidator = new ValidateCoordinatesSensorType();
    private static final ValidateBpmSensorType bpmValidator = new ValidateBpmSensorType();

    /**
     * Devuelve los mensajes de error concatenados. Si todo es válido, retorna "OK".
     */
    public static String validateSensor(InputDataSensor entrada) {
        StringBuilder errors = new StringBuilder();

        if (!gasValidator.validateSensorType(entrada)) {
            errors.append("Gas value is invalid. ");
        }
        if (!tempValidator.validateSensorType(entrada)) {
            errors.append("Temperature value is invalid. ");
        }
        if (!gpsValidator.validateSensorType(entrada)) {
            errors.append("Coordinates are invalid. ");
        }
        if (!bpmValidator.validateSensorType(entrada)) {
            errors.append("Heart rate value is invalid. ");
        }

        if (errors.isEmpty()) {
            return "OK";
        }
        return errors.toString().trim();
    }

    /**
     * Retorna true si TODOS los sensores pasan las validaciones.
     */
    public static boolean isValid(InputDataSensor entrada) {
        return gasValidator.validateSensorType(entrada)
                && tempValidator.validateSensorType(entrada)
                && gpsValidator.validateSensorType(entrada)
                && bpmValidator.validateSensorType(entrada);
    }
}
