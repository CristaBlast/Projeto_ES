package pt.futfever.model;

import java.util.Date;

public class Deslocacao {
    private int id;
    private String origem;
    private String destino;
    private Date dataHora;
    private Selecao selecao;
    private Jogo eventoAssociado;

    public Deslocacao(int id, String origem, String destino, Date dataHora, Selecao selecao) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.selecao = selecao;
    }

    public String getOrigem() {
        return origem;
    }

    public int getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public Jogo getEventoAssociado() {
        return eventoAssociado;
    }

    public void setEventoAssociado(Jogo eventoAssociado) {
        this.eventoAssociado = eventoAssociado;
    }
}
