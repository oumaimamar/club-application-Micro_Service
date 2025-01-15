package net.emsi.reservservice;

import net.emsi.reservservice.entities.Reserv;
import net.emsi.reservservice.entities.TerrainItem;
import net.emsi.reservservice.entities.TerrainType;
import net.emsi.reservservice.model.Customer;
import net.emsi.reservservice.model.Terrain;
import net.emsi.reservservice.repository.ReservRepository;
import net.emsi.reservservice.repository.TerrainItemRepository;
import net.emsi.reservservice.services.CustomerRestClient;
import net.emsi.reservservice.services.TerrainRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
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
                                   TerrainItemRepository terrainItemRepository,
                                   CustomerRestClient customerRestClient,
                                   TerrainRestClient terrainRestClient) {
        return args -> {
            // Récupération de tous les terrains
            List<Terrain> terrains = terrainRestClient.allTerrains();
            if (terrains == null || terrains.isEmpty()) {
                throw new RuntimeException("No terrains found from INVENTORY-SERVICE");
            }

            // ID d'un client (modifiable selon vos besoins)
            Long customerId = 1L;

            // Vérification de l'existence du client
            Customer customer = customerRestClient.findCustomerById(customerId);
            if (customer == null) {
                throw new RuntimeException("Customer not found with ID: " + customerId);
            }

            // Création et sauvegarde de la réservation
            Reserv reserv = new Reserv();
            reserv.setReservDate(new Date());
            reserv.setCustomerId(customerId);
            Reserv savedReserv = reservRepository.save(reserv);

            // Association des terrains à la réservation
            terrains.forEach(terrain -> {
                TerrainItem terrainItem = new TerrainItem();
                terrainItem.setReserv(savedReserv);
                terrainItem.setTerrainId(terrain.getId());
                terrainItem.setNbPersons(1 + new Random().nextInt(10));
                terrainItem.setType(TerrainType.Basketball); // Modifier si nécessaire
                terrainItem.setDiscount(Math.random()); // Génération d'un discount aléatoire
                terrainItemRepository.save(terrainItem);
            });

            System.out.println("Reservation initialized with terrains and customer data.");
        };
    }

}
