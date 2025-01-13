package it.epicode.progetto.settimanale.prenotazioneViaggi.dipendente;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @NotBlank(message = "Il campo Username non può essere vuoto")
    private String username;

    @NotBlank(message = "Il Campo Nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il Campo Cognome non può essere vuoto")
    private String cognome;

    @Email(message = "Inserisci un Email Valida")
    private String email;


    private String immagineProfilo;


}
