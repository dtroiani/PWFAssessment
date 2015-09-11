/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

/**
 *
 * @author dtroiani
 */
public class StrisciataBadge {
    public String matricola;
    public Integer anno;
    public Integer mese;
    public Integer giorno;
    public Integer oraIngresso;
    public Integer minIngresso;
    public Integer oraUscita;
    public Integer minUscita;

    public StrisciataBadge(String matricola, Integer anno, Integer mese, Integer giorno, Integer oraIngresso, Integer minIngresso, Integer oraUscita, Integer minUscita) {
        this.matricola = matricola;
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
        this.oraIngresso = oraIngresso;
        this.minIngresso = minIngresso;
        this.oraUscita = oraUscita;
        this.minUscita = minUscita;
    }

    public String getMatricola() {
        return matricola;
    }

    public Integer getAnno() {
        return anno;
    }

    public Integer getMese() {
        return mese;
    }

    public Integer getGiorno() {
        return giorno;
    }

    public Integer getOraIngresso() {
        return oraIngresso;
    }

    public Integer getMinIngresso() {
        return minIngresso;
    }

    public Integer getOraUscita() {
        return oraUscita;
    }

    public Integer getMinUscita() {
        return minUscita;
    }
    
    public Integer calcolaMinutiTempoLavorato() {

        return (oraUscita * 60 + minUscita) - (oraIngresso * 60 + minIngresso);
    }
}
