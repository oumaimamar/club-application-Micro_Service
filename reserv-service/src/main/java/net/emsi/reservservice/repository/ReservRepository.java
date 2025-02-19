package net.emsi.reservservice.repository;

import net.emsi.reservservice.entities.Reserv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservRepository extends JpaRepository<Reserv, Long> {
    List<Reserv> findByCustomerId(Long customerId);
    List<Reserv> findByTerrainId(Long terrainId);



    @Query("SELECT DISTINCT r.terrainId FROM Reserv r")
    List<Long> findDistinctTerrainIds();

}
