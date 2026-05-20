package pt.futfever.model;

import java.util.ArrayList;
import java.util.List;

public class Estadio {
    private int id;
    private String nome;
    private String cidade;
    private int capacidade;
    private List<Jogos> jogos;

    public Estadio(int id, String nome, String cidade, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.capacidade = capacidade;
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogos jogo) {
        jogos.add(jogo);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<Jogos> getJogos() {
        return jogos;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", capacidade=" + capacidade +
                '}';
    }
}