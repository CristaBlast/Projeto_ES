package pt.futfever.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jogo {
    private int id;
    private Date dataHora;
    private Selecao selecaoCasa;
    private Selecao selecaoFora;
    private EquipaArbitragem equipaArbitragem;
    private Estadio estadio;
    private List<Bilhete> bilhetesVendidos;
    private boolean vendaAberta;

    public Jogo() {
        this.bilhetesVendidos = new ArrayList<>();
        this.vendaAberta = false;
    }

    public Jogo(int id, Date dataHora, Selecao selecaoCasa, Selecao selecaoFora, Estadio estadio) {
        this.id = id;
        this.dataHora = dataHora;
        this.selecaoCasa = selecaoCasa;
        this.selecaoFora = selecaoFora;
        this.estadio = estadio;
        this.bilhetesVendidos = new ArrayList<>();
        this.vendaAberta = false;
    }

    public int getId() {
        return id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public Selecao getSelecaoCasa() {
        return selecaoCasa;
    }

    public Selecao getSelecaoFora() {
        return selecaoFora;
    }

    public EquipaArbitragem getEquipaArbitragem() {
        return equipaArbitragem;
    }

    public void setEquipaArbitragem(EquipaArbitragem equipaArbitragem) {
        this.equipaArbitragem = equipaArbitragem;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public List<Bilhete> getBilhetes() {
        return bilhetesVendidos;
    }

    public int getBilhetesVendidos() {
        int total = 0;
        for (Bilhete b : bilhetesVendidos) {
            total += b.getQuantidade();
        }
        return total;
    }

    public boolean isVendaAberta() {
        return vendaAberta;
    }

    public void abrirVenda() {
        this.vendaAberta = true;
    }

    public void fecharVenda() {
        this.vendaAberta = false;
    }

    /**
     * Regista a venda de um bilhete neste jogo, desde que a venda esteja
     * aberta e existam lugares disponíveis suficientes.
     */
    public boolean registarBilhete(Bilhete bilhete) {
        if (!vendaAberta) {
            return false;
        }
        if (bilhete.getQuantidade() > getLugaresDisponiveis()) {
            return false;
        }
        bilhetesVendidos.add(bilhete);
        return true;
    }

    public boolean verificarDisponibilidade() {
        return getLugaresDisponiveis() > 0;
    }

    public int getLugaresDisponiveis() {
        if (estadio == null) {
            return 0;
        }
        return estadio.getLotacao() - getBilhetesVendidos();
    }
}
