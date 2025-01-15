package net.emsi.inventoryservice.web;

import net.emsi.inventoryservice.entities.*;
import net.emsi.inventoryservice.repository.ClubRepository;
import net.emsi.inventoryservice.repository.TerrainRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ClubRestController {
    private ClubRepository clubRepository;
    private TerrainRepository terrainRepository;

    public ClubRestController(ClubRepository clubRepository, TerrainRepository terrainRepository) {
        this.clubRepository = clubRepository;
        this.terrainRepository = terrainRepository;
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



}
