import pt.futfever.model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class FutFeverApp extends JFrame {
    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);

    // Modelo simples para representar jogos
    private static class Game {
        String home, away, date, time, stadium;

        Game(String home, String away, String date, String time, String stadium) {
            this.home = home;
            this.away = away;
            this.date = date;
            this.time = time;
            this.stadium = stadium;
        }
    }

    private final java.util.List<Game> GAMES = new ArrayList<>();

    // Informação básica das equipas
    private static class TeamInfo {
        String coach;
        java.util.List<String> players;

        TeamInfo(String coach, java.util.List<String> players) {
            this.coach = coach;
            this.players = players;
        }
    }

    private final java.util.Map<String, TeamInfo> TEAM_INFO = new java.util.HashMap<>();
    private final Color GREEN = new Color(0, 110, 79);
    private final Color DARK = new Color(0, 47, 37);
    private final Color LIGHT = new Color(245, 247, 246);
    private final Color LINE = new Color(232, 236, 234);
    private final Color MUTED = new Color(107, 114, 128);
    private final ImageIcon LOGO;

    public FutFeverApp() {
        LOGO = loadLogo();
        // Povoar o singleton antes de construir os painéis
        GestorDados.getInstance().popularDados();
        initGames();
        initTeams();
        setTitle("FutFever - Java");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1180, 760);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(960, 650));
        root.add(indexPanel(), "index");
        root.add(userShell(), "user");
        root.add(adminShell(), "admin");
        setContentPane(root);
        // Ao iniciar a aplicação, mostrar directamente a área de Utilizador
        cards.show(root, "user");
    }

    private void initTeams() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Selecao s : GestorDados.getInstance().getSelecoes()) {
            String coach = s.getTreinador() != null ? s.getTreinador().getName() : "—";
            java.util.List<String> players = new ArrayList<>();
            for (Jogador j : s.getJogadores()) {
                players.add(j.getName() + " · " + j.getPosicao() + " · #" + j.getNumCamisola());
            }
            TEAM_INFO.put(s.getNome(), new TeamInfo(coach, players));
        }
    }

    private void initGames() {
        SimpleDateFormat dateFmt = new SimpleDateFormat("dd MMM", Locale.of("pt", "PT"));
        SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm");
        for (Jogo j : GestorDados.getInstance().getJogos()) {
            String home = j.getSelecaoCasa() != null ? j.getSelecaoCasa().getNome() : "—";
            String away = j.getSelecaoFora() != null ? j.getSelecaoFora().getNome() : "—";
            String date = j.getDataHora() != null ? dateFmt.format(j.getDataHora()) : "—";
            String time = j.getDataHora() != null ? timeFmt.format(j.getDataHora()) : "—";
            String stadium = j.getEstadio() != null ? j.getEstadio().getNome() : "—";
            GAMES.add(new Game(home, away, date, time, stadium));
        }
    }

    private ImageIcon loadLogo() {
        try {
            File f = new File("assets/logo_raw.png");
            if (!f.exists()) return null;
            ImageIcon ic = new ImageIcon(f.getAbsolutePath());
            Image img = ic.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new FutFeverApp().setVisible(true);
        });
    }

    private JPanel indexPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(LIGHT);
        JPanel box = new JPanel();
        box.setOpaque(false);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("FUTFEVER", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 46));
        title.setForeground(GREEN);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub = new JLabel("Escolha a área da aplicação", SwingConstants.CENTER);
        sub.setForeground(MUTED);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel grid = new JPanel(new GridLayout(1, 2, 22, 22));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(35, 0, 0, 0));
        grid.add(choiceCard("👤", "Área Utilizador", "Consultar jogos, equipas, bilhetes e perfil", () -> cards.show(root, "user")));
        grid.add(choiceCard("🛡️", "Backoffice Admin", "Gestão completa da competição", () -> cards.show(root, "admin")));
        box.add(title);
        box.add(sub);
        box.add(grid);
        p.add(box);
        return p;
    }

    private JPanel choiceCard(String icon, String title, String text, Runnable action) {
        JPanel c = card();
        c.setPreferredSize(new Dimension(360, 230));
        c.setLayout(new BorderLayout(10, 10));
        JLabel i = new JLabel(icon);
        i.setFont(new Font("SansSerif", Font.PLAIN, 52));
        JLabel t = label(title, 25, Font.BOLD, GREEN);
        JTextArea desc = text(text, 15);
        desc.setForeground(MUTED);
        JButton b = button("Entrar");
        b.addActionListener(e -> action.run());
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(i);
        top.add(t);
        top.add(desc);
        c.add(top, BorderLayout.CENTER);
        c.add(b, BorderLayout.SOUTH);
        return c;
    }

    private JPanel userShell() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(new Color(230, 235, 233));
        JPanel phone = new JPanel(new BorderLayout());
        phone.setBackground(Color.WHITE);
        phone.setPreferredSize(new Dimension(430, 700));
        phone.setBorder(new LineBorder(LINE));
        CardLayout userCards = new CardLayout();
        JPanel content = new JPanel(userCards);
        content.setBackground(Color.WHITE);
        content.add(userHome(userCards, content), "home");
        content.add(gamesScreen(userCards, content), "jogos");
        content.add(teamsScreen(userCards, content), "equipas");
        content.add(bilhetesScreen(), "bilhetes");
        content.add(profileScreen(), "perfil");
        // Lista de estádios que têm jogos — mostra quando o utilizador clica em "Estádios"
        content.add(stadiumsScreen(userCards, content), "stadios");
        phone.add(content, BorderLayout.CENTER);
        phone.add(bottomNav(userCards, content), BorderLayout.SOUTH);
        outer.add(phone);
        return outer;
    }

    private JPanel userHome(CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(22, 22, 10, 22));
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JPanel htext = new JPanel();
        htext.setOpaque(false);
        htext.setLayout(new BoxLayout(htext, BoxLayout.Y_AXIS));
        htext.add(label("Olá, João!", 20, Font.BOLD, Color.BLACK));
        htext.add(label("Bem-vindo ao FutFever", 13, Font.PLAIN, MUTED));
        JButton admin = smallButton("🛡️ ADMIN");
        admin.addActionListener(e -> cards.show(root, "admin"));
        header.add(htext, BorderLayout.WEST);
        header.add(admin, BorderLayout.EAST);
        JPanel hero = gradientPanel();
        hero.setLayout(new BorderLayout());
        hero.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel heroText = new JPanel();
        heroText.setOpaque(false);
        heroText.setLayout(new BoxLayout(heroText, BoxLayout.Y_AXIS));
        heroText.add(label("VIVE A EMOÇÃO", 20, Font.BOLD, Color.WHITE));
        heroText.add(label("DO FUTEBOL", 20, Font.BOLD, Color.WHITE));
        JLabel hp = label("Consulta jogos, equipas e bilhetes.", 13, Font.PLAIN, Color.WHITE);
        heroText.add(hp);
        JButton verJogos = whiteButton("Ver Jogos");
        verJogos.addActionListener(e -> userCards.show(content, "jogos"));
        heroText.add(Box.createVerticalStrut(10));
        heroText.add(verJogos);
        JLabel ball = new JLabel("⚽");
        ball.setFont(new Font("SansSerif", Font.PLAIN, 70));
        hero.add(heroText, BorderLayout.WEST);
        hero.add(ball, BorderLayout.EAST);
        JPanel quick = new JPanel(new GridLayout(2, 3, 12, 12));
        quick.setOpaque(false);
        quick.setBorder(new EmptyBorder(18, 0, 18, 0));
        addQuick(quick, "⚽", "Jogos", userCards, content, "jogos");
        addQuick(quick, "👕", "Equipas", userCards, content, "equipas");
        addQuick(quick, "🎟️", "Bilhetes", userCards, content, "bilhetes");
        addQuick(quick, "📅", "Calendário", userCards, content, "jogos");
        addQuick(quick, "🏟️", "Estádios", userCards, content, "stadios");
        addQuick(quick, "🪪", "ID Digital", userCards, content, "perfil");
        p.add(header);
        p.add(Box.createVerticalStrut(12));
        p.add(hero);
        p.add(quick);
        p.add(sectionTitle("Próximos jogos"));
        // Mostrar os primeiros 2 jogos reais do singleton
        java.util.List<Jogo> jogos = GestorDados.getInstance().getJogos();
        if (jogos.size() > 0) {
            Jogo j1 = jogos.get(0);
            p.add(matchCard("Jornada 1",
                    j1.getSelecaoCasa().getNome(),
                    GAMES.get(0).time + "\n" + GAMES.get(0).date,
                    j1.getSelecaoFora().getNome(),
                    j1.getEstadio().getNome() + " · " + (j1.isVendaAberta() ? "Bilhetes disponíveis" : "Venda encerrada")));
        }
        if (jogos.size() > 1) {
            Jogo j2 = jogos.get(1);
            p.add(matchCard("Jornada 1",
                    j2.getSelecaoCasa().getNome(),
                    GAMES.get(1).time + "\n" + GAMES.get(1).date,
                    j2.getSelecaoFora().getNome(),
                    j2.getEstadio().getNome() + " · " + (j2.isVendaAberta() ? "Bilhetes disponíveis" : "Venda encerrada")));
        }
        return p;
    }

    private JPanel adminShell() {
        JPanel layout = new JPanel(new BorderLayout());
        layout.setBackground(LIGHT);
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBackground(DARK);
        sidebar.setBorder(new EmptyBorder(24, 18, 24, 18));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        JLabel brand = label("⚽ FUTFEVER", 23, Font.BOLD, Color.WHITE);
        if (LOGO != null) {
            brand.setIcon(LOGO);
            brand.setIconTextGap(10);
        }
        sidebar.add(brand);
        sidebar.add(label("ADMIN", 10, Font.BOLD, new Color(215, 242, 231)));
        sidebar.add(Box.createVerticalStrut(20));
        CardLayout adminCards = new CardLayout();
        JPanel main = new JPanel(adminCards);
        String[] pages = {"Dashboard", "Calendário", "Jogos", "Equipas", "Árbitros", "Estádios", "Grupos", "Bilhetes", "Utilizadores", "Relatórios"};
        for (String page : pages) {
            JButton nav = navButton(iconFor(page) + "  " + page);
            nav.addActionListener(e -> adminCards.show(main, page));
            sidebar.add(nav);
        }
        JButton sair = navButton("🚪  Sair");
        sair.addActionListener(e -> cards.show(root, "index"));
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(sair);
        main.add(adminDashboard(), "Dashboard");
        main.add(adminCalendario(), "Calendário");
        main.add(adminJogos(), "Jogos");
        main.add(adminEquipas(), "Equipas");
        main.add(adminArbitros(), "Árbitros");
        main.add(adminEstadios(), "Estádios");
        main.add(adminGrupos(), "Grupos");
        main.add(adminBilhetes(), "Bilhetes");
        main.add(adminUtilizadores(), "Utilizadores");
        main.add(adminTablePage("Relatórios"), "Relatórios");
        layout.add(sidebar, BorderLayout.WEST);
        layout.add(main, BorderLayout.CENTER);
        return layout;
    }

    private JComponent adminDashboard() {
        GestorDados repo = GestorDados.getInstance();
        int totalJogos = repo.getJogos().size();
        int totalBilhetes = repo.getBilhetes().stream().mapToInt(Bilhete::getQuantidade).sum();
        int totalEquipas = repo.getSelecoes().size();
        int totalArbitros = repo.getEquipasArbitragem().size();
        double totalReceita = repo.getBilhetes().stream().mapToDouble(Bilhete::getPrecoTotal).sum();
        String receitaStr = totalReceita >= 1_000_000
                ? String.format("€%.1fM", totalReceita / 1_000_000)
                : String.format("€%.0f", totalReceita);

        JPanel p = adminBase("Dashboard", "Backoffice - gestão completa da competição");
        JPanel stats = new JPanel(new GridLayout(1, 5, 18, 18));
        stats.setOpaque(false);
        stats.add(stat("Jogos", String.valueOf(totalJogos), "📅"));
        stats.add(stat("Bilhetes", totalBilhetes > 1000 ? (totalBilhetes / 1000) + "k" : String.valueOf(totalBilhetes), "🎟️"));
        stats.add(stat("Equipas", String.valueOf(totalEquipas), "👥"));
        stats.add(stat("Árbitros", String.valueOf(totalArbitros), "⚖️"));
        stats.add(stat("Receita", receitaStr, "💶"));
        p.add(stats);
        p.add(Box.createVerticalStrut(20));
        JPanel mid = new JPanel(new GridLayout(1, 2, 18, 18));
        mid.setOpaque(false);
        JPanel chart = card();
        chart.setLayout(new BorderLayout());
        chart.add(label("Jogos por Data", 18, Font.BOLD, Color.BLACK), BorderLayout.NORTH);
        chart.add(barChart(), BorderLayout.CENTER);
        JPanel donut = card();
        donut.setLayout(new BorderLayout());
        donut.add(label("Jogos por Estado", 18, Font.BOLD, Color.BLACK), BorderLayout.NORTH);
        donut.add(new JLabel("   🟢 Agendados    🟡 Em curso    🔴 Terminados", SwingConstants.CENTER), BorderLayout.CENTER);
        mid.add(chart);
        mid.add(donut);
        p.add(mid);
        p.add(Box.createVerticalStrut(20));
        // Tabela com jogos reais
        SimpleDateFormat dateFmt = new SimpleDateFormat("dd MMM", Locale.of("pt", "PT"));
        SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm");
        java.util.List<String[]> rows = new ArrayList<>();
        for (Jogo j : repo.getJogos()) {
            String grupo = j.getSelecaoCasa() != null && j.getSelecaoCasa().getGrupo() != null
                    ? j.getSelecaoCasa().getGrupo().getNome() : "—";
            rows.add(new String[]{
                    j.getDataHora() != null ? dateFmt.format(j.getDataHora()) : "—",
                    j.getDataHora() != null ? timeFmt.format(j.getDataHora()) : "—",
                    grupo,
                    j.getSelecaoCasa() != null ? j.getSelecaoCasa().getNome() : "—",
                    j.getSelecaoFora() != null ? j.getSelecaoFora().getNome() : "—",
                    j.getEstadio() != null ? j.getEstadio().getNome() : "—",
                    j.isVendaAberta() ? "Agendado" : "Encerrado"
            });
        }
        p.add(tableCard("Próximos jogos",
                new String[]{"Data", "Hora", "Grupo", "Casa", "Fora", "Estádio", "Estado"},
                rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    // ── Admin pages backed by real singleton data ──────────────────────────

    private JComponent adminCalendario() {
        JPanel p = adminBase("Calendário", "Gestão de calendário");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        SimpleDateFormat dateFmt = new SimpleDateFormat("dd MMM", Locale.of("pt", "PT"));
        SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm");
        java.util.List<String[]> rows = new ArrayList<>();
        for (Jogo j : GestorDados.getInstance().getJogos()) {
            rows.add(new String[]{
                    j.getDataHora() != null ? dateFmt.format(j.getDataHora()) : "—",
                    j.getDataHora() != null ? timeFmt.format(j.getDataHora()) : "—",
                    j.getSelecaoCasa() != null ? j.getSelecaoCasa().getNome() : "—",
                    j.getSelecaoFora() != null ? j.getSelecaoFora().getNome() : "—",
                    j.getEstadio() != null ? j.getEstadio().getNome() : "—",
                    j.isVendaAberta() ? "Ativo" : "Encerrado"
            });
        }
        p.add(tableCard("Lista", new String[]{"Data", "Hora", "Casa", "Fora", "Estádio", "Estado"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminJogos() {
        JPanel p = adminBase("Jogos", "Gestão de jogos");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        java.util.List<String[]> rows = new ArrayList<>();
        for (Jogo j : GestorDados.getInstance().getJogos()) {
            rows.add(new String[]{
                    j.getSelecaoCasa() != null ? j.getSelecaoCasa().getNome() : "—",
                    j.getSelecaoFora() != null ? j.getSelecaoFora().getNome() : "—",
                    j.getEstadio() != null ? j.getEstadio().getNome() : "—",
                    j.getEquipaArbitragem() != null ? j.getEquipaArbitragem().getNome() : "—",
                    String.valueOf(j.getLugaresDisponiveis()),
                    j.isVendaAberta() ? "Ativo" : "Encerrado"
            });
        }
        p.add(tableCard("Lista", new String[]{"Casa", "Fora", "Estádio", "Árbitros", "Lugares", "Estado"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminEquipas() {
        JPanel p = adminBase("Equipas", "Gestão de equipas");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        java.util.List<String[]> rows = new ArrayList<>();
        for (Selecao s : GestorDados.getInstance().getSelecoes()) {
            rows.add(new String[]{
                    s.getNome(),
                    s.getTreinador() != null ? s.getTreinador().getName() : "—",
                    s.getGrupo() != null ? s.getGrupo().getNome() : "—",
                    s.getCentroDeEstagio() != null ? s.getCentroDeEstagio().getNome() : "—",
                    String.valueOf(s.getJogadores().size()),
                    s.isApurada() ? "Apurada" : "Em competição"
            });
        }
        p.add(tableCard("Lista", new String[]{"Equipa", "Treinador", "Grupo", "Centro Estágio", "Jogadores", "Estado"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminArbitros() {
        JPanel p = adminBase("Árbitros", "Gestão de árbitros");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        java.util.List<String[]> rows = new ArrayList<>();
        for (EquipaArbitragem ea : GestorDados.getInstance().getEquipasArbitragem()) {
            rows.add(new String[]{
                    ea.getNome(),
                    ea.getNacionalidade(),
                    String.valueOf(ea.getJogos().size()),
                    "Ativo"
            });
        }
        p.add(tableCard("Lista", new String[]{"Nome", "Nacionalidade", "Jogos", "Estado"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminEstadios() {
        JPanel p = adminBase("Estádios", "Gestão de estádios");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        java.util.List<String[]> rows = new ArrayList<>();
        for (Estadio e : GestorDados.getInstance().getEstadios()) {
            rows.add(new String[]{
                    e.getNome(),
                    String.format("%.4f, %.4f", e.getLatitude(), e.getLongitude()),
                    String.valueOf(e.getLotacao()),
                    String.valueOf(e.getJogos().size()),
                    "Ativo"
            });
        }
        p.add(tableCard("Lista", new String[]{"Nome", "Localização", "Lotação", "Jogos", "Estado"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminGrupos() {
        JPanel p = adminBase("Grupos", "Gestão de grupos");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        java.util.List<String[]> rows = new ArrayList<>();
        for (Grupo g : GestorDados.getInstance().getGrupos()) {
            StringBuilder equipas = new StringBuilder();
            for (Selecao s : g.getSelecoes()) {
                if (equipas.length() > 0) equipas.append(", ");
                equipas.append(s.getNome());
            }
            rows.add(new String[]{g.getNome(), equipas.toString(), String.valueOf(g.getSelecoes().size()), "Ativo"});
        }
        p.add(tableCard("Lista", new String[]{"Grupo", "Equipas", "Total", "Estado"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminBilhetes() {
        JPanel p = adminBase("Bilhetes", "Gestão de bilhetes");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        java.util.List<String[]> rows = new ArrayList<>();
        SimpleDateFormat dtFmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Bilhete b : GestorDados.getInstance().getBilhetes()) {
            String jogo = b.getJogo() != null
                    ? b.getJogo().getSelecaoCasa().getNome() + " vs " + b.getJogo().getSelecaoFora().getNome()
                    : "—";
            String comprador = b.getComprador() != null ? b.getComprador().getName() : "—";
            rows.add(new String[]{
                    b.getBilheteDigital(),
                    jogo,
                    comprador,
                    String.valueOf(b.getQuantidade()),
                    String.format("%.0f€", b.getPrecoTotal()),
                    b.getDataCompra() != null ? dtFmt.format(b.getDataCompra()) : "—"
            });
        }
        if (rows.isEmpty()) rows.add(new String[]{"—", "Sem bilhetes vendidos", "—", "—", "—", "—"});
        p.add(tableCard("Lista", new String[]{"Referência", "Jogo", "Comprador", "Qtd", "Total", "Data"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminUtilizadores() {
        JPanel p = adminBase("Utilizadores", "Gestão de utilizadores");
        p.add(formCard());
        p.add(Box.createVerticalStrut(18));
        java.util.List<String[]> rows = new ArrayList<>();
        for (User u : GestorDados.getInstance().getUtilizadores()) {
            String tipo = u instanceof Gestor ? "Gestor"
                    : u instanceof Treinador ? "Treinador"
                      : u instanceof Jogador ? "Jogador"
                        : u instanceof Funcionario ? "Funcionário"
                          : "Utilizador";
            rows.add(new String[]{String.valueOf(u.getId()), u.getName(), u.getEmail(), tipo, "Ativo"});
        }
        p.add(tableCard("Lista", new String[]{"ID", "Nome", "Email", "Tipo", "Estado"}, rows.toArray(new String[0][])));
        return new JScrollPane(p);
    }

    private JComponent adminTablePage(String title) {
        JPanel p = adminBase(title, "Gestão de " + title.toLowerCase());
        JPanel form = card();
        form.setLayout(new GridLayout(2, 3, 12, 12));
        form.add(input("Nome / título"));
        form.add(input("Data / país"));
        form.add(input("Estado"));
        form.add(input("Observações"));
        form.add(button("Guardar"));
        form.add(button("Limpar"));
        p.add(form);
        p.add(Box.createVerticalStrut(18));
        p.add(tableCard("Lista", new String[]{"Nome", "Categoria", "Estado", "Ações"}, new String[][]{{title + " 1", "Principal", "Ativo", "Editar / Remover"}, {title + " 2", "Secundário", "Pendente", "Editar / Remover"}, {title + " 3", "Principal", "Ativo", "Editar / Remover"}}));
        return new JScrollPane(p);
    }

    // Painel de formulário genérico reutilizado nas páginas admin
    private JPanel formCard() {
        JPanel form = card();
        form.setLayout(new GridLayout(2, 3, 12, 12));
        form.add(input("Nome / título"));
        form.add(input("Data / país"));
        form.add(input("Estado"));
        form.add(input("Observações"));
        form.add(button("Guardar"));
        form.add(button("Limpar"));
        return form;
    }

    // Tela de bilhetes reais a partir do singleton
    private JPanel bilhetesScreen() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label("🎟️  Bilhetes", 24, Font.BOLD, GREEN));
        p.add(Box.createVerticalStrut(16));
        java.util.List<Bilhete> bilhetes = GestorDados.getInstance().getBilhetes();
        if (bilhetes.isEmpty()) {
            p.add(label("Nenhum bilhete comprado ainda.", 14, Font.PLAIN, MUTED));
        } else {
            for (Bilhete b : bilhetes) {
                String jogo = b.getJogo() != null
                        ? b.getJogo().getSelecaoCasa().getNome() + " vs " + b.getJogo().getSelecaoFora().getNome()
                        : "—";
                String row = jogo + "  ·  " + String.format("%.0f€", b.getPrecoTotal())
                        + "  ·  " + b.getQuantidade() + " bilhete(s)";
                JPanel c = card();
                c.setMaximumSize(new Dimension(390, 76));
                c.setLayout(new BorderLayout());
                c.add(label(row, 14, Font.BOLD, Color.BLACK), BorderLayout.CENTER);
                c.add(label("Ref: " + b.getBilheteDigital(), 11, Font.PLAIN, MUTED), BorderLayout.SOUTH);
                p.add(c);
                p.add(Box.createVerticalStrut(10));
            }
        }
        return p;
    }

    private JPanel adminBase(String title, String subtitle) {
        JPanel p = new JPanel();
        p.setBackground(LIGHT);
        p.setBorder(new EmptyBorder(28, 28, 28, 28));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label(title, 32, Font.BOLD, Color.BLACK));
        p.add(label(subtitle, 14, Font.PLAIN, MUTED));
        p.add(Box.createVerticalStrut(24));
        return p;
    }

    private JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new CompoundBorder(new LineBorder(LINE, 1, true), new EmptyBorder(18, 18, 18, 18)));
        return p;
    }

    private JLabel label(String s, int size, int style, Color c) {
        JLabel l = new JLabel(s);
        l.setFont(new Font("SansSerif", style, size));
        l.setForeground(c);
        return l;
    }

    private JTextArea text(String s, int size) {
        JTextArea a = new JTextArea(s);
        a.setFont(new Font("SansSerif", Font.PLAIN, size));
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setOpaque(false);
        a.setEditable(false);
        return a;
    }

    private JButton button(String s) {
        JButton b = new JButton(s);
        b.setForeground(Color.WHITE);
        b.setBackground(GREEN);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(11, 16, 11, 16));
        return b;
    }

    private JButton whiteButton(String s) {
        JButton b = button(s);
        b.setBackground(Color.WHITE);
        b.setForeground(GREEN);
        return b;
    }

    private JButton smallButton(String s) {
        JButton b = whiteButton(s);
        b.setBorder(new EmptyBorder(8, 10, 8, 10));
        return b;
    }

    private JButton navButton(String s) {
        JButton b = new JButton(s);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setForeground(new Color(215, 242, 231));
        b.setBackground(DARK);
        b.setBorder(new EmptyBorder(12, 14, 12, 14));
        b.setFocusPainted(false);
        return b;
    }

    private JTextField input(String ph) {
        JTextField f = new JTextField(ph);
        f.setBorder(new CompoundBorder(new LineBorder(LINE), new EmptyBorder(10, 10, 10, 10)));
        return f;
    }

    private JPanel gradientPanel() {
        return new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(0, 0, DARK, getWidth(), getHeight(), GREEN));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            }

            public boolean isOpaque() {
                return false;
            }
        };
    }

    private void addQuick(JPanel q, String icon, String txt, CardLayout cl, JPanel content, String target) {
        JButton b = whiteButton("<html><center><span style='font-size:20px'>" + icon + "</span><br>" + txt + "</center></html>");
        b.setBorder(new CompoundBorder(new LineBorder(LINE, 1, true), new EmptyBorder(12, 5, 12, 5)));
        b.addActionListener(e -> cl.show(content, target));
        q.add(b);
    }

    private JLabel sectionTitle(String s) {
        JLabel l = label(s, 20, Font.BOLD, Color.BLACK);
        l.setBorder(new EmptyBorder(0, 0, 8, 0));
        return l;
    }

    private JPanel matchCard(String group, String home, String time, String away, String stadium) {
        JPanel c = card();
        c.setMaximumSize(new Dimension(390, 120));
        c.setLayout(new BorderLayout());
        c.add(label(group, 12, Font.PLAIN, MUTED), BorderLayout.NORTH);
        JLabel teams = label(home + "     " + time.replace("\n", " ") + "     " + away, 16, Font.BOLD, GREEN);
        c.add(teams, BorderLayout.CENTER);
        c.add(label(stadium, 12, Font.PLAIN, MUTED), BorderLayout.SOUTH);
        return c;
    }

    // Tela que lista os estádios (únicos) a partir dos jogos e permite clicar para ver os jogos nesse estádio
    private JPanel stadiumsScreen(CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label("🏟️  Estádios com jogos", 24, Font.BOLD, GREEN));
        p.add(Box.createVerticalStrut(16));
        // obter estádios únicos preservando ordem
        java.util.Set<String> stadiums = new java.util.LinkedHashSet<>();
        for (Game g : GAMES) stadiums.add(g.stadium);
        for (String s : stadiums) {
            JPanel c = card();
            c.setMaximumSize(new Dimension(430, 76));
            c.setLayout(new BorderLayout());
            JLabel l = label(s, 15, Font.BOLD, Color.BLACK);
            c.add(l, BorderLayout.CENTER);
            JButton ver = whiteButton("Ver jogos");
            ver.addActionListener(e -> {
                String key = "stadium:" + s;
                // se ainda não existe, adicionar painel com os jogos
                for (Component comp : content.getComponents()) {
                    if (key.equals(comp.getName())) {
                        userCards.show(content, key);
                        return;
                    }
                }
                JPanel details = stadiumGamesPanel(s, userCards, content);
                details.setName(key);
                content.add(details, key);
                userCards.show(content, key);
            });
            c.add(ver, BorderLayout.EAST);
            p.add(c);
            p.add(Box.createVerticalStrut(10));
        }
        return p;
    }

    // Painel que mostra todos os jogos que se realizam num estádio
    private JPanel stadiumGamesPanel(String stadium, CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label("Jogos em " + stadium, 20, Font.BOLD, Color.BLACK));
        p.add(Box.createVerticalStrut(12));
        boolean found = false;
        for (Game g : GAMES) {
            if (stadium.equals(g.stadium)) {
                JPanel c = card();
                c.setMaximumSize(new Dimension(430, 80));
                c.setLayout(new BorderLayout());
                c.add(label(g.home + "  vs  " + g.away, 16, Font.BOLD, GREEN), BorderLayout.CENTER);
                c.add(label(g.date + "  " + g.time, 12, Font.PLAIN, MUTED), BorderLayout.SOUTH);
                p.add(c);
                p.add(Box.createVerticalStrut(10));
                found = true;
            }
        }
        if (!found) {
            p.add(label("Nenhum jogo encontrado neste estádio.", 14, Font.PLAIN, MUTED));
        }
        JButton back = button("Voltar");
        back.addActionListener(e -> {
            userCards.show(content, "stadios");
        });
        p.add(Box.createVerticalStrut(12));
        p.add(back);
        return p;
    }

    // Tela que lista jogos a partir do modelo GAMES e permite ver detalhes de cada jogo
    private JPanel gamesScreen(CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label("⚽  Jogos", 24, Font.BOLD, GREEN));
        p.add(Box.createVerticalStrut(16));
        for (int i = 0; i < GAMES.size(); i++) {
            Game g = GAMES.get(i);
            JPanel c = card();
            c.setMaximumSize(new Dimension(430, 80));
            c.setLayout(new BorderLayout());
            c.add(label(g.home + "  vs  " + g.away, 16, Font.BOLD, Color.BLACK), BorderLayout.CENTER);
            c.add(label(g.date + "  " + g.time + " · " + g.stadium, 12, Font.PLAIN, MUTED), BorderLayout.SOUTH);
            JButton det = whiteButton("Detalhes");
            final int idx = i;
            det.addActionListener(e -> {
                String key = "game:" + idx;
                for (Component comp : content.getComponents()) {
                    if (key.equals(comp.getName())) {
                        userCards.show(content, key);
                        return;
                    }
                }
                JPanel details = gameDetailsPanel(GAMES.get(idx), GestorDados.getInstance().getJogos().get(idx), userCards, content);
                details.setName(key);
                content.add(details, key);
                userCards.show(content, key);
            });
            c.add(det, BorderLayout.EAST);
            p.add(c);
            p.add(Box.createVerticalStrut(10));
        }
        return p;
    }

    // Painel de detalhe do jogo com opção de comprar bilhetes e voltar
    private JPanel gameDetailsPanel(Game g, Jogo jogo, CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label(g.home + "  vs  " + g.away, 22, Font.BOLD, GREEN));
        p.add(Box.createVerticalStrut(8));
        p.add(label(g.date + "  " + g.time, 14, Font.PLAIN, Color.BLACK));
        p.add(label("Estádio: " + g.stadium, 14, Font.PLAIN, MUTED));
        p.add(label("Lugares disponíveis: " + jogo.getLugaresDisponiveis(), 14, Font.PLAIN, MUTED));
        p.add(Box.createVerticalStrut(12));
        JButton buy = button("Comprar bilhetes");
        buy.addActionListener(e -> {
            if (!jogo.isVendaAberta()) {
                JOptionPane.showMessageDialog(this, "A venda de bilhetes para este jogo está encerrada.", "Venda encerrada", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // Formulário simples para recolher telefone, número do cartão de cidadão e quantidade
            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            form.add(new JLabel("Telefone:"), gbc);
            gbc.gridx = 1;
            JTextField phoneField = new JTextField(12);
            form.add(phoneField, gbc);
            gbc.gridx = 0;
            gbc.gridy++;
            form.add(new JLabel("Nº Cartão Cidadão:"), gbc);
            gbc.gridx = 1;
            JTextField ccField = new JTextField(12);
            form.add(ccField, gbc);
            gbc.gridx = 0;
            gbc.gridy++;
            form.add(new JLabel("Quantidade:"), gbc);
            gbc.gridx = 1;
            JSpinner qty = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
            form.add(qty, gbc);

            int res = JOptionPane.showConfirmDialog(this, form, "Comprar bilhetes - " + g.home + " vs " + g.away, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                String phone = phoneField.getText().trim();
                String cc = ccField.getText().trim();
                int quantity = (Integer) qty.getValue();
                // validações simples
                if (phone.isEmpty() || cc.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor preencha telefone e número do cartão de cidadão.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!phone.matches("[0-9+\\-\\s]{6,20}")) {
                    JOptionPane.showMessageDialog(this, "Telefone inválido. Use dígitos, espaços, + ou -.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!cc.matches("\\d{6,20}")) {
                    JOptionPane.showMessageDialog(this, "Número do cartão de cidadão inválido (apenas dígitos).", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int unitPrice = 100;
                int total = unitPrice * quantity;
                int confirm = JOptionPane.showConfirmDialog(this, String.format("Total: %d€ (100€ x %d)\nConfirmar compra?", total, quantity), "Confirmar compra", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Criar utilizador público temporário e comprar bilhete via singleton
                    UtilizadorPublico comprador = new UtilizadorPublico(
                            GestorDados.getInstance().getUtilizadores().size() + 1,
                            "Utilizador", "utilizador@futfever.pt", cc, phone);
                    Bilhete bilhete = comprador.comprarBilhete(jogo, quantity, cc, phone);
                    if (bilhete != null) {
                        JOptionPane.showMessageDialog(this, String.format(
                                "Compra efetuada!\n%s vs %s\nQuantidade: %d\nTotal: %d€\nContacto: %s\nCC: %s\nReferência: %s",
                                g.home, g.away, quantity, total, phone, cc, bilhete.getBilheteDigital()));
                    } else {
                        JOptionPane.showMessageDialog(this, "Não foi possível concluir a compra. Verifique a disponibilidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        p.add(buy);
        JButton back = whiteButton("Voltar aos jogos");
        back.addActionListener(e -> userCards.show(content, "jogos"));
        p.add(Box.createVerticalStrut(12));
        p.add(back);
        return p;
    }

    // Tela que lista equipas (únicas) a partir dos jogos e permite clicar para ver os jogos da equipa
    private JPanel teamsScreen(CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label("👕  Equipas", 24, Font.BOLD, GREEN));
        p.add(Box.createVerticalStrut(16));
        // obter equipas únicas preservando ordem
        java.util.Set<String> teams = new java.util.LinkedHashSet<>();
        for (Game g : GAMES) {
            teams.add(g.home);
            teams.add(g.away);
        }
        for (String t : teams) {
            JPanel c = card();
            c.setMaximumSize(new Dimension(430, 76));
            c.setLayout(new BorderLayout());
            JLabel l = label(t, 15, Font.BOLD, Color.BLACK);
            c.add(l, BorderLayout.CENTER);
            JPanel actions = new JPanel(new GridLayout(2, 1, 0, 6));
            actions.setOpaque(false);
            JButton details = whiteButton("Detalhes");
            details.addActionListener(e -> {
                String key = "teaminfo:" + t;
                for (Component comp : content.getComponents()) {
                    if (key.equals(comp.getName())) {
                        userCards.show(content, key);
                        return;
                    }
                }
                JPanel info = teamDetailsPanel(t, userCards, content);
                info.setName(key);
                content.add(info, key);
                userCards.show(content, key);
            });
            JButton ver = whiteButton("Ver jogos");
            ver.addActionListener(e -> {
                String key = "team:" + t;
                for (Component comp : content.getComponents()) {
                    if (key.equals(comp.getName())) {
                        userCards.show(content, key);
                        return;
                    }
                }
                JPanel detailsPanel = teamGamesPanel(t, userCards, content);
                detailsPanel.setName(key);
                content.add(detailsPanel, key);
                userCards.show(content, key);
            });
            actions.add(details);
            actions.add(ver);
            c.add(actions, BorderLayout.EAST);
            p.add(c);
            p.add(Box.createVerticalStrut(10));
        }
        return p;
    }

    // Painel que mostra jogos de uma equipa
    private JPanel teamGamesPanel(String team, CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label("Jogos de " + team, 20, Font.BOLD, Color.BLACK));
        p.add(Box.createVerticalStrut(12));
        // botão para ver detalhes da equipa (treinador e jogadores)
        JButton detailsTeam = whiteButton("Detalhes equipa");
        detailsTeam.addActionListener(e -> {
            String key = "teaminfo:" + team;
            for (Component comp : content.getComponents()) {
                if (key.equals(comp.getName())) {
                    userCards.show(content, key);
                    return;
                }
            }
            JPanel info = teamDetailsPanel(team, userCards, content);
            info.setName(key);
            content.add(info, key);
            userCards.show(content, key);
        });
        p.add(detailsTeam);
        p.add(Box.createVerticalStrut(8));
        boolean found = false;
        for (int i = 0; i < GAMES.size(); i++) {
            Game g = GAMES.get(i);
            if (team.equals(g.home) || team.equals(g.away)) {
                JPanel c = card();
                c.setMaximumSize(new Dimension(430, 80));
                c.setLayout(new BorderLayout());
                c.add(label(g.home + "  vs  " + g.away, 16, Font.BOLD, GREEN), BorderLayout.CENTER);
                c.add(label(g.date + "  " + g.time + " · " + g.stadium, 12, Font.PLAIN, MUTED), BorderLayout.SOUTH);
                JButton det = whiteButton("Detalhes");
                final int idx = i;
                det.addActionListener(e -> {
                    String key = "game:" + idx;
                    for (Component comp : content.getComponents()) {
                        if (key.equals(comp.getName())) {
                            userCards.show(content, key);
                            return;
                        }
                    }
                    JPanel details = gameDetailsPanel(GAMES.get(idx), GestorDados.getInstance().getJogos().get(idx), userCards, content);
                    details.setName(key);
                    content.add(details, key);
                    userCards.show(content, key);
                });
                c.add(det, BorderLayout.EAST);
                p.add(c);
                p.add(Box.createVerticalStrut(10));
                found = true;
            }
        }
        if (!found) p.add(label("Nenhum jogo encontrado para esta equipa.", 14, Font.PLAIN, MUTED));
        JButton back = button("Voltar");
        back.addActionListener(e -> {
            userCards.show(content, "equipas");
        });
        p.add(Box.createVerticalStrut(12));
        p.add(back);
        return p;
    }

    // Painel que mostra detalhes da equipa: treinador e jogadores — lê do TEAM_INFO que foi populado pelo singleton
    private JPanel teamDetailsPanel(String team, CardLayout userCards, JPanel content) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label("Detalhes da equipa: " + team, 20, Font.BOLD, Color.BLACK));
        p.add(Box.createVerticalStrut(12));
        TeamInfo info = TEAM_INFO.get(team);
        if (info != null) {
            p.add(label("Treinador: " + info.coach, 14, Font.PLAIN, Color.BLACK));
            p.add(Box.createVerticalStrut(8));
            p.add(label("Jogadores:", 14, Font.BOLD, Color.BLACK));
            for (String pl : info.players) {
                p.add(label("- " + pl, 13, Font.PLAIN, MUTED));
            }
        } else {
            p.add(label("Sem informações de equipa.", 14, Font.PLAIN, MUTED));
        }
        p.add(Box.createVerticalStrut(12));
        JButton back = button("Voltar às equipas");
        back.addActionListener(e -> userCards.show(content, "equipas"));
        p.add(back);
        return p;
    }

    private JPanel profileScreen() {
        GestorDados repo = GestorDados.getInstance();
        int totalBilhetes = repo.getBilhetes().stream().mapToInt(Bilhete::getQuantidade).sum();
        JPanel p = simpleListScreen("Perfil", "👤", new String[]{
                "João Silva",
                "joao@email.com",
                "Bilhetes comprados: " + totalBilhetes,
                "Notificações ativas"
        });
        JButton back = button("Voltar ao início");
        back.addActionListener(e -> cards.show(root, "index"));
        p.add(back);
        return p;
    }

    private JPanel simpleListScreen(String title, String icon, String[] rows) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(24, 22, 10, 22));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label(icon + "  " + title, 24, Font.BOLD, GREEN));
        p.add(Box.createVerticalStrut(16));
        for (String r : rows) {
            JPanel c = card();
            c.setMaximumSize(new Dimension(390, 76));
            c.setLayout(new BorderLayout());
            c.add(label(r, 15, Font.BOLD, Color.BLACK), BorderLayout.CENTER);
            p.add(c);
            p.add(Box.createVerticalStrut(10));
        }
        return p;
    }

    private JPanel bottomNav(CardLayout cl, JPanel content) {
        JPanel n = new JPanel(new GridLayout(1, 4));
        n.setBorder(new MatteBorder(1, 0, 0, 0, LINE));
        String[][] items = {{"⌂\nInício", "home"}, {"⚽\nJogos", "jogos"}, {"🎟️\nBilhetes", "bilhetes"}, {"👤\nPerfil", "perfil"}};
        for (String[] it : items) {
            JButton b = whiteButton("<html><center>" + it[0].replace("\n", "<br>") + "</center></html>");
            b.addActionListener(e -> cl.show(content, it[1]));
            n.add(b);
        }
        return n;
    }

    private JPanel stat(String name, String value, String icon) {
        JPanel c = card();
        c.setLayout(new BorderLayout());
        c.add(label(name, 13, Font.PLAIN, MUTED), BorderLayout.NORTH);
        c.add(label(value, 24, Font.BOLD, Color.BLACK), BorderLayout.CENTER);
        c.add(label(icon, 24, Font.PLAIN, GREEN), BorderLayout.EAST);
        return c;
    }

    private JPanel barChart() {
        return new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int[] h = {40, 80, 120, 95, 170, 140};
                int w = getWidth() / 8;
                g.setColor(GREEN);
                for (int i = 0; i < h.length; i++)
                    g.fillRoundRect(25 + i * (w + 25), getHeight() - h[i] - 20, w, h[i], 10, 10);
            }
        };
    }

    private JPanel tableCard(String title, String[] cols, String[][] rows) {
        JPanel c = card();
        c.setLayout(new BorderLayout(0, 12));
        c.add(label(title, 18, Font.BOLD, Color.BLACK), BorderLayout.NORTH);
        JTable t = new JTable(rows, cols);
        t.setRowHeight(32);
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        c.add(new JScrollPane(t), BorderLayout.CENTER);
        return c;
    }

    private String iconFor(String p) {
        switch (p) {
            case "Dashboard":
                return "📊";
            case "Calendário":
                return "📅";
            case "Jogos":
                return "⚽";
            case "Equipas":
                return "👕";
            case "Árbitros":
                return "👨‍⚖️";
            case "Estádios":
                return "🏟️";
            case "Grupos":
                return "🧩";
            case "Bilhetes":
                return "🎟️";
            case "Utilizadores":
                return "👥";
            default:
                return "📈";
        }
    }
}
