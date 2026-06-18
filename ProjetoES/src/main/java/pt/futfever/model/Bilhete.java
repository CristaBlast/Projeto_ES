package pt.futfever.model;

import java.util.Date;

public class Bilhete {
    private static final double PRECO_UNITARIO = 100.0;

    private int id;
    private String codigoBilhete; // referência textual do bilhete
    private int quantidade;
    private Date dataCompra;
    private double precoTotal;

    private Jogo jogo;
    private UtilizadorPublico comprador;
    private Qrcode qrcode;

    public Bilhete() {
        this.dataCompra = new Date();
    }

    public Bilhete(Jogo jogo, UtilizadorPublico comprador, int quantidade) {
        this.jogo = jogo;
        this.comprador = comprador;
        this.quantidade = quantidade;
        this.dataCompra = new Date();
        this.precoTotal = calcularPrecoTotal();
        this.codigoBilhete = gerarCodigoBilhete();
        this.qrcode = new Qrcode(this);
    }

    private String gerarCodigoBilhete() {
        // Combina o id do jogo e a data de compra para criar uma referência legível
        int refJogo = (jogo != null) ? jogo.getId() : 0;
        return "FF-" + refJogo + "-" + dataCompra.getTime();
    }

    private double calcularPrecoTotal() {
        return quantidade * PRECO_UNITARIO;
    }

    public void enviarConfirmacao() {
        if (comprador == null) {
            System.out.println("Não é possível enviar confirmação: comprador desconhecido.");
            return;
        }
        System.out.println("A enviar confirmação de compra para " + comprador.getEmail()
                + " | Bilhete: " + codigoBilhete
                + " | Quantidade: " + quantidade
                + " | Total: " + precoTotal + "€");
    }

    public String getBilheteDigital() {
        return codigoBilhete;
    }

    public Qrcode getQrcode() {
        return qrcode;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public UtilizadorPublico getComprador() {
        return comprador;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }
}
