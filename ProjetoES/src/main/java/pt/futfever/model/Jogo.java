package pt.futfever.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Jogo {
    private int id;
    private Date dataHora;
    private Selecao selecaoCasa;
    private Selecao selecaoFora;
    private EquipaArbitragem equipaArbitragem;
    private Estadio estadio;
    private int bilhetesVendidos;

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

    public Estadio getEstadio() {
        return estadio;
    }

    public int getBilhetesVendidos() {
        return bilhetesVendidos;
    }

    public boolean verificarDisponibilidade(){
        return true; //TODO lotacaoDoEstadio-bilhetesVedidos
    }

    public void abrirVenda(){

    }

    public int getLugaresDisponiveis()
    {
        return 0;
    }


}