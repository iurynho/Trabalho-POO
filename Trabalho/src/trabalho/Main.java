package trabalho;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Campeonato campeonato = new Campeonato(); // Carrega times do banco ao criar o Campeonato

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Time");
            System.out.println("2. Adicionar Jogador");
            System.out.println("3. Registrar Partida");
            System.out.println("4. Ver Classificação");
            System.out.println("5. Ver Desempenho dos Jogadores");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao;

            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpar buffer
                continue;
            }
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do time: ");
                    String nomeTime = scanner.nextLine();
                    System.out.print("Cidade do time: ");
                    String cidadeTime = scanner.nextLine();
                    Time time = new Time(nomeTime, cidadeTime);
                    campeonato.adicionarTime(time);
                    break;
                case 2:
                    System.out.print("Nome do jogador: ");
                    String nomeJogador = scanner.nextLine();
                    System.out.print("Número do jogador: ");
                    int numeroJogador;
                    try {
                        numeroJogador = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Número inválido.");
                        scanner.nextLine(); // Limpar buffer
                        continue;
                    }
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Posição do jogador: ");
                    String posicaoJogador = scanner.nextLine();
                    System.out.println("Selecione o time do jogador:");
                    for (int i = 0; i < campeonato.getTimes().size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, campeonato.getTimes().get(i).getNome());
                    }
                    int timeIndex;
                    try {
                        timeIndex = scanner.nextInt() - 1;
                    } catch (Exception e) {
                        System.out.println("Seleção inválida.");
                        scanner.nextLine(); // Limpar buffer
                        continue;
                    }
                    scanner.nextLine(); // Limpar buffer
                    if (timeIndex >= 0 && timeIndex < campeonato.getTimes().size()) {
                        Time timeSelecionado = campeonato.getTimes().get(timeIndex);
                        Jogador jogador = new Jogador(nomeJogador, numeroJogador, posicaoJogador, timeSelecionado);
                        timeSelecionado.adicionarJogador(jogador);
                    } else {
                        System.out.println("Time inválido!");
                    }
                    break;
                case 3:
                    System.out.println("Selecione o time da casa:");
                    for (int i = 0; i < campeonato.getTimes().size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, campeonato.getTimes().get(i).getNome());
                    }
                    int casaIndex;
                    try {
                        casaIndex = scanner.nextInt() - 1;
                    } catch (Exception e) {
                        System.out.println("Seleção inválida.");
                        scanner.nextLine(); // Limpar buffer
                        continue;
                    }
                    scanner.nextLine(); // Limpar buffer
                    System.out.println("Selecione o time visitante:");
                    for (int i = 0; i < campeonato.getTimes().size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, campeonato.getTimes().get(i).getNome());
                    }
                    int visitanteIndex;
                    try {
                        visitanteIndex = scanner.nextInt() - 1;
                    } catch (Exception e) {
                        System.out.println("Seleção inválida.");
                        scanner.nextLine(); // Limpar buffer
                        continue;
                    }
                    scanner.nextLine(); // Limpar buffer
                    if (casaIndex >= 0 && casaIndex < campeonato.getTimes().size() && visitanteIndex >= 0
                            && visitanteIndex < campeonato.getTimes().size() && casaIndex != visitanteIndex) {
                        Time timeCasa = campeonato.getTimes().get(casaIndex);
                        Time timeVisitante = campeonato.getTimes().get(visitanteIndex);
                        Partida partida = new Partida(timeCasa, timeVisitante);
                        System.out.print("Placar do time da casa: ");
                        int placarCasa;
                        try {
                            placarCasa = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("Placar inválido.");
                            scanner.nextLine(); // Limpar buffer
                            continue;
                        }
                        System.out.print("Placar do time visitante: ");
                        int placarVisitante;
                        try {
                            placarVisitante = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("Placar inválido.");
                            scanner.nextLine(); // Limpar buffer
                            continue;
                        }
                        scanner.nextLine(); // Limpar buffer
                        partida.registrarResultado(placarCasa, placarVisitante);
                        campeonato.adicionarPartida(partida);
                    } else {
                        System.out.println("Seleção inválida de times!");
                    }
                    break;
                case 4:
                    campeonato.gerarClassificacao();
                    break;
                case 5:
                    campeonato.visualizarDesempenhoJogadores();
                    break;
                case 6:
                    System.out.println("Encerrando programa...");
                    scanner.close();
                    // A remoção do Singleton implica que não há um ponto central para fechar a conexão.
                    // A responsabilidade de fechar a conexão deve estar na classe que a utiliza (Campeonato).
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
