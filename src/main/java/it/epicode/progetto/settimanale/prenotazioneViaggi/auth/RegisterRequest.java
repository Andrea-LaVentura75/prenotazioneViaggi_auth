package it.epicode.progetto.settimanale.prenotazioneViaggi.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
