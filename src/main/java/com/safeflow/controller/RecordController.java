package com.safeflow.controller;

import com.safeflow.domain.model.RecordLog;
import com.safeflow.service.RecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Records", description = "Record Logs Management Endpoints")
@RequestMapping("/api/safeflow/v1/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/delivery/{deliveryId}")
    public ResponseEntity<List<RecordLog>> getAllRecordsByDeliveryId(@PathVariable Long deliveryId) {
        List<RecordLog> records = recordService.getAllRecordsByDeliveryId(deliveryId);
        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("/delivery/{deliveryId}/timedifference")
    public ResponseEntity<Long> getTimeDifferenceByDeliveryId(@PathVariable Long deliveryId) {
        Long difference = recordService.timeDifferenceInMinutesByDeliveryId(deliveryId);
        return new ResponseEntity<>(difference, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecordLog> createRecord(@RequestBody RecordLog recordLog) {
        try {
            RecordLog saved = recordService.createRecord(recordLog);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
