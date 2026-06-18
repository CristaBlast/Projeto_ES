package pt.futfever.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipaArbitragem {
    private int id;
    private String nome;
    private String nacionalidade;
    private List<Jogo> jogos;

    public EquipaArbitragem(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.jogos = new ArrayList<>();
    }

    /**
     * Adiciona um jogo à equipa de arbitragem, desde que a sua nacionalidade
     * não coincida com a de nenhuma das seleções em competição (regra de imparcialidade).
     */
    public boolean adicionarJogo(Jogo jogo) {
        if (jogo == null) {
            return false;
        }
        Selecao casa = jogo.getSelecaoCasa();
        Selecao fora = jogo.getSelecaoFora();

        boolean conflitoNacionalidade =
                (casa != null && nacionalidade.equalsIgnoreCase(casa.getNome())) ||
                (fora != null && nacionalidade.equalsIgnoreCase(fora.getNome()));

        if (conflitoNacionalidade) {
            System.out.println("Não é possível atribuir esta equipa de arbitragem: conflito de nacionalidade.");
            return false;
        }

        if (!disponibilidade(jogo.getDataHora())) {
            System.out.println("Equipa de arbitragem indisponível na data do jogo.");
            return false;
        }

        jogos.add(jogo);
        return true;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    /**
     * Verifica se a equipa de arbitragem está livre na data indicada,
     * ou seja, se não tem nenhum outro jogo já agendado nessa data.
     */
    public Boolean disponibilidade(Date data) {
        if (data == null) {
            return true;
        }
        for (Jogo j : jogos) {
            if (j.getDataHora() != null && j.getDataHora().equals(data)) {
                return false;
            }
        }
        return true;
    }
}
