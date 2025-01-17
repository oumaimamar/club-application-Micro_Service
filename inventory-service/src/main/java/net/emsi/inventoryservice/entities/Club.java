package net.emsi.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Club {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private ClubVille ville;

    @OneToMany(mappedBy = "club")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Terrain> terrains;


}
