package pt.futfever.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Selecao {
    private int id;
    private String nome;
    private boolean apurada;

    private Treinador treinador;
    private CentroDeEstagio centroDeEstagio;

    private List<Jogador> jogadores;
    private List<Jogo> jogos;
    private Grupo grupo;

    public String getNome() {
        return nome;
    }

    public boolean isApurada() {
        return apurada;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public CentroDeEstagio getCentroDeEstagio() {
        return centroDeEstagio;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void adicionarJogador(Jogador jogador){
        jogadores.add(jogador);
    }
    public void removerJogador(Jogador jogador){
        jogadores.remove(jogador);
    }
    public LinkedList<Jogador> getPlantel()
    {
        return new LinkedList<>(jogadores);
    }

    public void setCentroDeEstagio(CentroDeEstagio centroDeEstagio) {
        this.centroDeEstagio = centroDeEstagio;
    }
}