package it.epicode.progetto.settimanale.prenotazioneViaggi.viaggio;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepo viaggioRepo;


    public List<Viaggio> findAll() {
        return viaggioRepo.findAll();

    }

    public Viaggio findById(Long id) {
        if (!viaggioRepo.existsById(id)) {
            throw new EntityNotFoundException("il Viaggio non è stato trovato");
        } else {
            return viaggioRepo.findById(id).get();
        }
    }

    public Viaggio createViaggio(@RequestBody Viaggio viaggio) {

        return viaggioRepo.save(viaggio);
    }

    public Viaggio updateViaggio(Long id, Viaggio viaggioModificato) {
        Viaggio viaggio = findById(id);
        BeanUtils.copyProperties(viaggioModificato, viaggio);
        return viaggioRepo.save(viaggioModificato);
    }

    public Viaggio updateStatoViaggio(Long id, Viaggio modifiedViaggio){
        Viaggio viaggio= findById(id);
        viaggio.setStatoViaggio(modifiedViaggio.getStatoViaggio());
        return viaggioRepo.save(viaggio);
    }


    public void deleteById(Long id) {
        viaggioRepo.deleteById(id);
    }
}
