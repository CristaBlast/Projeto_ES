package pt.futfever.model;

public class Jogador {
    private int id;
    private String nome;
    private int idade;
    private String posicao;
    private int numero;
    private Selecao selecao;

    public Jogador(int id, String nome, int idade, String posicao, int numero) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.numero = numero;
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

    public int getIdade() {
        return idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public int getNumero() {
        return numero;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", posicao='" + posicao + '\'' +
                ", numero=" + numero +
                '}';
    }
}