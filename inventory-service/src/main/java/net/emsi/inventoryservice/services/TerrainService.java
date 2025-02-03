package net.emsi.inventoryservice.services;

import net.emsi.inventoryservice.dto.NewTerrainDTO;
import net.emsi.inventoryservice.entities.Club;
import net.emsi.inventoryservice.entities.Terrain;
import net.emsi.inventoryservice.entities.TerrainStatus;
import net.emsi.inventoryservice.entities.TerrainType;
import net.emsi.inventoryservice.repository.ClubRepository;
import net.emsi.inventoryservice.repository.TerrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Transactional
public class TerrainService {
    private TerrainRepository terrainRepository;
    private ClubRepository clubRepository;

    public TerrainService(TerrainRepository terrainRepository, ClubRepository clubRepository) {
        this.terrainRepository = terrainRepository;
        this.clubRepository = clubRepository;
    }

    // Create a new terrain METHODE_1
//    public Terrain createNewTerrain(NewTerrainDTO newTerrainDTO) {
//        // Check if the provided club exists
//        Club club = clubRepository.findById(newTerrainDTO.getClubId())
//                .orElseThrow(() -> new RuntimeException("Club not found with ID: " + newTerrainDTO.getClubId()));
//
//        // Create a new terrain
//        Terrain terrain = new Terrain();
//        terrain.setName(newTerrainDTO.getName());
//        terrain.setType(newTerrainDTO.getType());
//        terrain.setStatus(newTerrainDTO.getStatus());
//        terrain.setNbPersons(newTerrainDTO.getNbPersons());
//        terrain.setClub(club); // Associate with the club
//
//        return terrainRepository.save(terrain); // Save and return the terrain
//    }

    // Create a new terrain METHODE_2
    public Terrain createNewTerrain(NewTerrainDTO newTerrainDTO) {
        // Check if the provided club exists
        Club club = clubRepository.findById(newTerrainDTO.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found with ID: " + newTerrainDTO.getClubId()));

        // Create a new terrain
        Terrain terrain = Terrain.builder()
                .name(newTerrainDTO.getName())
                .type(newTerrainDTO.getType())
                .status(newTerrainDTO.getStatus())
                .club(club)
                .nbPersons(newTerrainDTO.getNbPersons())
                .build(); // Associate with the club
        return terrainRepository.save(terrain); // Save and return the terrain
    }



    // Update an existing terrain
    public Terrain updateTerrain(Long id, NewTerrainDTO newTerrainDTO) {
        // Fetch the existing terrain by ID
        Terrain terrain = terrainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terrain not found with ID: " + id));

        // Check if the provided club exists
        Club club = clubRepository.findById(newTerrainDTO.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found with ID: " + newTerrainDTO.getClubId()));

        // Update the terrain fields
        terrain.setName(newTerrainDTO.getName());
        terrain.setType(newTerrainDTO.getType());
        terrain.setStatus(newTerrainDTO.getStatus());
        terrain.setNbPersons(newTerrainDTO.getNbPersons());
        terrain.setClub(club); // Update the associated club

        // Save and return the updated terrain
        return terrainRepository.save(terrain);
    }


    // Update an existing terrain ONLY STATUS
    public Terrain updateTerrain( TerrainStatus status, Long id) {
        // Fetch the existing terrain by ID
        Terrain terrain = terrainRepository.findById(id).get();
        terrain.setStatus(status);
        // Save and return the updated terrain
        return terrainRepository.save(terrain);
    }




    public void deleteTerrain(Long terrainId) {
        // Fetch the terrain to ensure it exists
        Terrain terrain = terrainRepository.findById(terrainId)
                .orElseThrow(() -> new RuntimeException("Terrain not found with ID: " + terrainId));

        // Check if the terrain is associated with a club (optional, for logging or validation)
        if (terrain.getClub() == null) {
            throw new RuntimeException("Terrain is not associated with any club.");
        }

        // Delete the terrain
        terrainRepository.delete(terrain);
    }


}
