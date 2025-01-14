package net.emsi.inventoryservice;

import net.emsi.inventoryservice.entities.Terrain;
import net.emsi.inventoryservice.entities.TerrainType;
import net.emsi.inventoryservice.repository.TerrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(TerrainRepository terrainRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Terrain.class);

            TerrainType[] terrainTypes= TerrainType.values();
			Random random = new Random();

            int index = random.nextInt(terrainTypes.length);

            terrainRepository.saveAll(
                    List.of(
                            Terrain.builder().name("T1").type(terrainTypes[index]).nbPersons(5).build(),
                            Terrain.builder().name("T2").type(terrainTypes[index]).nbPersons(12).build(),
                            Terrain.builder().name("T3").type(terrainTypes[index]).nbPersons(10).build()

                            )

            );
        };
    }

}
