package it.epicode.progetto.settimanale.prenotazioneViaggi.prenotazione;

import it.epicode.progetto.settimanale.prenotazioneViaggi.dipendente.Dipendente;
import it.epicode.progetto.settimanale.prenotazioneViaggi.viaggio.Viaggio;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;



    @Column(name = "data_prenotazione", nullable = false)
    private LocalDate dataPrenotazione;

    private String note;


    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;


}
