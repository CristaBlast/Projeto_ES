package pt.futfever.model;

public class Funcionario extends User {
    private String tipo;

    public Funcionario() {
        super();
    }

    public Funcionario(int id, String name, String email, String tipo) {
        super(id, name, email);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
