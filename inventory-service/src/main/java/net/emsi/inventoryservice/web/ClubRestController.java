package net.emsi.inventoryservice.web;

import net.emsi.inventoryservice.dto.NewTerrainDTO;
import net.emsi.inventoryservice.entities.*;
import net.emsi.inventoryservice.repository.ClubRepository;
import net.emsi.inventoryservice.repository.TerrainRepository;
import net.emsi.inventoryservice.services.TerrainService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
//@RequestMapping("/api")
public class ClubRestController {

    private ClubRepository clubRepository;
    private TerrainRepository terrainRepository;
    private TerrainService terrainService;

    public ClubRestController(ClubRepository clubRepository, TerrainRepository terrainRepository, TerrainService terrainService) {
        this.clubRepository = clubRepository;
        this.terrainRepository = terrainRepository;
        this.terrainService = terrainService;
    }

    @GetMapping(path = "/clubs")
    public List<Club> AllClubs() {
        return clubRepository.findAll();
    }

    @GetMapping("/clubs/{code}")
    public Club getClubByCode(@PathVariable String code){
        return clubRepository.findByCode(code);
    }
    @GetMapping("/clubsByVille")
    public List<Club> clubsByVille(@RequestParam ClubVille ville){return clubRepository.findByVille(ville);}



    @GetMapping(path = "/terrains")
    public List<Terrain> allTerrains() {return terrainRepository.findAll();}
    @GetMapping("/terrains/{id}")
    public Terrain getTerrainsById(@PathVariable Long id){
        return terrainRepository.findById(id).get();
    }

    @GetMapping("/clubs/{code}/terrains")
    public List<Terrain> terrainsByClubCode(@PathVariable String code){return terrainRepository.findByClubCode(code);}

    @GetMapping("/terrainsByType")
    public List<Terrain> terrainsByType(@RequestParam TerrainType type){return terrainRepository.findByType(type);}
    @GetMapping("/terrainsByStatus")
    public List<Terrain> terrainsByStatus(@RequestParam TerrainStatus status){return terrainRepository.findByStatus(status);}

//    ----------------------------------------------------------------------------------------

    //Add new terrain declare METHODE_1
//    @PostMapping(path="/terrains")
//    public Terrain createNewTerrain( NewTerrainDTO newTerrainDTO) throws IOException {
//        return terrainService.createNewTerrain(newTerrainDTO);
//    }

    //Add new terrain declare METHODE_2
    @PostMapping(path="/terrains")
    public Terrain createNewTerrain( NewTerrainDTO newTerrainDTO) throws IOException {
        return terrainService.createNewTerrain(newTerrainDTO);
    }

    @PutMapping("terrains/{id}")
    public Terrain updateTerrain(@PathVariable Long id, @RequestBody NewTerrainDTO newTerrainDTO) {
        return terrainService.updateTerrain(id, newTerrainDTO);
    }


    @PutMapping("/{id}/status")
    public Terrain updateTerrainStatus(@PathVariable Long id, @RequestParam TerrainStatus status) {
        // Call the service method to update the terrain
        return terrainService.updateTerrain(status, id);
    }


//    Delete Terrain methode without ResponseEntity
//    public void deleteTerrain(Long id) {
//        Terrain terrain = terrainRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Terrain not found with ID: " + id));
//        terrainRepository.delete(terrain);
//    }

    @DeleteMapping("terrains/{id}")
    public ResponseEntity<String> deleteTerrain(@PathVariable Long id) {
        terrainService.deleteTerrain(id); // Perform the deletion logic
        String successMessage = "Terrain with ID " + id + " has been successfully deleted.";
        return ResponseEntity.ok(successMessage); // Return HTTP 200 OK with the success message
    }



}
