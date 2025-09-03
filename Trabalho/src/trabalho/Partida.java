/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

public class Partida {
    private Time timeCasa;
    private Time timeVisitante;
    private int placarTimeCasa;
    private int placarTimeVisitante;

    public Partida(Time timeCasa, Time timeVisitante) {
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
    }

    public void registrarResultado(int placarCasa, int placarVisitante) {
        this.placarTimeCasa = placarCasa;
        this.placarTimeVisitante = placarVisitante;
        timeCasa.atualizarEstatisticas(placarCasa, placarVisitante);
        timeVisitante.atualizarEstatisticas(placarVisitante, placarCasa);
        if (placarCasa > placarVisitante) {
            timeCasa.atualizarPontos(1);
            timeVisitante.atualizarPontos(-1);
        } else if (placarCasa < placarVisitante) {
            timeCasa.atualizarPontos(-1);
            timeVisitante.atualizarPontos(1);
        } else {
            timeCasa.atualizarPontos(0);
            timeVisitante.atualizarPontos(0);
        }
    }

    public int getPlacarTimeCasa(){
        return placarTimeCasa;
    }

    public int getPlacarTimeVisitante(){
        return placarTimeVisitante;
    }
   
}