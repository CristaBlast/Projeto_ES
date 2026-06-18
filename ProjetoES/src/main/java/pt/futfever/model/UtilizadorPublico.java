package pt.futfever.model;

import java.util.LinkedList;

public class UtilizadorPublico extends User {
    private String cartaoCidadao;
    private String telefone;
    private LinkedList<Bilhete> bilhetes;

    public UtilizadorPublico(int id, String name, String email, String cartaoCidadao, String telefone) {
        super(id, name, email);
        this.cartaoCidadao = cartaoCidadao;
        this.telefone = telefone;
        this.bilhetes = new LinkedList<>();
    }

    public String getCartaoCidadao() {
        return cartaoCidadao;
    }

    public String getTelefone() {
        return telefone;
    }

    /**
     * Compra um bilhete para um jogo, desde que existam lugares disponíveis.
     * Regista o bilhete no jogo e no repositório central de dados.
     */
    public Bilhete comprarBilhete(Jogo jogo, int quantidade, String cc, String telefone) {
        if (jogo == null || !jogo.verificarDisponibilidade()) {
            System.out.println("Compra não efetuada: jogo indisponível ou sem lugares.");
            return null;
        }

        Bilhete bilhete = new Bilhete(jogo, this, quantidade);

        if (!jogo.registarBilhete(bilhete)) {
            System.out.println("Compra não efetuada: venda fechada ou lugares insuficientes.");
            return null;
        }

        bilhetes.add(bilhete);
        GestorDados.getInstance().adicionarBilhete(bilhete);
        bilhete.enviarConfirmacao();
        return bilhete;
    }

    public LinkedList<Jogo> visualizarCalendario() {
        LinkedList<Jogo> calendario = new LinkedList<>();
        for (Jogo jogo : GestorDados.getInstance().getJogos()) {
            calendario.add(jogo);
        }
        return calendario;
    }

    public LinkedList<Selecao> visualizarEquipas() {
        return new LinkedList<>(GestorDados.getInstance().getSelecoes());
    }

    public LinkedList<EquipaArbitragem> visualizarArbitros() {
        return new LinkedList<>(GestorDados.getInstance().getEquipasArbitragem());
    }

    public LinkedList<CentroDeEstagio> visualizarCamposDeTreino() {
        return new LinkedList<>(GestorDados.getInstance().getCentrosDeEstagio());
    }

    public LinkedList<Bilhete> getBilhetes() {
        return bilhetes;
    }
}
