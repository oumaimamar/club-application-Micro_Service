package net.emsi.reservservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.emsi.reservservice.model.Customer;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reserv {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date reservDate;
    private Long customerId;

    @OneToMany(mappedBy = "reserv")
    private List<TerrainItem> terrainItems;

    @Transient
    private Customer customer;

}
