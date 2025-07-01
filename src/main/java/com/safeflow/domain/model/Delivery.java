package com.safeflow.domain.model;
import com.safeflow.domain.model.valueobjects.DeliveryState;
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
@Table(name="deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "package_description", nullable = false) // Renamed field
    private String packageDescription;

    @Column(name = "exit_point", nullable = false) // Renamed field
    private String exitPoint;

    @Column(name = "route", nullable = false)
    private String route;

    @Column(name = "stop", nullable = false)
    private String stop;

    @Column(name = "combustible_type", nullable = false)
    private String combustibleType;


    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private DeliveryState state;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name= "owner_id", nullable = false)
    private Long ownerId;
}
