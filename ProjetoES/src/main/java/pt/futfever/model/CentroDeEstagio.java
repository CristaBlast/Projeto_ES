package pt.futfever.model;

public class CentroDeEstagio {
    private int id;
    private String nome;
    private String localizacao;
    private Selecao selecao;

    public CentroDeEstagio(int id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void associarSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    @Override
    public String toString() {
        return "CentroDeEstagio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}