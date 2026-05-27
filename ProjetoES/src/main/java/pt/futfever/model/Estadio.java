package pt.futfever.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Estadio {
    private int id;
    private String nome;
    private double latitude;
    private double longitude;
    private int lotacao;
    private List<Jogo> jogos;

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

   public boolean disponivel(Jogo jogo)
   {
       return true;
   }

}