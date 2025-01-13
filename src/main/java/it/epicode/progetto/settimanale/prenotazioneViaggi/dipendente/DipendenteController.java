package it.epicode.progetto.settimanale.prenotazioneViaggi.dipendente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
@PreAuthorize("isAuthenticated()")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;



    @GetMapping
    public ResponseEntity<List<Dipendente>> listAllDipendenti() {
        return ResponseEntity.ok(dipendenteService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(dipendenteService.findById(id));
    }


    @PostMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Dipendente> createDipendente(@RequestParam("username") String username,
                                                       @RequestParam("nome") String nome,
                                                       @RequestParam("cognome") String cognome,
                                                       @RequestParam("email") String email,
                                                       @RequestParam("immagineProfilo") MultipartFile immagineProfilo) {
        Dipendente nuovoDipendente = new Dipendente();
        nuovoDipendente.setUsername(username);
        nuovoDipendente.setNome(nome);
        nuovoDipendente.setCognome(cognome);
        nuovoDipendente.setEmail(email);
        Dipendente dipendenteCreato = dipendenteService.createDipendente(nuovoDipendente, immagineProfilo);
        return ResponseEntity.ok(dipendenteCreato);

    }



    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Dipendente> updateDipendente(
            @PathVariable Long id,
            @RequestParam("username") String username,
            @RequestParam("nome") String nome,
            @RequestParam("cognome") String cognome,
            @RequestParam("email") String email,
            @RequestParam(value = "immagineProfilo", required = false) MultipartFile file) {


        Dipendente dipendenteEsistente = dipendenteService.findById(id);


        dipendenteEsistente.setUsername(username);
        dipendenteEsistente.setNome(nome);
        dipendenteEsistente.setCognome(cognome);
        dipendenteEsistente.setEmail(email);


        Dipendente dipendenteAggiornato = dipendenteService.updateDipendente(id, dipendenteEsistente, file);

        return ResponseEntity.ok(dipendenteAggiornato);
    }





    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
