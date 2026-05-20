package pt.futfever.model;

import java.util.ArrayList;
import java.util.List;

public class Arbitro {
    private int id;
    private String nome;
    private String nacionalidade;
    private List<Jogos> jogos;

    public Arbitro(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public List<Jogos> getJogos() {
        return jogos;
    }

    @Override
    public String toString() {
        return "Arbitro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
}