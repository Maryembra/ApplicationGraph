package ma.projet.graph.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double solde;

    @JsonFormat(pattern = "yyyy-MM-dd")  // Spécification du format ISO 8601 pour la date
    private LocalDate dateCreation;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;
    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions;
    public Compte(Long id, double solde, LocalDate dateCreation, TypeCompte type) {
        this.id = id;
        this.solde = solde;
        this.dateCreation = dateCreation;

        this.type = type;

    }
}