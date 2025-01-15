package net.emsi.inventoryservice.repository;

import net.emsi.inventoryservice.entities.Club;
import net.emsi.inventoryservice.entities.ClubVille;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Club findByCode(String code);
    List<Club> findByVille(ClubVille ville);
}
