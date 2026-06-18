package pt.futfever.model;

import java.util.ArrayList;
import java.util.List;

public class Estadio {
    private int id;
    private String nome;
    private double latitude;
    private double longitude;
    private int lotacao;
    private List<Jogo> jogos;

    public Estadio() {
        this.jogos = new ArrayList<>();
    }

    public Estadio(int id, String nome, double latitude, double longitude, int lotacao) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lotacao = lotacao;
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getLotacao() {
        return lotacao;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    /**
     * Verifica se o estádio está livre na data/hora do jogo indicado,
     * ou seja, se não tem nenhum outro jogo agendado no mesmo dia.
     */
    public boolean disponivel(Jogo jogo) {
        if (jogo == null || jogo.getDataHora() == null) {
            return true;
        }
        for (Jogo j : jogos) {
            if (j.getDataHora() != null && j.getDataHora().equals(jogo.getDataHora())) {
                return false;
            }
        }
        return true;
    }
}
