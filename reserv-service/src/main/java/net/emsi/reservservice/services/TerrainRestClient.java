package net.emsi.reservservice.services;

import net.emsi.reservservice.model.Terrain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface TerrainRestClient {
    @GetMapping(path = "/terrains/{id}")
    public Terrain findTerrainById(@PathVariable Long id);

    @GetMapping(path = "/terrains")
    PagedModel<Terrain>allTerrains();
}
