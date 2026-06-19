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

        // ═══════════════════════════════════════════════════════════
        // 1. ESTÁDIOS
        // ═══════════════════════════════════════════════════════════
        Estadio estadioLuz       = new Estadio(1,  "Estádio da Luz",         38.7527,  -9.1847,  65000);
        Estadio estadioDragao    = new Estadio(2,  "Estádio do Dragão",      41.1620,  -8.5828,  50000);
        Estadio estadioMaracana  = new Estadio(3,  "Estádio do Maracanã",   -22.9122, -43.2302,  78838);
        Estadio estadioWembley   = new Estadio(4,  "Wembley Stadium",        51.5560,  -0.2796,  90000);
        Estadio estadioAllianz   = new Estadio(5,  "Allianz Arena",          48.2188,  11.6248,  75024);
        Estadio estadioSanSiro   = new Estadio(6,  "Estádio San Siro",       45.4782,   9.1239,  80018);
        Estadio estadioLusail    = new Estadio(7,  "Estádio de Lusail",      25.4309,  51.5053,  89012);
        Estadio estadioMetLife   = new Estadio(8,  "MetLife Stadium",        40.8135, -74.0745,  82500);

        Arrays.asList(estadioLuz, estadioDragao, estadioMaracana, estadioWembley,
                      estadioAllianz, estadioSanSiro, estadioLusail, estadioMetLife)
              .forEach(this::adicionarEstadio);

        // ═══════════════════════════════════════════════════════════
        // 2. SELEÇÕES  (20 total — 4 grupos de 5)
        //    IDs de jogadores: 1-220  (11 por equipa × 20)
        //    IDs de treinadores: 301-320
        // ═══════════════════════════════════════════════════════════

        // ── GRUPO A ──────────────────────────────────────────────
        Selecao portugal  = criarSelecao(1,  "Portugal",       301, "Roberto Martínez",  "r.martinez@fpf.pt",   1973, Calendar.JANUARY,   13, 20,
            new Object[][]{{1,"Diogo Costa",1,"GR",1999,Calendar.SEPTEMBER,19},{2,"João Cancelo",20,"DD",1994,Calendar.MAY,27},{3,"Rúben Dias",4,"DC",1997,Calendar.MAY,14},{4,"Pepe",3,"DC",1983,Calendar.FEBRUARY,26},{5,"Nuno Mendes",19,"DE",2002,Calendar.JUNE,19},{6,"Vitinha",16,"MC",2000,Calendar.FEBRUARY,8},{7,"Rúben Neves",8,"MC",1997,Calendar.MARCH,13},{8,"Bruno Fernandes",8,"MA",1994,Calendar.SEPTEMBER,8},{9,"Bernardo Silva",10,"MA",1994,Calendar.AUGUST,10},{10,"Rafael Leão",22,"EX",2000,Calendar.JUNE,10},{11,"Cristiano Ronaldo",7,"AV",1985,Calendar.FEBRUARY,5}});
        Selecao brasil    = criarSelecao(2,  "Brasil",         302, "Dorival Júnior",    "d.junior@cbf.com.br", 1962, Calendar.JUNE,       1,  18,
            new Object[][]{{12,"Alisson",1,"GR",1992,Calendar.OCTOBER,2},{13,"Danilo",2,"DD",1991,Calendar.JULY,15},{14,"Marquinhos",4,"DC",1994,Calendar.MAY,14},{15,"Gabriel Magalhães",5,"DC",2000,Calendar.DECEMBER,19},{16,"Guilherme Arana",6,"DE",1997,Calendar.JANUARY,14},{17,"Casemiro",5,"MC",1992,Calendar.FEBRUARY,23},{18,"Bruno Guimarães",8,"MC",1997,Calendar.NOVEMBER,16},{19,"Lucas Paquetá",10,"MA",1997,Calendar.AUGUST,27},{20,"Raphinha",11,"EX",1996,Calendar.DECEMBER,14},{21,"Vinícius Júnior",7,"AV",2000,Calendar.JULY,12},{22,"Rodrygo",9,"AV",2001,Calendar.JANUARY,9}});
        Selecao argentina = criarSelecao(3,  "Argentina",      303, "Lionel Scaloni",    "l.scaloni@afa.com.ar",1978, Calendar.MAY,        16, 15,
            new Object[][]{{23,"Emiliano Martínez",1,"GR",1992,Calendar.SEPTEMBER,2},{24,"Nahuel Molina",26,"DD",1998,Calendar.APRIL,6},{25,"Cristian Romero",13,"DC",1998,Calendar.APRIL,27},{26,"Nicolás Otamendi",19,"DC",1988,Calendar.FEBRUARY,12},{27,"Marcos Acuña",8,"DE",1991,Calendar.OCTOBER,28},{28,"Rodrigo De Paul",7,"MC",1994,Calendar.MAY,24},{29,"Enzo Fernández",24,"MC",2001,Calendar.JANUARY,17},{30,"Alexis Mac Allister",10,"MA",1998,Calendar.DECEMBER,24},{31,"Ángel Di María",11,"EX",1988,Calendar.FEBRUARY,14},{32,"Julián Álvarez",9,"AV",2000,Calendar.JANUARY,31},{33,"Lionel Messi",10,"AV",1987,Calendar.JUNE,24}});
        Selecao alemanha  = criarSelecao(4,  "Alemanha",       304, "Julian Nagelsmann", "j.nagelsmann@dfb.de", 1987, Calendar.JULY,       23, 8,
            new Object[][]{{34,"Manuel Neuer",1,"GR",1986,Calendar.MARCH,27},{35,"Joshua Kimmich",6,"DD",1995,Calendar.FEBRUARY,8},{36,"Antonio Rüdiger",2,"DC",1993,Calendar.MARCH,3},{37,"Jonathan Tah",4,"DC",1996,Calendar.FEBRUARY,11},{38,"David Raum",5,"DE",1998,Calendar.APRIL,22},{39,"Toni Kroos",8,"MC",1990,Calendar.JANUARY,4},{40,"Robert Andrich",23,"MC",1994,Calendar.SEPTEMBER,22},{41,"Florian Wirtz",10,"MA",2003,Calendar.MAY,3},{42,"Leroy Sané",19,"EX",1996,Calendar.JANUARY,11},{43,"Kai Havertz",29,"AV",1999,Calendar.JUNE,11},{44,"Thomas Müller",25,"AV",1989,Calendar.SEPTEMBER,13}});
        Selecao marrocos  = criarSelecao(5,  "Marrocos",       305, "Walid Regragui",    "w.regragui@frmf.ma",  1975, Calendar.AUGUST,     6,  10,
            new Object[][]{{45,"Yassine Bounou",1,"GR",1991,Calendar.APRIL,5},{46,"Achraf Hakimi",2,"DD",1998,Calendar.NOVEMBER,4},{47,"Jawad El Yamiq",3,"DC",1992,Calendar.FEBRUARY,29},{48,"Romain Saïss",5,"DC",1990,Calendar.MARCH,26},{49,"Noussair Mazraoui",12,"DE",1997,Calendar.NOVEMBER,14},{50,"Sofyan Amrabat",4,"MC",1996,Calendar.AUGUST,21},{51,"Azzedine Ounahi",8,"MC",2000,Calendar.MARCH,19},{52,"Hakim Ziyech",7,"MA",1993,Calendar.MARCH,19},{53,"Bilal El Khannouss",6,"EX",2004,Calendar.MAY,10},{54,"Youssef En-Nesyri",9,"AV",1997,Calendar.JUNE,1},{55,"Sofiane Boufal",11,"AV",1993,Calendar.SEPTEMBER,17}});

        // ── GRUPO B ──────────────────────────────────────────────
        Selecao espanha   = criarSelecao(6,  "Espanha",        306, "Luis de la Fuente", "l.fuente@rfef.es",    1961, Calendar.APRIL,      26, 25,
            new Object[][]{{56,"Unai Simón",1,"GR",1997,Calendar.JUNE,11},{57,"Dani Carvajal",2,"DD",1992,Calendar.JANUARY,11},{58,"Aymeric Laporte",14,"DC",1994,Calendar.MAY,27},{59,"Robin Le Normand",3,"DC",1996,Calendar.NOVEMBER,11},{60,"Marc Cucurella",24,"DE",1998,Calendar.JULY,22},{61,"Rodri",16,"MC",1996,Calendar.JUNE,22},{62,"Fabian Ruiz",8,"MC",1996,Calendar.APRIL,3},{63,"Pedri",26,"MA",2002,Calendar.NOVEMBER,25},{64,"Dani Olmo",10,"MA",1998,Calendar.MAY,7},{65,"Lamine Yamal",19,"EX",2007,Calendar.JULY,13},{66,"Álvaro Morata",7,"AV",1992,Calendar.OCTOBER,23}});
        Selecao inglaterra = criarSelecao(7, "Inglaterra",     307, "Gareth Southgate",  "g.southgate@thefa.com",1970,Calendar.SEPTEMBER,3, 20,
            new Object[][]{{67,"Jordan Pickford",1,"GR",1994,Calendar.MARCH,7},{68,"Kyle Walker",2,"DD",1990,Calendar.MAY,28},{69,"John Stones",5,"DC",1994,Calendar.MAY,28},{70,"Harry Maguire",6,"DC",1993,Calendar.MARCH,5},{71,"Luke Shaw",23,"DE",1995,Calendar.JULY,12},{72,"Declan Rice",4,"MC",1999,Calendar.JANUARY,14},{73,"Jude Bellingham",22,"MC",2003,Calendar.JUNE,29},{74,"Phil Foden",47,"MA",2000,Calendar.MAY,28},{75,"Marcus Rashford",10,"EX",1997,Calendar.OCTOBER,31},{76,"Bukayo Saka",7,"EX",2001,Calendar.SEPTEMBER,5},{77,"Harry Kane",9,"AV",1993,Calendar.JULY,28}});
        Selecao holanda   = criarSelecao(8,  "Holanda",        308, "Ronald Koeman",     "r.koeman@knvb.nl",    1963, Calendar.MARCH,      7,  22,
            new Object[][]{{78,"Bart Verbruggen",1,"GR",2002,Calendar.AUGUST,18},{79,"Denzel Dumfries",2,"DD",1996,Calendar.APRIL,18},{80,"Virgil van Dijk",4,"DC",1991,Calendar.JULY,8},{81,"Stefan de Vrij",6,"DC",1992,Calendar.FEBRUARY,5},{82,"Nathan Aké",5,"DE",1995,Calendar.FEBRUARY,18},{83,"Frenkie de Jong",21,"MC",1997,Calendar.MAY,12},{84,"Tijjani Reijnders",14,"MC",1998,Calendar.JULY,29},{85,"Teun Koopmeiners",8,"MA",1998,Calendar.FEBRUARY,28},{86,"Cody Gakpo",11,"EX",1999,Calendar.MAY,7},{87,"Donyell Malen",9,"AV",1999,Calendar.JANUARY,19},{88,"Memphis Depay",10,"AV",1994,Calendar.FEBRUARY,13}});
        Selecao belgica   = criarSelecao(9,  "Bélgica",        309, "Domenico Tedesco",  "d.tedesco@rbfa.be",   1985, Calendar.SEPTEMBER,12, 12,
            new Object[][]{{89,"Koen Casteels",1,"GR",1992,Calendar.JUNE,25},{90,"Timothy Castagne",2,"DD",1995,Calendar.DECEMBER,5},{91,"Wout Faes",3,"DC",1998,Calendar.APRIL,3},{92,"Jan Vertonghen",5,"DC",1987,Calendar.APRIL,24},{93,"Leandro Trossard",11,"DE",1994,Calendar.DECEMBER,4},{94,"Axel Witsel",6,"MC",1989,Calendar.JANUARY,12},{95,"Youri Tielemans",8,"MC",1997,Calendar.MAY,7},{96,"Kevin De Bruyne",7,"MA",1991,Calendar.JUNE,28},{97,"Dodi Lukébakio",9,"EX",1997,Calendar.SEPTEMBER,24},{98,"Romelu Lukaku",9,"AV",1993,Calendar.MAY,13},{99,"Lois Openda",18,"AV",2000,Calendar.FEBRUARY,16}});
        Selecao croacia   = criarSelecao(10, "Croácia",        310, "Zlatko Dalic",      "z.dalic@hns.hr",      1966, Calendar.OCTOBER,    26, 22,
            new Object[][]{{100,"Dominik Livaković",1,"GR",1995,Calendar.JANUARY,9},{101,"Josip Juranović",2,"DD",1995,Calendar.AUGUST,16},{102,"Joško Gvardiol",24,"DC",2002,Calendar.JANUARY,23},{103,"Dario Špikić",4,"DC",1996,Calendar.MARCH,14},{104,"Borna Sosa",5,"DE",1998,Calendar.JANUARY,21},{105,"Marcelo Brozović",11,"MC",1992,Calendar.NOVEMBER,16},{106,"Luka Modrić",10,"MC",1985,Calendar.SEPTEMBER,9},{107,"Mateo Kovačić",8,"MA",1994,Calendar.MAY,6},{108,"Ante Rebić",18,"EX",1993,Calendar.SEPTEMBER,21},{109,"Ivan Perišić",4,"EX",1989,Calendar.FEBRUARY,2},{110,"Andrej Kramarić",9,"AV",1991,Calendar.JUNE,19}});

        // ── GRUPO C ──────────────────────────────────────────────
        Selecao franca    = criarSelecao(11, "França",         311, "Didier Deschamps",  "d.deschamps@fff.fr",  1968, Calendar.OCTOBER,    15, 30,
            new Object[][]{{111,"Mike Maignan",1,"GR",1995,Calendar.JULY,4},{112,"Benjamin Pavard",5,"DD",1996,Calendar.MARCH,28},{113,"Ibrahima Konaté",13,"DC",1999,Calendar.MAY,25},{114,"Dayot Upamecano",4,"DC",1998,Calendar.OCTOBER,27},{115,"Theo Hernandez",22,"DE",1997,Calendar.OCTOBER,6},{116,"Aurélien Tchouaméni",8,"MC",2000,Calendar.JANUARY,16},{117,"Adrien Rabiot",14,"MC",1995,Calendar.APRIL,3},{118,"Antoine Griezmann",7,"MA",1991,Calendar.MARCH,21},{119,"Ousmane Dembélé",11,"EX",1997,Calendar.MAY,15},{120,"Marcus Thuram",9,"AV",1997,Calendar.AUGUST,6},{121,"Kylian Mbappé",10,"AV",1998,Calendar.DECEMBER,20}});
        Selecao italia    = criarSelecao(12, "Itália",         312, "Luciano Spalletti", "l.spalletti@figc.it", 1959, Calendar.MARCH,      7,  28,
            new Object[][]{{122,"Gianluigi Donnarumma",1,"GR",1999,Calendar.FEBRUARY,25},{123,"Giovanni Di Lorenzo",2,"DD",1993,Calendar.AUGUST,4},{124,"Alessandro Bastoni",23,"DC",1999,Calendar.APRIL,8},{125,"Riccardo Calafiori",3,"DC",2002,Calendar.MAY,19},{126,"Federico Dimarco",32,"DE",1997,Calendar.NOVEMBER,10},{127,"Nicolò Barella",18,"MC",1997,Calendar.FEBRUARY,7},{128,"Jorginho",8,"MC",1991,Calendar.DECEMBER,20},{129,"Sandro Tonali",4,"MA",2000,Calendar.MAY,8},{130,"Federico Chiesa",14,"EX",1997,Calendar.OCTOBER,25},{131,"Giacomo Raspadori",10,"AV",2000,Calendar.DECEMBER,18},{132,"Gianluca Scamacca",9,"AV",1999,Calendar.JANUARY,1}});
        Selecao uruguay   = criarSelecao(13, "Uruguai",        313, "Marcelo Bielsa",    "m.bielsa@auf.org.uy", 1955, Calendar.JULY,       21, 30,
            new Object[][]{{133,"Sergio Rochet",1,"GR",1993,Calendar.MARCH,23},{134,"Nahitan Nández",15,"DD",1995,Calendar.DECEMBER,28},{135,"José María Giménez",2,"DC",1995,Calendar.JANUARY,20},{136,"Ronald Araújo",4,"DC",1999,Calendar.MARCH,7},{137,"Mathías Olivera",22,"DE",1997,Calendar.OCTOBER,31},{138,"Lucas Torreira",11,"MC",1996,Calendar.FEBRUARY,11},{139,"Rodrigo Bentancur",8,"MC",1997,Calendar.JUNE,25},{140,"Federico Valverde",14,"MA",1998,Calendar.JULY,22},{141,"Giorgian De Arrascaeta",10,"EX",1994,Calendar.JUNE,1},{142,"Darwin Núñez",11,"AV",2000,Calendar.JUNE,24},{143,"Luis Suárez",9,"AV",1987,Calendar.JANUARY,24}});
        Selecao japon     = criarSelecao(14, "Japão",          314, "Hajime Moriyasu",   "h.moriyasu@jfa.jp",   1968, Calendar.AUGUST,     23, 15,
            new Object[][]{{144,"Shuichi Gonda",1,"GR",1989,Calendar.MARCH,3},{145,"Hiroki Sakai",5,"DD",1990,Calendar.JUNE,12},{146,"Takehiro Tomiyasu",16,"DC",1998,Calendar.NOVEMBER,5},{147,"Ko Itakura",3,"DC",1997,Calendar.JANUARY,27},{148,"Yuto Nagatomo",5,"DE",1986,Calendar.SEPTEMBER,12},{149,"Wataru Endo",6,"MC",1993,Calendar.FEBRUARY,9},{150,"Hidemasa Morita",10,"MC",1994,Calendar.JULY,16},{151,"Daichi Kamada",14,"MA",1996,Calendar.AUGUST,5},{152,"Junya Ito",17,"EX",1993,Calendar.MARCH,9},{153,"Kaoru Mitoma",10,"EX",1997,Calendar.MAY,20},{154,"Ayase Ueda",18,"AV",1998,Calendar.SEPTEMBER,28}});
        Selecao senegal   = criarSelecao(15, "Senegal",        315, "Aliou Cissé",       "a.cisse@fsf.sn",      1975, Calendar.MARCH,      24, 20,
            new Object[][]{{155,"Édouard Mendy",1,"GR",1992,Calendar.MARCH,1},{156,"Formose Mendy",2,"DD",1998,Calendar.DECEMBER,26},{157,"Abdou Diallo",5,"DC",1996,Calendar.MAY,4},{158,"Kalidou Koulibaly",3,"DC",1991,Calendar.JUNE,20},{159,"Saliou Ciss",23,"DE",1989,Calendar.OCTOBER,15},{160,"Nampalys Mendy",12,"MC",1992,Calendar.JUNE,23},{161,"Idrissa Gueye",4,"MC",1989,Calendar.SEPTEMBER,26},{162,"Sadio Mané",10,"EX",1992,Calendar.APRIL,10},{163,"Ismaila Sarr",19,"EX",1998,Calendar.FEBRUARY,25},{164,"Habib Diallo",11,"AV",1994,Calendar.SEPTEMBER,18},{165,"Nicolas Jackson",9,"AV",2001,Calendar.JUNE,20}});

        // ── GRUPO D ──────────────────────────────────────────────
        Selecao eua       = criarSelecao(16, "EUA",            316, "Gregg Berhalter",   "g.berhalter@ussoccer.org",1963,Calendar.AUGUST,  1,  12,
            new Object[][]{{166,"Matt Turner",1,"GR",1994,Calendar.JUNE,24},{167,"Sergiño Dest",2,"DD",2000,Calendar.NOVEMBER,3},{168,"Chris Richards",5,"DC",2000,Calendar.MARCH,28},{169,"Walker Zimmermann",6,"DC",1993,Calendar.MAY,19},{170,"Antonee Robinson",91,"DE",1997,Calendar.AUGUST,8},{171,"Tyler Adams",4,"MC",1999,Calendar.FEBRUARY,14},{172,"Weston McKennie",8,"MC",1998,Calendar.AUGUST,28},{173,"Christian Pulisic",10,"MA",1998,Calendar.SEPTEMBER,18},{174,"Giovanni Reyna",7,"EX",2002,Calendar.NOVEMBER,13},{175,"Josh Sargent",9,"AV",1999,Calendar.FEBRUARY,20},{176,"Ricardo Pepi",11,"AV",2003,Calendar.JANUARY,9}});
        Selecao mexico    = criarSelecao(17, "México",         317, "Javier Aguirre",    "j.aguirre@femexfut.org",1958,Calendar.DECEMBER, 1,  20,
            new Object[][]{{177,"Guillermo Ochoa",1,"GR",1985,Calendar.JULY,13},{178,"Jorge Sánchez",2,"DD",1997,Calendar.SEPTEMBER,4},{179,"Nestor Araujo",3,"DC",1991,Calendar.AUGUST,7},{180,"César Montes",6,"DC",1997,Calendar.MAY,24},{181,"Jesús Gallardo",23,"DE",1994,Calendar.AUGUST,15},{182,"Edson Álvarez",4,"MC",1997,Calendar.OCTOBER,24},{183,"Hector Herrera",16,"MC",1990,Calendar.APRIL,19},{184,"Hirving Lozano",22,"EX",1995,Calendar.JULY,30},{185,"Alexis Vega",11,"EX",1997,Calendar.NOVEMBER,17},{186,"Raúl Jiménez",9,"AV",1991,Calendar.MAY,27},{187,"Henry Martín",14,"AV",1992,Calendar.NOVEMBER,19}});
        Selecao colombia  = criarSelecao(18, "Colômbia",       318, "Néstor Lorenzo",    "n.lorenzo@fcf.com.co", 1966, Calendar.NOVEMBER,  22, 18,
            new Object[][]{{188,"Camilo Vargas",1,"GR",1992,Calendar.DECEMBER,12},{189,"Daniel Muñoz",2,"DD",1996,Calendar.MAY,5},{190,"Dávinson Sánchez",3,"DC",1996,Calendar.JUNE,12},{191,"Jhon Lucumí",6,"DC",1998,Calendar.JUNE,26},{192,"Johan Mojica",7,"DE",1992,Calendar.AUGUST,21},{193,"Wilmar Barrios",5,"MC",1993,Calendar.OCTOBER,16},{194,"Lerma Juan",8,"MC",1994,Calendar.NOVEMBER,16},{195,"James Rodríguez",10,"MA",1991,Calendar.JULY,12},{196,"Luis Díaz",7,"EX",1997,Calendar.JANUARY,13},{197,"Rafael Santos Borré",19,"AV",1995,Calendar.SEPTEMBER,15},{198,"Falcao García",9,"AV",1986,Calendar.FEBRUARY,10}});
        Selecao coreia    = criarSelecao(19, "Coreia do Sul",  319, "Jurgen Klinsmann",  "j.klinsmann@kfa.or.kr",1964, Calendar.JULY,      30, 22,
            new Object[][]{{199,"Kim Seung-Gyu",1,"GR",1990,Calendar.SEPTEMBER,30},{200,"Kim Moon-Hwan",2,"DD",1995,Calendar.AUGUST,1},{201,"Kim Min-Jae",3,"DC",1996,Calendar.MAY,15},{202,"Jung Seung-Hyun",6,"DC",1994,Calendar.APRIL,3},{203,"Kim Jin-Su",12,"DE",1992,Calendar.JUNE,13},{204,"Jung Woo-Young",16,"MC",1989,Calendar.SEPTEMBER,23},{205,"Lee Jae-Sung",8,"MC",1992,Calendar.AUGUST,10},{206,"Son Heung-Min",7,"MA",1992,Calendar.JULY,8},{207,"Hwang Hee-Chan",11,"EX",1996,Calendar.JANUARY,26},{208,"Cho Gue-Sung",9,"AV",1998,Calendar.JANUARY,25},{209,"Hwang Ui-Jo",10,"AV",1992,Calendar.SEPTEMBER,28}});
        Selecao australia = criarSelecao(20, "Austrália",      320, "Tony Popovic",      "t.popovic@footballaustralia.com.au",1973,Calendar.JULY,4, 14,
            new Object[][]{{210,"Mat Ryan",1,"GR",1992,Calendar.APRIL,8},{211,"Nathaniel Atkinson",2,"DD",2000,Calendar.JUNE,13},{212,"Harry Souttar",3,"DC",1998,Calendar.OCTOBER,22},{213,"Kye Rowles",6,"DC",1998,Calendar.JUNE,24},{214,"Aziz Behich",5,"DE",1990,Calendar.DECEMBER,16},{215,"Aaron Mooy",13,"MC",1990,Calendar.SEPTEMBER,15},{216,"Riley McGree",8,"MC",1998,Calendar.NOVEMBER,2},{217,"Ajdin Hrustic",10,"MA",1996,Calendar.JULY,5},{218,"Mathew Leckie",7,"EX",1991,Calendar.FEBRUARY,4},{219,"Mitchell Duke",19,"AV",1990,Calendar.JANUARY,18},{220,"Jamie Maclaren",9,"AV",1993,Calendar.MARCH,6}});

        // ── Registar todas as seleções ─────────────────────────────
        List<Selecao> todasSelecoes = Arrays.asList(
            portugal, brasil, argentina, alemanha, marrocos,
            espanha, inglaterra, holanda, belgica, croacia,
            franca, italia, uruguay, japon, senegal,
            eua, mexico, colombia, coreia, australia
        );
        todasSelecoes.forEach(s -> {
            adicionarSelecao(s);
            adicionarUtilizador(s.getTreinador());
            s.getJogadores().forEach(j -> { adicionarJogador(j); adicionarUtilizador(j); });
        });

        // ═══════════════════════════════════════════════════════════
        // 3. GRUPOS
        // ═══════════════════════════════════════════════════════════
        Grupo grupoA = new Grupo(1, "Grupo A");
        grupoA.adicionarSelecao(portugal); grupoA.adicionarSelecao(brasil);
        grupoA.adicionarSelecao(argentina); grupoA.adicionarSelecao(alemanha);
        grupoA.adicionarSelecao(marrocos);

        Grupo grupoB = new Grupo(2, "Grupo B");
        grupoB.adicionarSelecao(espanha); grupoB.adicionarSelecao(inglaterra);
        grupoB.adicionarSelecao(holanda); grupoB.adicionarSelecao(belgica);
        grupoB.adicionarSelecao(croacia);

        Grupo grupoC = new Grupo(3, "Grupo C");
        grupoC.adicionarSelecao(franca); grupoC.adicionarSelecao(italia);
        grupoC.adicionarSelecao(uruguay); grupoC.adicionarSelecao(japon);
        grupoC.adicionarSelecao(senegal);

        Grupo grupoD = new Grupo(4, "Grupo D");
        grupoD.adicionarSelecao(eua); grupoD.adicionarSelecao(mexico);
        grupoD.adicionarSelecao(colombia); grupoD.adicionarSelecao(coreia);
        grupoD.adicionarSelecao(australia);

        Arrays.asList(grupoA, grupoB, grupoC, grupoD).forEach(this::adicionarGrupo);

        // ═══════════════════════════════════════════════════════════
        // 4. CENTROS DE ESTÁGIO  (um por seleção)
        // ═══════════════════════════════════════════════════════════
        Object[][] centros = {
            {1,"Cidade do Futebol","Oeiras",portugal},
            {2,"CT Granja Comary","Teresópolis",brasil},
            {3,"Predio AFA","Ezeiza",argentina},
            {4,"DFB Campus","Frankfurt",alemanha},
            {5,"Centre Mohammed VI","Salé",marrocos},
            {6,"Ciudad del Fútbol","Las Rozas",espanha},
            {7,"St. George's Park","Burton upon Trent",inglaterra},
            {8,"KNVB Campus","Zeist",holanda},
            {9,"Tubize Football Center","Tubize",belgica},
            {10,"Šalata Football Center","Zagreb",croacia},
            {11,"Clairefontaine","Clairefontaine-en-Yvelines",franca},
            {12,"Centro Tecnico Coverciano","Florença",italia},
            {13,"Complejo Celeste","Montevideo",uruguay},
            {14,"JFA YUME Field","Chiba",japon},
            {15,"Slsc Dakar","Dakar",senegal},
            {16,"IMG Academy","Bradenton",eua},
            {17,"Centro de Alto Rendimiento","Guadalajara",mexico},
            {18,"CT Compensar","Bogotá",colombia},
            {19,"NFC Seoul","Seoul",coreia},
            {20,"AIS Campus","Canberra",australia}
        };
        for (Object[] c : centros) {
            CentroDeEstagio centro = new CentroDeEstagio((int)c[0], (String)c[1], (String)c[2]);
            centro.associarSelecao((Selecao)c[3]);
            adicionarCentroDeEstagio(centro);
        }

        // ═══════════════════════════════════════════════════════════
        // 5. EQUIPAS DE ARBITRAGEM  (10, de países neutros)
        // ═══════════════════════════════════════════════════════════
        Object[][] arbitros = {
            {1, "Equipa Suíça A",       "Suíça"},
            {2, "Equipa Suíça B",       "Suíça"},
            {3, "Equipa Polaca",        "Polónia"},
            {4, "Equipa Turca",         "Turquia"},
            {5, "Equipa Grega",         "Grécia"},
            {6, "Equipa Checa",         "República Checa"},
            {7, "Equipa Eslovena",      "Eslovénia"},
            {8, "Equipa Húngara",       "Hungria"},
            {9, "Equipa Romena",        "Roménia"},
            {10,"Equipa Escocesa",      "Escócia"}
        };
        List<EquipaArbitragem> equipasArb = new ArrayList<>();
        for (Object[] a : arbitros) {
            EquipaArbitragem ea = new EquipaArbitragem((int)a[0], (String)a[1], (String)a[2]);
            adicionarEquipaArbitragem(ea);
            equipasArb.add(ea);
        }

        // ═══════════════════════════════════════════════════════════
        // 6. JOGOS  (20 jogos — 5 jornadas × 4 jogos por jornada)
        //    Cada árbitro usado em 2 jogos, sem conflito de nac.
        // ═══════════════════════════════════════════════════════════
        // helper para criar data com hora
        // criarDataHora(ano, mes, dia, hora)
        int jogoId = 1;

        // Jornada 1
        Jogo j1  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,20,17), portugal,   brasil,     estadioLuz,      equipasArb.get(0)); // EA Suíça A — ok
        Jogo j2  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,20,20), argentina,  alemanha,   estadioMaracana, equipasArb.get(2)); // EA Polaca — ok
        Jogo j3  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,21,17), espanha,    inglaterra, estadioWembley,  equipasArb.get(1)); // EA Suíça B — ok
        Jogo j4  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,21,20), holanda,    belgica,    estadioAllianz,  equipasArb.get(3)); // EA Turca — ok
        // Jornada 2
        Jogo j5  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,24,17), franca,     italia,     estadioSanSiro,  equipasArb.get(4)); // EA Grega — ok
        Jogo j6  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,24,20), uruguay,    japon,      estadioLusail,   equipasArb.get(5)); // EA Checa — ok
        Jogo j7  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,25,17), eua,        mexico,     estadioMetLife,  equipasArb.get(6)); // EA Eslovena — ok
        Jogo j8  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,25,20), colombia,   coreia,     estadioDragao,   equipasArb.get(7)); // EA Húngara — ok
        // Jornada 3
        Jogo j9  = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,28,17), portugal,   marrocos,   estadioLusail,   equipasArb.get(8)); // EA Romena — ok
        Jogo j10 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,28,20), brasil,     argentina,  estadioMaracana, equipasArb.get(9)); // EA Escocesa — ok
        Jogo j11 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,29,17), espanha,    croacia,    estadioAllianz,  equipasArb.get(0)); // EA Suíça A — ok
        Jogo j12 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JUNE,29,20), inglaterra, holanda,    estadioWembley,  equipasArb.get(2)); // EA Polaca — ok
        // Jornada 4
        Jogo j13 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 2, 17), franca,   senegal,    estadioSanSiro,  equipasArb.get(3)); // EA Turca — ok
        Jogo j14 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 2, 20), italia,   uruguay,    estadioLuz,      equipasArb.get(1)); // EA Suíça B — ok
        Jogo j15 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 3, 17), eua,      australia,  estadioMetLife,  equipasArb.get(4)); // EA Grega — ok
        Jogo j16 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 3, 20), mexico,   colombia,   estadioDragao,   equipasArb.get(5)); // EA Checa — ok
        // Jornada 5
        Jogo j17 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 7, 17), alemanha,  marrocos,   estadioAllianz,  equipasArb.get(6)); // EA Eslovena — ok
        Jogo j18 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 7, 20), belgica,   croacia,    estadioLusail,   equipasArb.get(7)); // EA Húngara — ok
        Jogo j19 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 8, 17), japon,     senegal,    estadioMaracana, equipasArb.get(8)); // EA Romena — ok
        Jogo j20 = criarJogo(jogoId++, criarDataHora(2026,Calendar.JULY, 8, 20), coreia,    australia,  estadioMetLife,  equipasArb.get(9)); // EA Escocesa — ok

        Arrays.asList(j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,j16,j17,j18,j19,j20)
              .forEach(j -> {
                  adicionarJogo(j);
                  j.getEstadio().adicionarJogo(j);
                  j.abrirVenda();
                  j.getSelecaoCasa().adicionarJogo(j);
                  j.getSelecaoFora().adicionarJogo(j);
              });

        // ═══════════════════════════════════════════════════════════
        // 7. DESLOCAÇÕES  (seleção visitante de cada jogo)
        // ═══════════════════════════════════════════════════════════
        int deslId = 1;
        criarERegistarDeslocacao(deslId++, "Rio de Janeiro", "Lisboa",               j1.getDataHora(),  brasil,     j1);
        criarERegistarDeslocacao(deslId++, "Ezeiza",         "Rio de Janeiro",       j2.getDataHora(),  alemanha,   j2);
        criarERegistarDeslocacao(deslId++, "Las Rozas",      "Londres",              j3.getDataHora(),  inglaterra, j3);
        criarERegistarDeslocacao(deslId++, "Zeist",          "Munique",              j4.getDataHora(),  belgica,    j4);
        criarERegistarDeslocacao(deslId++, "Coverciano",     "Milão",                j5.getDataHora(),  italia,     j5);
        criarERegistarDeslocacao(deslId++, "Montevideo",     "Doha",                 j6.getDataHora(),  japon,      j6);
        criarERegistarDeslocacao(deslId++, "Guadalajara",    "Nova Iorque",          j7.getDataHora(),  mexico,     j7);
        criarERegistarDeslocacao(deslId++, "Bogotá",         "Porto",                j8.getDataHora(),  coreia,     j8);
        criarERegistarDeslocacao(deslId++, "Salé",           "Doha",                 j9.getDataHora(),  marrocos,   j9);
        criarERegistarDeslocacao(deslId++, "Teresópolis",    "Rio de Janeiro",       j10.getDataHora(), argentina,  j10);
        criarERegistarDeslocacao(deslId++, "Zagreb",         "Munique",              j11.getDataHora(), croacia,    j11);
        criarERegistarDeslocacao(deslId++, "Zeist",          "Londres",              j12.getDataHora(), holanda,    j12);
        criarERegistarDeslocacao(deslId++, "Dakar",          "Milão",                j13.getDataHora(), senegal,    j13);
        criarERegistarDeslocacao(deslId++, "Montevideo",     "Lisboa",               j14.getDataHora(), uruguay,    j14);
        criarERegistarDeslocacao(deslId++, "Canberra",       "Nova Iorque",          j15.getDataHora(), australia,  j15);
        criarERegistarDeslocacao(deslId++, "Bogotá",         "Porto",                j16.getDataHora(), colombia,   j16);
        criarERegistarDeslocacao(deslId++, "Salé",           "Munique",              j17.getDataHora(), marrocos,   j17);
        criarERegistarDeslocacao(deslId++, "Zagreb",         "Doha",                 j18.getDataHora(), croacia,    j18);
        criarERegistarDeslocacao(deslId++, "Dakar",          "Rio de Janeiro",       j19.getDataHora(), senegal,    j19);
        criarERegistarDeslocacao(deslId++, "Canberra",       "Nova Iorque",          j20.getDataHora(), australia,  j20);

        // ═══════════════════════════════════════════════════════════
        // 8. GESTORES  (3)
        // ═══════════════════════════════════════════════════════════
        Gestor g1 = new Gestor(401, "Ana Silva",    "ana.silva@futfever.pt",    "admin",    "1234");
        Gestor g2 = new Gestor(402, "Carlos Matos", "c.matos@futfever.pt",      "carlos",   "abcd");
        Gestor g3 = new Gestor(403, "Rita Costa",   "r.costa@futfever.pt",      "rita",     "xpto");
        Arrays.asList(g1, g2, g3).forEach(this::adicionarUtilizador);

        // ═══════════════════════════════════════════════════════════
        // 9. UTILIZADORES PÚBLICOS  (8)
        // ═══════════════════════════════════════════════════════════
        UtilizadorPublico u1 = new UtilizadorPublico(501, "João Silva",       "joao.silva@email.pt",    "12345678",  "910000001");
        UtilizadorPublico u2 = new UtilizadorPublico(502, "Maria Santos",     "maria.santos@email.pt",  "23456789",  "910000002");
        UtilizadorPublico u3 = new UtilizadorPublico(503, "Pedro Ferreira",   "pedro.f@email.pt",       "34567890",  "910000003");
        UtilizadorPublico u4 = new UtilizadorPublico(504, "Ana Rodrigues",    "ana.r@email.pt",         "45678901",  "910000004");
        UtilizadorPublico u5 = new UtilizadorPublico(505, "Tiago Oliveira",   "tiago.o@email.pt",       "56789012",  "910000005");
        UtilizadorPublico u6 = new UtilizadorPublico(506, "Sofia Pereira",    "sofia.p@email.pt",       "67890123",  "910000006");
        UtilizadorPublico u7 = new UtilizadorPublico(507, "Miguel Costa",     "miguel.c@email.pt",      "78901234",  "910000007");
        UtilizadorPublico u8 = new UtilizadorPublico(508, "Inês Carvalho",    "ines.c@email.pt",        "89012345",  "910000008");
        Arrays.asList(u1,u2,u3,u4,u5,u6,u7,u8).forEach(this::adicionarUtilizador);

        // Compras de bilhetes de exemplo — precisam que a venda esteja aberta (já foi chamado abrirVenda())
        u1.comprarBilhete(j1,  2, u1.getCartaoCidadao(), u1.getTelefone()); // Portugal vs Brasil
        u2.comprarBilhete(j3,  4, u2.getCartaoCidadao(), u2.getTelefone()); // Espanha vs Inglaterra
        u3.comprarBilhete(j5,  1, u3.getCartaoCidadao(), u3.getTelefone()); // França vs Itália
        u4.comprarBilhete(j10, 3, u4.getCartaoCidadao(), u4.getTelefone()); // Brasil vs Argentina
        u5.comprarBilhete(j1,  2, u5.getCartaoCidadao(), u5.getTelefone()); // Portugal vs Brasil (outro comprador)
        u6.comprarBilhete(j7,  5, u6.getCartaoCidadao(), u6.getTelefone()); // EUA vs México
        u7.comprarBilhete(j9,  2, u7.getCartaoCidadao(), u7.getTelefone()); // Portugal vs Marrocos
        u8.comprarBilhete(j14, 1, u8.getCartaoCidadao(), u8.getTelefone()); // Itália vs Uruguai
    }

    // Helpers privados usados apenas por popularDados()

    /**
     * Cria uma Seleção completa: treinador + plantel, a partir de uma matriz de dados de jogadores.
     * Cada linha de jogadoresData: {id, nome, numCamisola, posicao, anoNasc, mesNasc, diaNasc}
     */
    private Selecao criarSelecao(int id, String nome,
                                  int treinadorId, String treinadorNome, String treinadorEmail,
                                  int treinadorAno, int treinadorMes, int treinadorDia, int anosExperiencia,
                                  Object[][] jogadoresData) {
        Treinador treinador = new Treinador(treinadorId, treinadorNome, treinadorEmail,
                criarData(treinadorAno, treinadorMes, treinadorDia), anosExperiencia);

        List<Jogador> plantel = new ArrayList<>();
        for (Object[] j : jogadoresData) {
            int jId          = (int) j[0];
            String jNome     = (String) j[1];
            int numCamisola  = (int) j[2];
            String posicao   = (String) j[3];
            int ano          = (int) j[4];
            int mes          = (int) j[5];
            int dia          = (int) j[6];
            plantel.add(criarJogador(jId, jNome, numCamisola, posicao, ano, mes, dia, nome));
        }

        Selecao selecao = new Selecao(id, nome, plantel, treinador);
        treinador.setSelecao(selecao);
        return selecao;
    }

    /** Cria uma data com hora específica (ex.: para o início de um jogo). */
    private Date criarDataHora(int ano, int mes, int dia, int hora) {
        Calendar c = Calendar.getInstance();
        c.set(ano, mes, dia, hora, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /** Cria um Jogo já associado ao estádio e à equipa de arbitragem indicados. */
    private Jogo criarJogo(int id, Date dataHora, Selecao casa, Selecao fora,
                            Estadio estadio, EquipaArbitragem equipaArbitragem) {
        Jogo jogo = new Jogo(id, dataHora, casa, fora, estadio);
        jogo.setEquipaArbitragem(equipaArbitragem);
        equipaArbitragem.adicionarJogo(jogo);
        return jogo;
    }

    /** Cria uma Deslocação, associa-a ao jogo correspondente e regista-a na seleção e no repositório. */
    private Deslocacao criarERegistarDeslocacao(int id, String origem, String destino,
                                                  Date dataHora, Selecao selecao, Jogo jogo) {
        Deslocacao deslocacao = new Deslocacao(id, origem, destino, dataHora, selecao);
        deslocacao.setEventoAssociado(jogo);
        selecao.adicionarDeslocacao(deslocacao);
        adicionarDeslocacao(deslocacao);
        return deslocacao;
    }

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
