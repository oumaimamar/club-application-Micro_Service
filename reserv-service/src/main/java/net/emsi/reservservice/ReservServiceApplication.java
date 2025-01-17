package net.emsi.reservservice;

import net.emsi.reservservice.entities.Reserv;
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
            // IDs des clients et terrains à utiliser
            List<Long> customerIds = List.of(1L, 2L, 3L); // Exemple d'IDs de clients
            List<Terrain> terrains = terrainRestClient.allTerrains();

            if (terrains.isEmpty()) {
                throw new RuntimeException("No terrains found from INVENTORY-SERVICE");
            }

            // Créer 3 réservations
            for (int i = 0; i < 3; i++) {
                // Sélectionner un client et un terrain aléatoire
                Long customerId = customerIds.get(new Random().nextInt(customerIds.size()));
                Terrain terrain = terrains.get(new Random().nextInt(terrains.size()));

                // Vérifier l'existence du client
                Customer customer = customerRestClient.findCustomerById(customerId);
                if (customer == null) {
                    System.out.println("Customer not found with ID: " + customerId);
                    continue; // Passer à la réservation suivante
                }

                // Vérifier l'existence du terrain
                if (terrain == null) {
                    System.out.println("Terrain not found");
                    continue; // Passer à la réservation suivante
                }

                // Créer et sauvegarder la réservation
                Reserv reserv = new Reserv();
                reserv.setReservDate(new Date());
                reserv.setCustomerId(customerId);
                reserv.setTerrainId(terrain.getId());
                reserv.setType(terrain.getType());

                Reserv savedReserv = reservRepository.save(reserv);

                System.out.println("Reservation created:");
                System.out.println("Reservation ID: " + savedReserv.getId());
                System.out.println("Reservation Date: " + savedReserv.getReservDate());
                System.out.println("Customer ID: " + savedReserv.getCustomerId());
                System.out.println("Terrain ID: " + savedReserv.getTerrainId());
                System.out.println("Terrain Type: " + savedReserv.getType());
            }
        };
    }


}
