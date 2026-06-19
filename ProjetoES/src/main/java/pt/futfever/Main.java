package pt.futfever.app;

import pt.futfever.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println(" FUTFEVER - Sistema de Gestão de Futebol ");
        System.out.println("=========================================\n");

        // ---------------------------------------------------------
        // 1. Obter a instância única do repositório e pré-populá-la
        // ---------------------------------------------------------
        GestorDados repositorio = GestorDados.getInstance();
        repositorio.popularDados();

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
        // 3. Ler as seleções e plantéis diretamente do repositório
        // ---------------------------------------------------------
        System.out.println("--- Seleções e plantéis ---");
        for (Selecao s : repositorio.getSelecoes()) {
            System.out.println("\n" + s.getNome().toUpperCase()
                    + " | Treinador: " + s.getTreinador().getName());
            System.out.println("  Nº  Nome                    Posição");
            System.out.println("  --  ----------------------  -------");
            for (Jogador j : s.getJogadores()) {
                System.out.printf("  %-3d %-22s  %s%n",
                        j.getNumCamisola(), j.getName(), j.getPosicao());
            }
        }
        System.out.println();

        // ---------------------------------------------------------
        // 4. Registar Estádios
        // ---------------------------------------------------------
        Estadio estadioLuz    = new Estadio(1, "Estádio da Luz",    38.7527, -9.1847, 65000);
        Estadio estadioDragao = new Estadio(2, "Estádio do Dragão", 41.1620, -8.5828, 50000);
        gestor.gerirEstadio(estadioLuz);
        gestor.gerirEstadio(estadioDragao);

        System.out.println("--- Estádios registados ---");
        for (Estadio e : repositorio.getEstadios()) {
            System.out.println("- " + e.getNome() + " (capacidade: " + e.getLotacao() + ")");
        }
        System.out.println();

        // ---------------------------------------------------------
        // 5. Ler as seleções do repositório para usar nos jogos
        // ---------------------------------------------------------
        Selecao portugal = repositorio.procurarSelecaoPorId(1);
        Selecao espanha  = repositorio.procurarSelecaoPorId(2);
        Selecao franca   = repositorio.procurarSelecaoPorId(3);

        // ---------------------------------------------------------
        // 6. Organizar Grupo A
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
        System.out.println("Centro de estágio de Portugal: "
                + portugal.getCentroDeEstagio().getNome() + "\n");

        // ---------------------------------------------------------
        // 7. Agendar um Jogo, atribuir árbitros e abrir venda
        // ---------------------------------------------------------
        Date dataJogo = criarData(2026, Calendar.JUNE, 20, 21, 0);
        Jogo jogo = new Jogo(1, dataJogo, portugal, espanha, estadioLuz);
        gestor.gerirJogos(jogo);
        estadioLuz.adicionarJogo(jogo);

        EquipaArbitragem arbitros = new EquipaArbitragem(1, "Equipa de Arbitragem Italiana", "Itália");
        gestor.gerirArbitragem(jogo, arbitros);
        gestor.abrirVendaBilhetes(jogo);

        System.out.println("--- Jogo agendado ---");
        System.out.println(portugal.getNome() + " vs " + espanha.getNome());
        System.out.println("Estádio : " + jogo.getEstadio().getNome());
        System.out.println("Árbitros: " + jogo.getEquipaArbitragem().getNome());
        System.out.println("Lugares disponíveis: " + jogo.getLugaresDisponiveis() + "\n");

        // ---------------------------------------------------------
        // 8. Registar Deslocação da Espanha para o jogo
        // ---------------------------------------------------------
        Deslocacao viagem = new Deslocacao(1, "Madrid", "Lisboa", dataJogo, espanha);
        gestor.registarDeslocacao(espanha, viagem);
        System.out.println("--- Deslocação registada ---");
        System.out.println(viagem.getOrigem() + " → " + viagem.getDestino()
                + " (Seleção: " + viagem.getSelecao().getNome() + ")\n");

        // ---------------------------------------------------------
        // 9. Utilizador Público compra bilhetes
        // ---------------------------------------------------------
        UtilizadorPublico cris = new UtilizadorPublico(
                10, "Cris", "cris@mail.pt", "12345678", "910000000");

        Bilhete bilhete = cris.comprarBilhete(jogo, 2, cris.getCartaoCidadao(), cris.getTelefone());

        System.out.println("--- Compra de bilhetes ---");
        if (bilhete != null) {
            System.out.println("Referência : " + bilhete.getBilheteDigital());
            System.out.println("Preço total: " + bilhete.getPrecoTotal() + "€");
            System.out.println("QR Code    : " + bilhete.getQrcode().getCodigo());
            System.out.println("Lugares disponíveis após compra: " + jogo.getLugaresDisponiveis());
        }
        System.out.println();

        // ---------------------------------------------------------
        // 10. Validação do QR Code à entrada do estádio
        // ---------------------------------------------------------
        System.out.println("--- Validação à entrada ---");
        System.out.println("1ª leitura (deve ser true) : " + bilhete.getQrcode().validar());
        System.out.println("2ª leitura (deve ser false): " + bilhete.getQrcode().validar() + "\n");

        // ---------------------------------------------------------
        // 11. Prova do Singleton: outra referência, mesmos dados
        // ---------------------------------------------------------
        GestorDados outraReferencia = GestorDados.getInstance();
        System.out.println("--- Verificação do Singleton ---");
        System.out.println("repositorio == outraReferencia ? " + (repositorio == outraReferencia));
        System.out.println("Total jogadores no repositório : " + outraReferencia.getJogadores().size());
        System.out.println("Total seleções no repositório  : " + outraReferencia.getSelecoes().size());
        System.out.println("Total utilizadores no repositório: " + outraReferencia.getUtilizadores().size());
    }

    private static Date criarData(int ano, int mes, int dia, int hora, int minuto) {
        Calendar c = Calendar.getInstance();
        c.set(ano, mes, dia, hora, minuto, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
