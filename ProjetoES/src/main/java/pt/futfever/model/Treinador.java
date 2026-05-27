package pt.futfever.model;

import java.util.Date;

public class Treinador extends User{
    private Date datanascimento;
    private int anosExperiencia;

    public Treinador(Date datanascimento, int anosExperiencia) {
        super();
        this.datanascimento = datanascimento;
        this.anosExperiencia = anosExperiencia;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }
}