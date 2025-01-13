package it.epicode.progetto.settimanale.prenotazioneViaggi.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
