package ma.projet.graph.controllers;

import lombok.AllArgsConstructor;
import ma.projet.graph.entities.Compte;
import ma.projet.graph.entities.Transaction;
import ma.projet.graph.entities.TypeCompte;
import ma.projet.graph.repositories.CompteRepository;
import ma.projet.graph.repositories.TransactionRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CompteControllerGraphQL {

    private CompteRepository compteRepository;
    private TransactionRepository transactionRepository;
    @QueryMapping
    public List<Compte> allComptes(){
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte compteById(@Argument Long id){
        Compte compte =  compteRepository.findById(id).orElse(null);
        if(compte == null) throw new RuntimeException(String.format("Compte %s not found", id));
        else return compte;
    }

    @MutationMapping
    public Compte saveCompte(@Argument Compte compte){
       return compteRepository.save(compte);
    }

    @QueryMapping
    public Map<String, Object> totalSolde() {
        long count = compteRepository.count(); // Nombre total de comptes
        double sum = compteRepository.sumSoldes(); // Somme totale des soldes
        double average = count > 0 ? sum / count : 0; // Moyenne des soldes

        return Map.of(
                "count", count,
                "sum", sum,
                "average", average
        );
    }
    @QueryMapping
    public List<Compte> findByType(@Argument TypeCompte type) {
        List<Compte> comptes = compteRepository.findByType(type);
        if (comptes.isEmpty()) {
            throw new RuntimeException(String.format("No accounts found for type: %s", type));
        }
        return comptes;
    }
    @MutationMapping
    public String deleteById(@Argument Long id) {
        if (compteRepository.existsById(id)) {
            // Supprimer les transactions associées avant de supprimer le compte
            List<Transaction> transactions = transactionRepository.findByCompteId(id);
            transactionRepository.deleteAll(transactions);

            // Supprimer le compte
            compteRepository.deleteById(id);
            return String.format("Compte with ID %d has been deleted successfully.", id);
        } else {
            throw new RuntimeException(String.format("Compte with ID %d not found.", id));
        }
    }



}
