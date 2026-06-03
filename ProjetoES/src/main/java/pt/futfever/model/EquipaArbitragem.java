package pt.futfever.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipaArbitragem extends Jogo{
    private int id;
    private String nome;
    private String nacionalidade;
    private List<Jogo> jogos;

    public EquipaArbitragem(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    } //TODO must be verified nationality of the judge before being added to the game

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public Boolean disponibilidade(Date data){
        return true;
    }

}