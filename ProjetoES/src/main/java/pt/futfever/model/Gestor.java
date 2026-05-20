package pt.futfever.model;

public class Gestor {
    private int id;
    private String nome;

    public Gestor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Selecao criarSelecao(int id, String nome, String pais) {
        return new Selecao(id, nome, pais);
    }

    public Arbitro criarArbitro(int id, String nome, String nacionalidade) {
        return new Arbitro(id, nome, nacionalidade);
    }

    public Estadio criarEstadio(int id, String nome, String cidade, int capacidade) {
        return new Estadio(id, nome, cidade, capacidade);
    }

    public CentroDeEstagio criarCentroDeEstagio(int id, String nome, String localizacao) {
        return new CentroDeEstagio(id, nome, localizacao);
    }

    public Bilhete venderBilhete(
            int id,
            Jogos jogo,
            String numeroCartaoCidadao,
            String numeroTelefone,
            double preco,
            String lugar
    ) {
        Comprador comprador = new Comprador(numeroCartaoCidadao, numeroTelefone);
        return new Bilhete(id, jogo, comprador, preco, lugar);
    }

    public void definirCalendarioJogos() {
        System.out.println("Gestor a definir calendário dos jogos...");
    }

    public void atribuirEquipaArbitragem() {
        System.out.println("Gestor a atribuir equipa de arbitragem...");
    }

    public void gerirVendaBilhetes() {
        System.out.println("Gestor a gerir venda de bilhetes...");
    }

    public void deslocarEquipa() {
        System.out.println("Gestor a deslocar equipa...");
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Gestor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}