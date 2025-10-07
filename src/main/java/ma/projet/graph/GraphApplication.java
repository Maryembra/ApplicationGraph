package ma.projet.graph;

import ma.projet.graph.entities.Compte;
import ma.projet.graph.entities.TypeCompte;
import ma.projet.graph.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class GraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphApplication.class, args);
	}

	/*@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			// Initialisation des comptes
			compteRepository.save(new Compte(null, Math.random() * 9000, convertToLocalDate(new Date()), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, Math.random() * 9000, convertToLocalDate(new Date()), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, Math.random() * 9000, convertToLocalDate(new Date()), TypeCompte.EPARGNE));
		};
	}
*/
	// Méthode de conversion de java.util.Date en java.time.LocalDate
	private LocalDate convertToLocalDate(Date date) {
		return date.toInstant()
				.atZone(java.time.ZoneId.systemDefault())
				.toLocalDate();
	}
}
