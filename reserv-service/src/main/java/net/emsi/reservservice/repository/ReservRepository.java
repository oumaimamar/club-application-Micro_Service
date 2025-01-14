package net.emsi.reservservice.repository;

import net.emsi.reservservice.entities.Reserv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservRepository extends JpaRepository<Reserv, Long> {
}
