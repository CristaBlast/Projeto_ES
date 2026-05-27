package pt.futfever.model;

import java.util.Date;

public class Jogador {
    private int numCamisola;
    private String posicao;
    private Date dataNascimento;
    private String nacionalidade;
    private Selecao selecao;

    public Jogador(int numCamisola, String posicao, Date dataNascimento, String nacionalidade, Selecao selecao) {
        this.numCamisola = numCamisola;
        this.posicao = posicao;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.selecao = selecao;
    }

    public int getNumCamisola() {
        return numCamisola;
    }

    public String getPosicao() {
        return posicao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    //TODO QRCODE IF NEEDED XD

    public Boolean validarAcesso()
    {
        return true;
    }
}