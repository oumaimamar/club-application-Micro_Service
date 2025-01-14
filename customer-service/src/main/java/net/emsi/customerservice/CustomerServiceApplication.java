package net.emsi.customerservice;

import net.emsi.customerservice.config.CustomerConfigParams;
import net.emsi.customerservice.entities.Customer;
import net.emsi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner init(CustomerRepository customerRepository,
						   RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(
					List.of(
							Customer.builder().name("Hanan").email("hanan@gmail.com").build(),
							Customer.builder().name("Alex").email("alex@gmail.com").build(),
							Customer.builder().name("Amin").email("amin@gmail.com").build()
					)
			);
			customerRepository.findAll().forEach(System.out::println);

		};
	}

}
