package pt.futfever.model;

public class Bilhete {
    private int id;
    private Jogos jogo;
    private Comprador comprador;
    private double preco;
    private String lugar;
    private boolean usado;

    public Bilhete(int id, Jogos jogo, Comprador comprador, double preco, String lugar) {
        this.id = id;
        this.jogo = jogo;
        this.comprador = comprador;
        this.preco = preco;
        this.lugar = lugar;
        this.usado = false;
    }

    public void validarEntrada() {
        if (!usado) {
            usado = true;
            System.out.println("Entrada validada com sucesso.");
        } else {
            System.out.println("Bilhete já foi utilizado.");
        }
    }

    public int getId() {
        return id;
    }

    public Jogos getJogo() {
        return jogo;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public double getPreco() {
        return preco;
    }

    public String getLugar() {
        return lugar;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return "Bilhete{" +
                "id=" + id +
                ", jogo=" + jogo +
                ", comprador=" + comprador +
                ", preco=" + preco +
                ", lugar='" + lugar + '\'' +
                ", usado=" + usado +
                '}';
    }
}