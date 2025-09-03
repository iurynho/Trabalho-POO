/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private String cidade;
    private List<Jogador> jogadores = new ArrayList<>();
    private int pontos = 0;
    private int golsMarcados = 0;
    private int golsSofridos = 0;
    private int saldoGols = 0;

    public Time(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void atualizarEstatisticas(int golsMarcados, int golsSofridos) {
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
        this.saldoGols = this.golsMarcados - this.golsSofridos;
    }

    public void atualizarPontos(int resultado) {
        // 3 pontos para vitória, 1 ponto para empate, 0 para derrota
        if (resultado > 0) {
            pontos += 3;
        } else if (resultado == 0) {
            pontos += 1;
        }
    }

    public int getPontos(){
        return pontos;
    }

    public String getNome(){
        return nome;
    }

    public String getCidade(){
        return cidade;
    }

    public int getSaldoGols(){
        return saldoGols;
    }
    
    public int getGolsMarcados(){
        return golsMarcados;
    }
    
    
    public List<Jogador> getJogadores() {
        return jogadores;
    }
    
    public void setGolsMarcados(){
        this.golsMarcados++;
    }
}
