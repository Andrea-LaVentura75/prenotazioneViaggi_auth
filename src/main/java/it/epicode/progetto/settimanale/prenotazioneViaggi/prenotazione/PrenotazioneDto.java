package it.epicode.progetto.settimanale.prenotazioneViaggi.prenotazione;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDto {

    @NotNull(message = "La Data della Prenotazione non può essere vuota")
    private LocalDate dataPrenotazione;

    private String note;

    @Min(value = 1, message = "Inserisci un Id maggiore o uguale a 1")
    private Long viaggioId;

    @Min(value = 1, message = "Inserisci un Id maggiore o uguale a 1")
    private Long dipendenteId;
}
