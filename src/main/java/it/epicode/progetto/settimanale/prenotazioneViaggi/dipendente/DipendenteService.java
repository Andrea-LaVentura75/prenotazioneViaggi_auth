package it.epicode.progetto.settimanale.prenotazioneViaggi.dipendente;

import it.epicode.progetto.settimanale.prenotazioneViaggi.cloudinary.CloudinaryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Validated
@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepo dipendenteRepo;

    @Autowired
    private CloudinaryService cloudinaryService;


    public List<Dipendente> findAll() {
        return dipendenteRepo.findAll();

    }

    public Dipendente findById(Long id) {
        if (!dipendenteRepo.existsById(id)) {
            throw new EntityNotFoundException("Dipendente non trovato");
        } else {
            return dipendenteRepo.findById(id).get();
        }
    }


    public Dipendente createDipendente(@Valid Dipendente dipendente, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            Map result = cloudinaryService.uploader(file, "dipendenti");
            dipendente.setImmagineProfilo(result.get("url").toString());
        }
        return dipendenteRepo.save(dipendente);
    }


    public Dipendente updateDipendente(Long id, @Valid Dipendente dipendenteModificato, MultipartFile file) {

        Dipendente dipendente = findById(id);


        BeanUtils.copyProperties(dipendenteModificato, dipendente, "id");


        if (file != null && !file.isEmpty()) {
            Map result = cloudinaryService.uploader(file, "dipendenti");
            dipendente.setImmagineProfilo(result.get("url").toString());
        }


        return dipendenteRepo.save(dipendente);
    }




    public void deleteById(Long id) {
        dipendenteRepo.deleteById(id);
    }
}
