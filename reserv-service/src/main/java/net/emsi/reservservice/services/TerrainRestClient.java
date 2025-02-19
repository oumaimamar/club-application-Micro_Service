package net.emsi.reservservice.services;

import net.emsi.reservservice.model.Club;
import net.emsi.reservservice.model.Terrain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE")
public interface TerrainRestClient {

    @GetMapping(path = "/terrains/{id}")
    Terrain findTerrainById(@PathVariable Long id);

    @GetMapping(path = "/terrains")
    List<Terrain> allTerrains();


    @GetMapping(path = "/clubs")
    List<Club> allClubs(); // Fetch all clubs

}
