package net.emsi.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.emsi.inventoryservice.entities.TerrainStatus;
import net.emsi.inventoryservice.entities.TerrainType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTerrainDTO {
    private String name;
    private TerrainType type;
    private TerrainStatus status;
    private int nbPersons;
    private Long clubId; // The ID of the club to associate with the terrain
}
