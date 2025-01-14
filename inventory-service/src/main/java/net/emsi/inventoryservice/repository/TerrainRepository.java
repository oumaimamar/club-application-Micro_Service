package net.emsi.inventoryservice.repository;

import net.emsi.inventoryservice.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TerrainRepository extends JpaRepository<Terrain, Long> {
}
