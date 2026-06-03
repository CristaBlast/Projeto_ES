package pt.futfever.model;

import java.util.LinkedList;

public class Grupo extends Selecao{
    private int id;
    private String nome;
    private LinkedList<Selecao> selecao;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

//    public LinkedList<Selecao> getSelecao() {
//        return this.selecao;
//    }

    public void adicionarSelecao(Selecao selecao) {
        this.selecao.add(selecao);
    }

    public boolean selecaoPertence(Selecao selecao) {
        return this.selecao.contains(selecao);
    }
}
