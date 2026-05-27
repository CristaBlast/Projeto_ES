package pt.futfever.model;

public class Gestor {
    private String username;
    private String passwordHash;

    public Boolean login(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        return true;
    }

    public void gerirEquipa()
    {

    }

    public void gerirEstadio()
    {

    }

    public void gerirGrupos()
    {

    }

    public void gerirJogos()
    {

    }

    public void abrirVendaBilhetes(Jogo jogo)
    {

    }

    public void gerirAbitragem(Jogo jogo)
    {

    }

    public void gerirCentroEstagios(Selecao selecao)
    {

    }

    public void registarDeslocacao(Selecao selecao)
    {

    }
}