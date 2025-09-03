/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

public class Jogador {
    private String nome;
    private int numero;
    private String posicao;
    private Time time;
    private int golsMarcados = 0;
    private int assistencias = 0;

    public Jogador(String nome, int numero, String posicao, Time time) {
        this.nome = nome;
        this.numero = numero;
        this.posicao = posicao;
        this.time = time;
    }
    public void marcarGol() {
        golsMarcados++;
        time.setGolsMarcados();
    }
    public void fazerAssistencia() {
        assistencias++;
    }

    public String getNome(){
        return nome;
    }

    public String getPosicao(){
        return posicao;
    }

    public int getGolsMarcados(){
        return golsMarcados;
    }

    public int getNumero(){
        return numero;
    }

    public int getAssistencias(){
        return assistencias;
    }
}
