package pt.futfever.model;

public class Gestor extends User {

    public Gestor(int id, String nome, String email, String password) {
        super(id, nome, email, password);
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

    public void gerirVendaBilhetes() {
        System.out.println("Gestor a gerir venda de bilhetes...");
    }

    public void definirCalendarioJogos() {
        System.out.println("Gestor a definir calendário dos jogos...");
    }

    public void atribuirEquipaArbitragem() {
        System.out.println("Gestor a atribuir equipa de arbitragem aos jogos...");
    }

    public void deslocarEquipa() {
        System.out.println("Gestor a deslocar equipa...");
    }
}