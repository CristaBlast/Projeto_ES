package pt.futfever.model;

import java.util.Date;

public class Treinador extends User {
    private Date dataNascimento;
    private int anosExperiencia;
    private Selecao selecao;

    public Treinador(int id, String name, String email, Date dataNascimento, int anosExperiencia) {
        super(id, name, email);
        this.dataNascimento = dataNascimento;
        this.anosExperiencia = anosExperiencia;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }
}
