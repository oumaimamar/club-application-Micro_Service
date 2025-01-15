package net.emsi.inventoryservice;

import net.emsi.inventoryservice.entities.*;
import net.emsi.inventoryservice.repository.ClubRepository;
import net.emsi.inventoryservice.repository.TerrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner start(TerrainRepository terrainRepository, RepositoryRestConfiguration restConfiguration) {
//        return args -> {
//            restConfiguration.exposeIdsFor(Terrain.class);
//
//            TerrainType[] terrainTypes= TerrainType.values();
//			Random random = new Random();
//
//            int index = random.nextInt(terrainTypes.length);
//
//            terrainRepository.saveAll(
//                    List.of(
//                            Terrain.builder().name("T1").type(terrainTypes[index]).nbPersons(5).build(),
//                            Terrain.builder().name("T2").type(terrainTypes[index]).nbPersons(12).build(),
//                            Terrain.builder().name("T3").type(terrainTypes[index]).nbPersons(10).build()
//
//                            )
//
//            );
//        };
//    }


    	@Bean
	CommandLineRunner start(ClubRepository clubRepository, TerrainRepository terrainRepository) {
		return args -> {
			clubRepository.save(Club.builder().code("1111").name("CitySports").ville(ClubVille.MARRAKESH).build());
            clubRepository.save(Club.builder().code("2222").name("Kahrama").ville(ClubVille.MARRAKESH).build());
            clubRepository.save(Club.builder().code("3333").name("ONE").ville(ClubVille.CASABLANCA).build());
            clubRepository.save(Club.builder().code("4444").name("Lydec").ville(ClubVille.FES).build());

			TerrainType[] terrainTypes= TerrainType.values();
            TerrainStatus[] terrainStatus= TerrainStatus.values();
			Random random = new Random();
            clubRepository.findAll().forEach(club -> {
				for(int i = 0; i < 5 ; i++){
					int index = random.nextInt(terrainTypes.length);
                    int index1 = random.nextInt(terrainStatus.length);

                    Terrain terrain = Terrain.builder()
//                            .name(club.getName())
							.name("T"+(1+new Random().nextInt(20)))
							.type(terrainTypes[index])
                            .status(terrainStatus[index1])
							.nbPersons(1 + new Random().nextInt(10))
							.club(club)
							.build();
					terrainRepository.save(terrain);
				}
			});

		};
	}

}
