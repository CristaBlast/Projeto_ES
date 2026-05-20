package pt.futfever.model;

public class Comprador {
    private String numeroCartaoCidadao;
    private String numeroTelefone;

    public Comprador(String numeroCartaoCidadao, String numeroTelefone) {
        this.numeroCartaoCidadao = numeroCartaoCidadao;
        this.numeroTelefone = numeroTelefone;
    }

    public String getNumeroCartaoCidadao() {
        return numeroCartaoCidadao;
    }

    public void setNumeroCartaoCidadao(String numeroCartaoCidadao) {
        this.numeroCartaoCidadao = numeroCartaoCidadao;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "numeroCartaoCidadao='" + numeroCartaoCidadao + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                '}';
    }
}