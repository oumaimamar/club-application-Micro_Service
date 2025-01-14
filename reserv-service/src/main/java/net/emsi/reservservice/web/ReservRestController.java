package net.emsi.reservservice.web;

import net.emsi.reservservice.entities.Reserv;
import net.emsi.reservservice.entities.TerrainItem;
import net.emsi.reservservice.repository.ReservRepository;
import net.emsi.reservservice.repository.TerrainItemRepository;
import net.emsi.reservservice.services.CustomerRestClient;
import net.emsi.reservservice.services.TerrainRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservRestController {
    private ReservRepository reservRepository;
    private TerrainItemRepository terrainItemRepository;
    private TerrainRestClient terrainRestClient;
    private CustomerRestClient customerRestClient;

    public ReservRestController(ReservRepository reservRepository,
                                TerrainItemRepository terrainItemRepository,
                                TerrainRestClient terrainRestClient,
                                CustomerRestClient customerRestClient) {
        this.reservRepository = reservRepository;
        this.terrainItemRepository = terrainItemRepository;
        this.terrainRestClient = terrainRestClient;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping( "/fullReserv/{id}")
    public Reserv reserv(@PathVariable Long id) {
        Reserv reserv=reservRepository.findById(id).get();
        reserv.setCustomer(customerRestClient.findCustomerById(reserv.getCustomerId()));
        reserv.getTerrainItems().forEach(ti -> {
            ti.setTerrain(terrainRestClient.findTerrainById(ti.getTerrainId()));
        });
        return reserv;

    }
}
