package pt.futfever.model;

import java.util.LinkedList;

public class Grupo {
    private int id;
    private String nome;
    private LinkedList<Selecao> selecoes;

    public Grupo() {
        this.selecoes = new LinkedList<>();
    }

    public Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.selecoes = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LinkedList<Selecao> getSelecoes() {
        return this.selecoes;
    }

    public void adicionarSelecao(Selecao selecao) {
        this.selecoes.add(selecao);
        selecao.setGrupo(this);
    }

    public boolean selecaoPertence(Selecao selecao) {
        return this.selecoes.contains(selecao);
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", selecoes=" + selecoes.size() +
                '}';
    }
}
