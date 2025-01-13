package it.epicode.progetto.settimanale.prenotazioneViaggi.viaggio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;



    @GetMapping
    public ResponseEntity<List<Viaggio>> listAllDipendenti() {
        return ResponseEntity.ok(viaggioService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> findById(@PathVariable Long id) {
        return ResponseEntity.ok(viaggioService.findById(id));
    }


    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody Viaggio viaggio) {
        return ResponseEntity.ok(viaggioService.createViaggio(viaggio));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> updateViaggio(@PathVariable Long id, @RequestBody Viaggio viaggioModificato) {
        return ResponseEntity.ok(viaggioService.updateViaggio(id, viaggioModificato));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Viaggio> updateStatoViaggio(@PathVariable Long id, @RequestBody Viaggio viaggioModificato) {
        return ResponseEntity.ok(viaggioService.updateStatoViaggio(id, viaggioModificato));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
