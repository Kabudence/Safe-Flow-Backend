package com.safeflow.service;

import com.safeflow.domain.model.RecordLog;

import java.util.List;

public interface RecordService {
    List<RecordLog> getAllRecordsByDeliveryId(Long id);
    Long timeDifferenceInMinutesByDeliveryId(Long id);
    RecordLog createRecord(RecordLog record);

}
