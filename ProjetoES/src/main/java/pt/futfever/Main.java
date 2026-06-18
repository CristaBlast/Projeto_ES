import pt.futfever.model.*;

private static Date criarData(int ano, int mes, int dia, int hora, int minuto) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(ano, mes, dia, hora, minuto, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
}

void main() {
    System.out.println("=========================================");
    System.out.println(" FUTFEVER - Sistema de Gestão de Futebol ");
    System.out.println("=========================================\n");

    // ---------------------------------------------------------
    // 1. Obter a instância única do repositório de dados
    // ---------------------------------------------------------
    GestorDados repositorio = GestorDados.getInstance();

    // ---------------------------------------------------------
    // 2. Criar um Gestor e autenticar
    // ---------------------------------------------------------
    Gestor gestor = new Gestor(1, "Ana Silva", "ana.silva@futfever.pt", "admin", "1234");

    System.out.println("--- Autenticação ---");
    if (gestor.login("admin", "1234")) {
        System.out.println("Login efetuado com sucesso como " + gestor.getName() + "\n");
    } else {
        System.out.println("Falha no login.\n");
        return;
    }

    // ---------------------------------------------------------
    // 3. Gestor regista Estádios no repositório
    // ---------------------------------------------------------
    Estadio estadioLuz = new Estadio(1, "Estádio da Luz", 38.7527, -9.1847, 65000);
    Estadio estadioDragao = new Estadio(2, "Estádio do Dragão", 41.1620, -8.5828, 50000);

    gestor.gerirEstadio(estadioLuz);
    gestor.gerirEstadio(estadioDragao);

    System.out.println("--- Estádios registados ---");
    for (Estadio e : repositorio.getEstadios()) {
        System.out.println("- " + e.getNome() + " (capacidade: " + e.getLotacao() + ")");
    }
    System.out.println();

    // ---------------------------------------------------------
    // 4. Gestor regista Seleções no repositório
    // ---------------------------------------------------------
    Selecao portugal = new Selecao(1, "Portugal");
    Selecao espanha = new Selecao(2, "Espanha");
    Selecao franca = new Selecao(3, "França");

    gestor.gerirEquipa(portugal);
    gestor.gerirEquipa(espanha);
    gestor.gerirEquipa(franca);

    System.out.println("--- Seleções registadas ---");
    for (Selecao s : repositorio.getSelecoes()) {
        System.out.println("- " + s.getNome());
    }
    System.out.println();

    // ---------------------------------------------------------
    // 5. Gestor organiza um Grupo e associa um Centro de Estágio
    // ---------------------------------------------------------
    Grupo grupoA = new Grupo(1, "Grupo A");
    grupoA.adicionarSelecao(portugal);
    grupoA.adicionarSelecao(espanha);
    grupoA.adicionarSelecao(franca);
    gestor.gerirGrupos(grupoA);

    CentroDeEstagio cidadeDoFutebol = new CentroDeEstagio(1, "Cidade do Futebol", "Oeiras");
    gestor.gerirCentroEstagios(portugal, cidadeDoFutebol);

    System.out.println("--- Grupo A ---");
    for (Selecao s : grupoA.getSelecoes()) {
        System.out.println("- " + s.getNome());
    }
    System.out.println("Centro de estágio de Portugal: " + portugal.getCentroDeEstagio().getNome() + "\n");

    // ---------------------------------------------------------
    // 6. Gestor agenda um Jogo, atribui Equipa de Arbitragem e abre a venda
    // ---------------------------------------------------------
    Date dataJogo = criarData(2026, Calendar.JUNE, 20, 21, 0);
    Jogo jogo = new Jogo(1, dataJogo, portugal, espanha, estadioLuz);
    gestor.gerirJogos(jogo);
    estadioLuz.adicionarJogo(jogo);

    EquipaArbitragem arbitros = new EquipaArbitragem(1, "Equipa de Arbitragem Italiana", "Itália");
    gestor.gerirArbitragem(jogo, arbitros);
    gestor.abrirVendaBilhetes(jogo);

    System.out.println("--- Jogo agendado ---");
    System.out.println(jogo.getSelecaoCasa().getNome() + " vs " + jogo.getSelecaoFora().getNome());
    System.out.println("Estádio: " + jogo.getEstadio().getNome());
    System.out.println("Árbitros: " + jogo.getEquipaArbitragem().getNome());
    System.out.println("Venda de bilhetes aberta: " + jogo.isVendaAberta());
    System.out.println("Lugares disponíveis: " + jogo.getLugaresDisponiveis() + "\n");

    // ---------------------------------------------------------
    // 7. Gestor regista uma Deslocação da seleção
    // ---------------------------------------------------------
    Deslocacao viagem = new Deslocacao(1, "Lisboa", "Madrid", dataJogo, espanha);
    gestor.registarDeslocacao(espanha, viagem);
    System.out.println("--- Deslocação registada ---");
    System.out.println(viagem.getOrigem() + " -> " + viagem.getDestino()
            + " (Seleção: " + viagem.getSelecao().getNome() + ")\n");

    // ---------------------------------------------------------
    // 8. Um Utilizador Público compra bilhetes para o jogo
    // ---------------------------------------------------------
    UtilizadorPublico cris = new UtilizadorPublico(10, "Cris", "cris@mail.pt", "12345678", "910000000");

    Bilhete bilhete = cris.comprarBilhete(jogo, 2, cris.getCartaoCidadao(), cris.getTelefone());

    System.out.println("--- Compra de bilhetes ---");
    if (bilhete != null) {
        System.out.println("Bilhete: " + bilhete.getBilheteDigital());
        System.out.println("Preço total: " + bilhete.getPrecoTotal() + "€");
        System.out.println("QR Code: " + bilhete.getQrcode().getCodigo());
        System.out.println("Lugares disponíveis após a compra: " + jogo.getLugaresDisponiveis());
    }
    System.out.println();

    // ---------------------------------------------------------
    // 9. Validação do bilhete à entrada do estádio
    // ---------------------------------------------------------
    System.out.println("--- Validação à entrada ---");
    System.out.println("1ª validação do QR Code (deve ser true): " + bilhete.getQrcode().validar());
    System.out.println("2ª validação do mesmo QR Code (deve ser false, bilhete já usado): "
            + bilhete.getQrcode().validar() + "\n");

    // ---------------------------------------------------------
    // 10. Um Utilizador Público consulta o calendário (via Singleton)
    //     Mostra que os dados são partilhados em qualquer ponto da app,
    //     sem que este utilizador tenha acesso direto ao Gestor.
    // ---------------------------------------------------------
    System.out.println("--- Calendário consultado por um utilizador público ---");
    List<Jogo> calendario = cris.visualizarCalendario();
    for (Jogo j : calendario) {
        System.out.println(j.getSelecaoCasa().getNome() + " vs " + j.getSelecaoFora().getNome()
                + " em " + j.getEstadio().getNome());
    }

    // ---------------------------------------------------------
    // 11. Prova de que o Singleton é único em toda a aplicação
    // ---------------------------------------------------------
    GestorDados outraReferencia = GestorDados.getInstance();
    System.out.println("\n--- Verificação do Singleton ---");
    System.out.println("repositorio == outraReferencia ? " + (repositorio == outraReferencia));
    System.out.println("Total de jogos no repositório: " + outraReferencia.getJogos().size());
}

