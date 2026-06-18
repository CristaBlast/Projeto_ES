package pt.futfever.model;

import java.util.Date;

public class Jogador extends User {
    private int numCamisola;
    private String posicao;
    private Date dataNascimento;
    private String nacionalidade;
    private Selecao selecao;

    public Jogador(int id, String name, String email, int numCamisola, String posicao,
                    Date dataNascimento, String nacionalidade, Selecao selecao) {
        super(id, name, email);
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

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    /**
     * Valida o acesso do jogador às instalações (estádio/centro de estágio),
     * confirmando que pertence a uma seleção registada.
     */
    public Boolean validarAcesso() {
        return selecao != null;
    }
}
