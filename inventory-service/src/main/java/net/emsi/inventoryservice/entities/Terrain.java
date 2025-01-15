package net.emsi.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Terrain {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private TerrainType type;
    @Enumerated(EnumType.STRING)
    private TerrainStatus status;
    private int nbPersons;

    @ManyToOne
    @JsonBackReference // Child side of the relationship
    private Club club;
}
