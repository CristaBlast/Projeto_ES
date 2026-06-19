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
    private List<Deslocacao> deslocacoes;

    public Selecao() {
        this.jogadores = new ArrayList<>();
        this.jogos = new ArrayList<>();
        this.deslocacoes = new ArrayList<>();
    }

    public Selecao(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.apurada = false;
        this.jogadores = new ArrayList<>();
        this.jogos = new ArrayList<>();
        this.deslocacoes = new ArrayList<>();
    }

    public Selecao(int id, String nome, List<Jogador> jogadores, Treinador treinador) {
        this.id = id;
        this.nome = nome;
        this.apurada = false;
        this.jogadores = new ArrayList<>(jogadores);
        this.treinador = treinador;
        this.jogos = new ArrayList<>();
        this.deslocacoes = new ArrayList<>();
        // Ligar cada jogador de volta a esta seleção
        for (Jogador j : this.jogadores) {
            j.setSelecao(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isApurada() {
        return apurada;
    }

    public void setApurada(boolean apurada) {
        this.apurada = apurada;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public CentroDeEstagio getCentroDeEstagio() {
        return centroDeEstagio;
    }

    public void setCentroDeEstagio(CentroDeEstagio centroDeEstagio) {
        this.centroDeEstagio = centroDeEstagio;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Deslocacao> getDeslocacoes() {
        return deslocacoes;
    }

    public void adicionarDeslocacao(Deslocacao deslocacao) {
        deslocacoes.add(deslocacao);
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    public LinkedList<Jogador> getPlantel() {
        return new LinkedList<>(jogadores);
    }

    @Override
    public String toString() {
        return "Selecao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", apurada=" + apurada +
                '}';
    }
}
