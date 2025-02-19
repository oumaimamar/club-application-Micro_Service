package net.emsi.reservservice;

import net.emsi.reservservice.entities.Reserv;
import net.emsi.reservservice.model.Club;
import net.emsi.reservservice.model.Customer;
import net.emsi.reservservice.model.Terrain;
import net.emsi.reservservice.repository.ReservRepository;
import net.emsi.reservservice.services.CustomerRestClient;
import net.emsi.reservservice.services.TerrainRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class ReservServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ReservRepository reservRepository,
                                   CustomerRestClient customerRestClient,
                                   TerrainRestClient terrainRestClient) {
        return args -> {

            List<Long> customerIds = List.of(1L, 2L, 3L);

            // Fetch all clubs and terrains
            List<Club> clubs = terrainRestClient.allClubs();
            List<Terrain> terrains = terrainRestClient.allTerrains();

            if (terrains.isEmpty()) {
                throw new RuntimeException("No terrains found from INVENTORY-SERVICE");
            }

            if (clubs.isEmpty()) {
                throw new RuntimeException("No clubs found from INVENTORY-SERVICE");
            }

            for (int i = 0; i < 3; i++) {
                // Select a random customer and terrain
                Long customerId = customerIds.get(new Random().nextInt(customerIds.size()));
                Terrain terrain = terrains.get(new Random().nextInt(terrains.size()));
                Customer customer = customerRestClient.findCustomerById(customerId);

                if (customer == null) {
                    System.out.println("Customer not found with ID: " + customerId);
                    continue; // Skip to the next reservation
                }

                if (terrain == null) {
                    System.out.println("Terrain not found");
                    continue; // Skip to the next reservation
                }

                // Create and save the reservation
                Reserv reserv = new Reserv();
                reserv.setReservDate(new Date());
                reserv.setCustomerId(customerId);
                reserv.setTerrainId(terrain.getId());
                reserv.setType(terrain.getType());

                // Fetch the club name from the terrain's associated club (only set once)
                String clubName = (terrain.getClub() != null && terrain.getClub().getName() != null)
                        ? terrain.getClub().getName()
                        : "Unknown Club";  // Default to "Unknown Club" if null
                reserv.setClubName(clubName);

                // Save the reservation to the repository
                Reserv savedReserv = reservRepository.save(reserv);

                System.out.println("Reservation created:");
                System.out.println("Reservation ID: " + savedReserv.getId());
                System.out.println("Reservation Date: " + savedReserv.getReservDate());
                System.out.println("Customer ID: " + savedReserv.getCustomerId());
                System.out.println("Terrain ID: " + savedReserv.getTerrainId());
                System.out.println("Terrain Type: " + savedReserv.getType());
                System.out.println("Club Name: " + savedReserv.getClubName());
            }
        };
    }






}
