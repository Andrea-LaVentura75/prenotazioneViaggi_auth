package it.epicode.progetto.settimanale.prenotazioneViaggi.prenotazione;

import it.epicode.progetto.settimanale.prenotazioneViaggi.dipendente.Dipendente;
import it.epicode.progetto.settimanale.prenotazioneViaggi.dipendente.DipendenteService;
import it.epicode.progetto.settimanale.prenotazioneViaggi.exeption.PrenotazioneExe;
import it.epicode.progetto.settimanale.prenotazioneViaggi.viaggio.Viaggio;
import it.epicode.progetto.settimanale.prenotazioneViaggi.viaggio.ViaggioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class PrenotazioneService {

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private PrenotazioneRepo prenotazioneRepo;

    public List<Prenotazione> findAll() {
        return prenotazioneRepo.findAll();
    }

    public Prenotazione findById(Long id) {
        if (!prenotazioneRepo.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }

        return prenotazioneRepo.findById(id).get();
    }

    public Prenotazione createPrenotazione(@Valid PrenotazioneDto prenotazioneDto) {

        if (prenotazioneRepo.dataPrenotazioniGiorno(prenotazioneDto.getDipendenteId(), prenotazioneDto.getDataPrenotazione()) > 0) {
            throw new PrenotazioneExe("Il dipendente ha già effettuato una prenotazione oggi");
        }
        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(prenotazioneDto, prenotazione);

        Viaggio viaggio = viaggioService.findById(prenotazioneDto.getViaggioId());
        Dipendente dipendente = dipendenteService.findById(prenotazioneDto.getDipendenteId());

        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);

        if (prenotazioneRepo.dataPrenotazioneViaggio(prenotazioneDto.getDipendenteId(), viaggio.getData()) > 0) {
            throw new PrenotazioneExe("il dipendente ha già un viaggio per questa data");
        }

        return prenotazioneRepo.save(prenotazione);
    }

    public Prenotazione updatePrenotazione(Long id, @Valid PrenotazioneDto modifiedPrenotazione) {
        if (prenotazioneRepo.dataPrenotazioniGiorno(modifiedPrenotazione.getDipendenteId(), modifiedPrenotazione.getDataPrenotazione()) > 0) {
            throw new PrenotazioneExe("Il dipendente ha già effettuato una prenotazione oggi");
        }

        Prenotazione prenotazione = findById(id);
        BeanUtils.copyProperties(modifiedPrenotazione, prenotazione);

        Viaggio viaggio = viaggioService.findById(modifiedPrenotazione.getViaggioId());
        Dipendente dipendente = dipendenteService.findById(modifiedPrenotazione.getDipendenteId());

        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);

        return prenotazioneRepo.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        prenotazioneRepo.deleteById(id);
    }

}
