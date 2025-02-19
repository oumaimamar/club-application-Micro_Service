package net.emsi.reservservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class Club {
    private Long id;
//    @Column(unique = true)
//    private String code;
    private String name;
//    @Enumerated(EnumType.STRING)
//    private ClubVille ville;

//    private List<Terrain> terrains;
}
