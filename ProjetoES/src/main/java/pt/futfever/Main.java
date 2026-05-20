package pt.futfever;

import pt.futfever.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor(
                1,
                "Gestor FutFever",
                "gestor@futfever.pt",
                "1234"
        );

        Selecao portugal = gestor.criarSelecao(
                1,
                "Portugal",
                "Portugal"
        );

        Treinador treinador = new Treinador(
                1,
                "Roberto Martínez",
                "Espanha"
        );

        Jogador jogador = new Jogador(
                1,
                "Cristiano Ronaldo",
                39,
                "Avançado",
                7
        );

        Arbitro arbitro = gestor.criarArbitro(
                1,
                "Daniele Orsato",
                "Itália"
        );

        Estadio estadio = gestor.criarEstadio(
                1,
                "Estádio da Luz",
                "Lisboa",
                65000
        );

        CentroDeEstagio centro = gestor.criarCentroDeEstagio(
                1,
                "Cidade do Futebol",
                "Oeiras"
        );

        portugal.definirTreinador(treinador);
        portugal.adicionarJogador(jogador);
        portugal.definirCentroDeEstagio(centro);

        Jogos jogo = new Jogos(
                1,
                portugal,
                arbitro,
                estadio,
                LocalDate.of(2026, 6, 15),
                LocalTime.of(20, 0)
        );

        System.out.println("=== FUTFEVER ===");
        System.out.println("Gestor: " + gestor.getNome());
        System.out.println("Seleção: " + portugal);
        System.out.println("Treinador: " + portugal.getTreinador());
        System.out.println("Jogador: " + jogador);
        System.out.println("Centro de Estágio: " + centro);
        System.out.println("Jogo: " + jogo);
    }
}
