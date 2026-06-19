package pt.futfever.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
    private List<Jogador> jogadores;

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
        jogadores = new ArrayList<>();
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

    // ---------- Jogadores ----------
    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    // ---------- População inicial de dados ----------

    /**
     * Povoa o repositório com dados realistas de três seleções (Portugal, Espanha,
     * França), incluindo treinador e plantel completo para cada uma.
     * Deve ser chamado uma única vez no arranque da aplicação.
     */
    public void popularDados() {

        // ── PORTUGAL ──────────────────────────────────────────────
        Treinador treinadorPortugal = new Treinador(
                101, "Roberto Martínez", "r.martinez@fpf.pt",
                criarData(1973, Calendar.JANUARY, 13), 20);

        List<Jogador> plantilhaPortugal = Arrays.asList(
            criarJogador(1,  "Diogo Costa",       1,  "GR",  1999, Calendar.SEPTEMBER, 19, "Portugal"),
            criarJogador(2,  "João Cancelo",       20, "DD",  1994, Calendar.MAY,       27, "Portugal"),
            criarJogador(3,  "Rúben Dias",         4,  "DC",  1997, Calendar.MAY,       14, "Portugal"),
            criarJogador(4,  "Pepe",               3,  "DC",  1983, Calendar.FEBRUARY,  26, "Portugal"),
            criarJogador(5,  "Nuno Mendes",        19, "DE",  2002, Calendar.JUNE,      19, "Portugal"),
            criarJogador(6,  "Vitinha",            16, "MC",  2000, Calendar.FEBRUARY,   8, "Portugal"),
            criarJogador(7,  "Rúben Neves",        8,  "MC",  1997, Calendar.MARCH,     13, "Portugal"),
            criarJogador(8,  "Bruno Fernandes",    8,  "MA",  1994, Calendar.SEPTEMBER,  8, "Portugal"),
            criarJogador(9,  "Bernardo Silva",     10, "MA",  1994, Calendar.AUGUST,    10, "Portugal"),
            criarJogador(10, "Rafael Leão",        22, "EX",  2000, Calendar.JUNE,      10, "Portugal"),
            criarJogador(11, "Cristiano Ronaldo",  7,  "AV",  1985, Calendar.FEBRUARY,   5, "Portugal")
        );

        Selecao portugal = new Selecao(1, "Portugal", plantilhaPortugal, treinadorPortugal);
        treinadorPortugal.setSelecao(portugal);
        adicionarSelecao(portugal);
        adicionarUtilizador(treinadorPortugal);
        plantilhaPortugal.forEach(j -> { adicionarJogador(j); adicionarUtilizador(j); });

        // ── ESPANHA ───────────────────────────────────────────────
        Treinador treinadorEspanha = new Treinador(
                102, "Luis de la Fuente", "l.fuente@rfef.es",
                criarData(1961, Calendar.APRIL, 26), 25);

        List<Jogador> plantilhaEspanha = Arrays.asList(
            criarJogador(12, "Unai Simón",          1,  "GR", 1997, Calendar.JUNE,     11, "Espanha"),
            criarJogador(13, "Dani Carvajal",        2,  "DD", 1992, Calendar.JANUARY,  11, "Espanha"),
            criarJogador(14, "Aymeric Laporte",      14, "DC", 1994, Calendar.MAY,      27, "Espanha"),
            criarJogador(15, "Robin Le Normand",     3,  "DC", 1996, Calendar.NOVEMBER, 11, "Espanha"),
            criarJogador(16, "Marc Cucurella",       24, "DE", 1998, Calendar.JULY,     22, "Espanha"),
            criarJogador(17, "Rodri",                16, "MC", 1996, Calendar.JUNE,     22, "Espanha"),
            criarJogador(18, "Fabian Ruiz",          8,  "MC", 1996, Calendar.APRIL,    3,  "Espanha"),
            criarJogador(19, "Pedri",                26, "MA", 2002, Calendar.NOVEMBER, 25, "Espanha"),
            criarJogador(20, "Dani Olmo",            10, "MA", 1998, Calendar.MAY,      7,  "Espanha"),
            criarJogador(21, "Lamine Yamal",         19, "EX", 2007, Calendar.JULY,     13, "Espanha"),
            criarJogador(22, "Álvaro Morata",        7,  "AV", 1992, Calendar.OCTOBER,  23, "Espanha")
        );

        Selecao espanha = new Selecao(2, "Espanha", plantilhaEspanha, treinadorEspanha);
        treinadorEspanha.setSelecao(espanha);
        adicionarSelecao(espanha);
        adicionarUtilizador(treinadorEspanha);
        plantilhaEspanha.forEach(j -> { adicionarJogador(j); adicionarUtilizador(j); });

        // ── FRANÇA ────────────────────────────────────────────────
        Treinador treinadorFranca = new Treinador(
                103, "Didier Deschamps", "d.deschamps@fff.fr",
                criarData(1968, Calendar.OCTOBER, 15), 30);

        List<Jogador> plantilhaFranca = Arrays.asList(
            criarJogador(23, "Mike Maignan",         1,  "GR", 1995, Calendar.JULY,     4,  "França"),
            criarJogador(24, "Benjamin Pavard",      5,  "DD", 1996, Calendar.MARCH,    28, "França"),
            criarJogador(25, "Ibrahima Konaté",      13, "DC", 1999, Calendar.MAY,      25, "França"),
            criarJogador(26, "Dayot Upamecano",      4,  "DC", 1998, Calendar.OCTOBER,  27, "França"),
            criarJogador(27, "Theo Hernandez",       22, "DE", 1997, Calendar.OCTOBER,   6, "França"),
            criarJogador(28, "Aurélien Tchouaméni",  8,  "MC", 2000, Calendar.JANUARY,  16, "França"),
            criarJogador(29, "Adrien Rabiot",        14, "MC", 1995, Calendar.APRIL,     3, "França"),
            criarJogador(30, "Antoine Griezmann",    7,  "MA", 1991, Calendar.MARCH,    21, "França"),
            criarJogador(31, "Ousmane Dembélé",      11, "EX", 1997, Calendar.MAY,      15, "França"),
            criarJogador(32, "Marcus Thuram",        9,  "AV", 1997, Calendar.AUGUST,    6, "França"),
            criarJogador(33, "Kylian Mbappé",        10, "AV", 1998, Calendar.DECEMBER, 20, "França")
        );

        Selecao franca = new Selecao(3, "França", plantilhaFranca, treinadorFranca);
        treinadorFranca.setSelecao(franca);
        adicionarSelecao(franca);
        adicionarUtilizador(treinadorFranca);
        plantilhaFranca.forEach(j -> { adicionarJogador(j); adicionarUtilizador(j); });
    }

    // Helpers privados usados apenas por popularDados()
    private Jogador criarJogador(int id, String nome, int numCamisola, String posicao,
                                  int ano, int mes, int dia, String nacionalidade) {
        String email = nome.toLowerCase()
                           .replace(" ", ".")
                           .replace("á","a").replace("é","e").replace("í","i")
                           .replace("ó","o").replace("ú","u").replace("ã","a")
                           .replace("â","a").replace("ê","e").replace("ô","o")
                           .replace("ç","c").replace("ü","u").replace("ñ","n")
                + "@futfever.pt";
        return new Jogador(id, nome, email, numCamisola, posicao,
                           criarData(ano, mes, dia), nacionalidade, null);
    }

    private Date criarData(int ano, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(ano, mes, dia, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
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
        jogadores.clear();
    }
}
