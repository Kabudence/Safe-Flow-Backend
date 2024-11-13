package com.safeflow.model;

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
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gas_value", nullable = false)
    private float gasValue;

    @Column(name = "temp", nullable = false)
    private float temp;

    @Column(name = "safe", nullable = false)
    private boolean safe;

    @Column(name = "unsafe", nullable = false)
    private boolean unsafe;
}
