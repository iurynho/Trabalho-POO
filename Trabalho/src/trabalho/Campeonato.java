package trabalho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Campeonato {
    private List<Time> times = new ArrayList<>();
    private List<Partida> partidas = new ArrayList<>();
    private Conexao conexao; // Adiciona uma instância de Conexao

    public Campeonato() {
        conexao = new Conexao(); // Inicializa a conexão
        carregarTimesDoBanco();
    }

    public void adicionarTime(Time time) {
        times.add(time);
        salvarTimeNoBanco(time);
    }

    public void adicionarPartida(Partida partida) {
        partidas.add(partida);
    }

    public void gerarClassificacao() {
        times.sort(Comparator.comparingInt((Time time) -> time.getPontos())
                .thenComparingInt(t -> t.getSaldoGols())
                .thenComparingInt(t -> t.getGolsMarcados())
                .reversed());
        System.out.println("Classificação do Campeonato:");
        for (int i = 0; i < times.size(); i++) {
            Time time = times.get(i);
            System.out.printf("%d. %s - Pontos: %d, Saldo de Gols: %d\n", i + 1, time.getNome(), time.getPontos(),
                    time.getSaldoGols());
        }
    }

    public void visualizarDesempenhoJogadores() {
        System.out.println("Desempenho dos Jogadores:");
        for (Time time : times) {
            for (Jogador jogador : time.getJogadores()) {
                System.out.printf("%s (Time: %s) - Gols: %d, Assistências: %d, Cartões: %d\n",
                        jogador.getNome(), time.getNome(), jogador.getGolsMarcados(), jogador.getAssistencias());
            }
        }
    }

    public List<Time> getTimes() {
        return times;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    private void salvarTimeNoBanco(Time time) {
        String sql = "INSERT INTO times (nome, cidade) VALUES (?, ?)";
        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, time.getNome());
            stmt.setString(2, time.getCidade());
            stmt.executeUpdate();
            System.out.println("Time " + time.getNome() + " salvo no banco de dados.");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar time no banco: " + e.getMessage());
        }
    }

    private void carregarTimesDoBanco() {
        String sql = "SELECT nome, cidade FROM times";
        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Usando PreparedStatement
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cidade = rs.getString("cidade");
                Time time = new Time(nome, cidade);
                times.add(time);
            }
            System.out.println("Times carregados do banco de dados.");
        } catch (SQLException e) {
            System.err.println("Erro ao carregar times do banco: " + e.getMessage());
        }
    }
}
