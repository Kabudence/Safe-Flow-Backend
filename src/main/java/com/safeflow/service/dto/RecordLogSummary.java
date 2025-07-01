package com.safeflow.service.dto;

import com.safeflow.domain.model.RecordLog;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class RecordLogSummary {
    private List<RecordLog> records;
    private long timeDifferenceInMinutes;


    public static long calculateMinutesBetween(List<RecordLog> records) {
        if (records.size() < 2) return 0;

        LocalDateTime first = records.get(0).getTimestamp();
        LocalDateTime last = records.get(records.size() - 1).getTimestamp();

        return Duration.between(first, last).toMinutes();
    }

}
