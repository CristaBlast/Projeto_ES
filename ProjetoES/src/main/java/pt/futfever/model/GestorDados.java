package pt.futfever.model;

import java.util.ArrayList;
import java.util.List;

/**
 * GestorDados - Repositório central de dados da aplicação (padrão Singleton).
 *
 * Mantém em memória todas as coleções de entidades do sistema (Seleções, Jogos,
 * Estádios, Equipas de Arbitragem, Grupos, Centros de Estágio e Utilizadores),
 * simulando uma base de dados única e partilhada por toda a aplicação.
 *
 * Garante que existe apenas uma instância deste repositório durante a execução,
 * acessível através de GestorDados.getInstance().
 */
public class GestorDados {

    private static GestorDados instance;

    private List<Selecao> selecoes;
    private List<Jogo> jogos;
    private List<Estadio> estadios;
    private List<EquipaArbitragem> equipasArbitragem;
    private List<Grupo> grupos;
    private List<CentroDeEstagio> centrosDeEstagio;
    private List<Deslocacao> deslocacoes;
    private List<User> utilizadores;
    private List<Bilhete> bilhetes;

    // Construtor privado - impede a criação de instâncias fora desta classe
    private GestorDados() {
        selecoes = new ArrayList<>();
        jogos = new ArrayList<>();
        estadios = new ArrayList<>();
        equipasArbitragem = new ArrayList<>();
        grupos = new ArrayList<>();
        centrosDeEstagio = new ArrayList<>();
        deslocacoes = new ArrayList<>();
        utilizadores = new ArrayList<>();
        bilhetes = new ArrayList<>();
    }

    /**
     * Ponto de acesso único à instância do GestorDados.
     * Cria a instância na primeira chamada (lazy initialization).
     */
    public static synchronized GestorDados getInstance() {
        if (instance == null) {
            instance = new GestorDados();
        }
        return instance;
    }

    // ---------- Seleções ----------
    public void adicionarSelecao(Selecao selecao) {
        selecoes.add(selecao);
    }

    public List<Selecao> getSelecoes() {
        return selecoes;
    }

    public Selecao procurarSelecaoPorId(int id) {
        for (Selecao s : selecoes) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // ---------- Jogos ----------
    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public Jogo procurarJogoPorId(int id) {
        for (Jogo j : jogos) {
            if (j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    // ---------- Estádios ----------
    public void adicionarEstadio(Estadio estadio) {
        estadios.add(estadio);
    }

    public List<Estadio> getEstadios() {
        return estadios;
    }

    // ---------- Equipas de Arbitragem ----------
    public void adicionarEquipaArbitragem(EquipaArbitragem equipa) {
        equipasArbitragem.add(equipa);
    }

    public List<EquipaArbitragem> getEquipasArbitragem() {
        return equipasArbitragem;
    }

    // ---------- Grupos ----------
    public void adicionarGrupo(Grupo grupo) {
        grupos.add(grupo);
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    // ---------- Centros de Estágio ----------
    public void adicionarCentroDeEstagio(CentroDeEstagio centro) {
        centrosDeEstagio.add(centro);
    }

    public List<CentroDeEstagio> getCentrosDeEstagio() {
        return centrosDeEstagio;
    }

    // ---------- Deslocações ----------
    public void adicionarDeslocacao(Deslocacao deslocacao) {
        deslocacoes.add(deslocacao);
    }

    public List<Deslocacao> getDeslocacoes() {
        return deslocacoes;
    }

    // ---------- Utilizadores ----------
    public void adicionarUtilizador(User utilizador) {
        utilizadores.add(utilizador);
    }

    public List<User> getUtilizadores() {
        return utilizadores;
    }

    public User procurarUtilizadorPorEmail(String email) {
        for (User u : utilizadores) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    // ---------- Bilhetes ----------
    public void adicionarBilhete(Bilhete bilhete) {
        bilhetes.add(bilhete);
    }

    public List<Bilhete> getBilhetes() {
        return bilhetes;
    }

    /**
     * Reinicia todos os dados do repositório (útil em testes).
     */
    public void limparTudo() {
        selecoes.clear();
        jogos.clear();
        estadios.clear();
        equipasArbitragem.clear();
        grupos.clear();
        centrosDeEstagio.clear();
        deslocacoes.clear();
        utilizadores.clear();
        bilhetes.clear();
    }
}
