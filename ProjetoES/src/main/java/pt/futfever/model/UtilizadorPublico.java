package pt.futfever.model;

import java.util.LinkedList;

public class UtilizadorPublico extends User {
    private String cartaoCidadao;
    private String telefone;
    private LinkedList<Jogo> jogos;
    private LinkedList<Selecao> selecao;
    private LinkedList<EquipaArbitragem> equipaArbitragem;
    private LinkedList<CentroDeEstagio> centroDeEstagio;

    public UtilizadorPublico(int id, String name, String email, String cartaoCidadao, String telefone) {
        super(id, name, email);
        this.cartaoCidadao = cartaoCidadao;
        this.telefone = telefone;
        jogos = new LinkedList<>();
        selecao = new LinkedList<>();
        equipaArbitragem = new LinkedList<>();
        centroDeEstagio = new LinkedList<>();
    }

    public String getCartaoCidadao() {
        return cartaoCidadao;
    }

    public String getTelefone() {
        return telefone;
    }

    public Bilhete comprarBilhete(Jogo jogo, int quantidade, String cc, String telefone) {
        Bilhete bilhete = new Bilhete();
        return bilhete;
    }

    public LinkedList<Jogo> visulizarCalendario() {
        return jogos;
    }

    public LinkedList<Selecao> visulizarEquipas() {
        return selecao;
    }

    public LinkedList<EquipaArbitragem> visulizarArbitros() {
        return equipaArbitragem;
    }

    public LinkedList<CentroDeEstagio> visulizarCamposDeTreino() {
        return centroDeEstagio;
    }


}
