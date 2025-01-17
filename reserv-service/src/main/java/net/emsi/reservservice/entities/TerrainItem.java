//package net.emsi.reservservice.entities;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import net.emsi.reservservice.model.Terrain;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//public class TerrainItem {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Long terrainId;
//    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Reserv reserv;
//
//    @Enumerated(EnumType.STRING)
//    private TerrainType type;
//    private int nbPersons;
//    private double discount;
//
//    @Transient
//    private Terrain terrain;
//}
