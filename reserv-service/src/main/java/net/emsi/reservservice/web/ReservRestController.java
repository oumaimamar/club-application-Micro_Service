package net.emsi.reservservice.web;

import net.emsi.reservservice.entities.Reserv;
import net.emsi.reservservice.model.Customer;
import net.emsi.reservservice.model.Terrain;
import net.emsi.reservservice.repository.ReservRepository;
import net.emsi.reservservice.services.CustomerRestClient;
import net.emsi.reservservice.services.TerrainRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservRestController {
    private ReservRepository reservRepository;
    private TerrainRestClient terrainRestClient;
    private CustomerRestClient customerRestClient;

    public ReservRestController(ReservRepository reservRepository,
                                TerrainRestClient terrainRestClient,
                                CustomerRestClient customerRestClient) {
        this.reservRepository = reservRepository;
        this.terrainRestClient = terrainRestClient;
        this.customerRestClient = customerRestClient;
    }


    @GetMapping("/by-customer/{customerId}")
    public List<Reserv> getReservationsByCustomer(@PathVariable Long customerId) {
        // Fetch all reservations for the given customer ID
        List<Reserv> reservations = reservRepository.findByCustomerId(customerId);

        // Enhance each reservation with customer and terrain details
        reservations.forEach(reservation -> {
            // Fetch and set the customer details
            Customer customer = customerRestClient.findCustomerById(reservation.getCustomerId());
            if (customer != null) {
                reservation.setCustomer(customer);
            }

            // Fetch and set the terrain details
            Terrain terrain = terrainRestClient.findTerrainById(reservation.getTerrainId());
            if (terrain != null) {
                reservation.setTerrain(terrain);
            }
        });

        return reservations;
    }

    @GetMapping("/by-terrain/{terrainId}")
    public List<Reserv> getReservationsByTerrain(@PathVariable Long terrainId) {
        // Fetch all reservations for the given terrain ID
        List<Reserv> reservations = reservRepository.findByTerrainId(terrainId);

        // Enhance each reservation with customer and terrain details
        reservations.forEach(reservation -> {
            // Fetch and set the customer details
            Customer customer = customerRestClient.findCustomerById(reservation.getCustomerId());
            if (customer != null) {
                reservation.setCustomer(customer);
            }

            // Fetch and set the terrain details
            Terrain terrain = terrainRestClient.findTerrainById(reservation.getTerrainId());
            if (terrain != null) {
                reservation.setTerrain(terrain);
            }
        });

        return reservations;
    }




}
