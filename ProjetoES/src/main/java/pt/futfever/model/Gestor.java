package pt.futfever.model;

public class Gestor extends Funcionario {
    private String username;
    private String passwordHash;

    public Gestor(int id, String name, String email, String username, String passwordHash) {
        super(id, name, email, "Gestor");
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Boolean login(String username, String passwordHash) {
        return this.username != null
                && this.username.equals(username)
                && this.passwordHash != null
                && this.passwordHash.equals(passwordHash);
    }

    public void gerirEquipa(Selecao selecao) {
        GestorDados repo = GestorDados.getInstance();
        if (repo.procurarSelecaoPorId(selecao.getId()) == null) {
            repo.adicionarSelecao(selecao);
        }
    }

    public void gerirEstadio(Estadio estadio) {
        GestorDados repo = GestorDados.getInstance();
        if (!repo.getEstadios().contains(estadio)) {
            repo.adicionarEstadio(estadio);
        }
    }

    public void gerirGrupos(Grupo grupo) {
        GestorDados repo = GestorDados.getInstance();
        if (!repo.getGrupos().contains(grupo)) {
            repo.adicionarGrupo(grupo);
        }
    }

    public void gerirJogos(Jogo jogo) {
        GestorDados repo = GestorDados.getInstance();
        if (!repo.getJogos().contains(jogo)) {
            repo.adicionarJogo(jogo);
        }
    }

    public void abrirVendaBilhetes(Jogo jogo) {
        jogo.abrirVenda();
    }

    public void gerirArbitragem(Jogo jogo, EquipaArbitragem equipaArbitragem) {
        equipaArbitragem.adicionarJogo(jogo);
        jogo.setEquipaArbitragem(equipaArbitragem);
    }

    public void gerirCentroEstagios(Selecao selecao, CentroDeEstagio centroDeEstagio) {
        centroDeEstagio.associarSelecao(selecao);
        GestorDados repo = GestorDados.getInstance();
        if (!repo.getCentrosDeEstagio().contains(centroDeEstagio)) {
            repo.adicionarCentroDeEstagio(centroDeEstagio);
        }
    }

    public void registarDeslocacao(Selecao selecao, Deslocacao deslocacao) {
        selecao.adicionarDeslocacao(deslocacao);
        GestorDados.getInstance().adicionarDeslocacao(deslocacao);
    }
}
