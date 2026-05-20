package pt.futfever.model;

public class Treinador {
    private int id;
    private String nome;
    private String nacionalidade;
    private Selecao selecao;

    public Treinador(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public void associarSelecao(Selecao selecao) {
        this.selecao = selecao;
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

    public Selecao getSelecao() {
        return selecao;
    }

    @Override
    public String toString() {
        return nome + " - " + nacionalidade;
    }
}