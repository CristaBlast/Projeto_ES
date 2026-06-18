package pt.futfever.model;

import java.util.UUID;

/**
 * Representa o código QR único associado a um Bilhete.
 * É usado para validar a entrada de um espectador no Estádio.
 */
public class Qrcode {
    private String codigo;
    private Bilhete bilhete;
    private boolean validado;

    public Qrcode(Bilhete bilhete) {
        this.bilhete = bilhete;
        this.codigo = gerarCodigo();
        this.validado = false;
    }

    private String gerarCodigo() {
        // Gera um identificador único para representar o conteúdo do QR Code
        return UUID.randomUUID().toString();
    }

    public String getCodigo() {
        return codigo;
    }

    public Bilhete getBilhete() {
        return bilhete;
    }

    public boolean isValidado() {
        return validado;
    }

    /**
     * Valida o QR code à entrada do estádio.
     * Só pode ser validado uma vez (evita reutilização do mesmo bilhete).
     */
    public boolean validar() {
        if (validado) {
            return false; // já foi usado anteriormente
        }
        if (bilhete == null) {
            return false;
        }
        this.validado = true;
        return true;
    }
}
