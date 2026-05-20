package pt.futfever.model;

import java.util.ArrayList;
import java.util.List;

public class Selecao {
    private int id;
    private String nome;
    private String pais;

    private Treinador treinador;
    private CentroDeEstagio centroDeEstagio;

    private List<Jogador> jogadores;
    private List<Jogos> jogos;

    public Selecao(int id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.jogadores = new ArrayList<>();
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
        jogador.associarSelecao(this);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    public void adicionarJogo(Jogos jogo) {
        jogos.add(jogo);
    }

    public void definirTreinador(Treinador treinador) {
        this.treinador = treinador;
        treinador.associarSelecao(this);
    }

    public void definirCentroDeEstagio(CentroDeEstagio centroDeEstagio) {
        this.centroDeEstagio = centroDeEstagio;
        centroDeEstagio.associarSelecao(this);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
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

    public List<Jogos> getJogos() {
        return jogos;
    }

    @Override
    public String toString() {
        return "Selecao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}