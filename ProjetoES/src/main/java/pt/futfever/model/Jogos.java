package pt.futfever.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Jogos {
    private int id;
    private Selecao selecao;
    private Arbitro arbitro;
    private Estadio estadio;
    private LocalDate data;
    private LocalTime hora;

    public Jogos(int id, Selecao selecao, Arbitro arbitro, Estadio estadio, LocalDate data, LocalTime hora) {
        this.id = id;
        this.selecao = selecao;
        this.arbitro = arbitro;
        this.estadio = estadio;
        this.data = data;
        this.hora = hora;

        selecao.adicionarJogo(this);
        arbitro.adicionarJogo(this);
        estadio.adicionarJogo(this);
    }

    public int getId() {
        return id;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return selecao.getNome() + " - " + data + " às " + hora + " no " + estadio.getNome();
    }
}