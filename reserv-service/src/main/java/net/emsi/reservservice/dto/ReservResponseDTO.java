package net.emsi.reservservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservResponseDTO {
    private Long reservId;
    private Date reservDate;
    private Long customerId;
    private String terrainType; // Type de terrain
    private String clubName;    // Nom du club
}
