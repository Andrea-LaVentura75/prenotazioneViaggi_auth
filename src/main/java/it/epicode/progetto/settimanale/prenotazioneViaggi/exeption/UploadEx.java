package it.epicode.progetto.settimanale.prenotazioneViaggi.exeption;

public class UploadEx extends RuntimeException {
    public UploadEx(String message) {
        super(message);
    }

    public UploadEx(String message, Throwable cause) {
        super(message, cause);
    }
}
