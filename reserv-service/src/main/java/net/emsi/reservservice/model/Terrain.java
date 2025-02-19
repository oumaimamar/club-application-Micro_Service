package net.emsi.reservservice.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import net.emsi.reservservice.entities.TerrainType;

@Data
public class Terrain {
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private TerrainType type;
    private int nbPersons;

    private Club club;  // Reference to the club

}

