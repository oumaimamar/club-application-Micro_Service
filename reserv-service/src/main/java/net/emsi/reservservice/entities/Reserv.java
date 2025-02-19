package net.emsi.reservservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.emsi.reservservice.model.Customer;
import net.emsi.reservservice.model.Terrain;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reserv {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date reservDate;
    private Long customerId;
    private Long terrainId;

    @Enumerated(EnumType.STRING)
    private TerrainType type;

    @Transient
    private Customer customer;

    @Transient
    private Terrain terrain;

    @Transient
    private String clubName; // New field for club name
}

