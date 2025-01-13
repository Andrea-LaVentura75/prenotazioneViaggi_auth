package it.epicode.progetto.settimanale.prenotazioneViaggi.viaggio;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String destinazione;

    @FutureOrPresent(message = "La data di partenza inserita non è valida")
    private LocalDate data;


    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;
}

