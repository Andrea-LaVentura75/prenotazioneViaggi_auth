package it.epicode.progetto.settimanale.prenotazioneViaggi.prenotazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
    @Query("SELECT COUNT(p) FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.dataPrenotazione = :dataPrenotazione")
    public int dataPrenotazioniGiorno(Long dipendenteId, LocalDate dataPrenotazione);


    @Query("SELECT COUNT(p) FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.viaggio.data = :data")
    int dataPrenotazioneViaggio(Long dipendenteId, LocalDate data);

}
