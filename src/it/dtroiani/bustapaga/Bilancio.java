/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

/**
 *
 * @author DanieleT
 */
class Bilancio {
    private final Integer anno;
    private final Double ricavi;
    private final Double costi;

    public Bilancio(Integer anno, Double ricavi, Double costi) {
        this.anno = anno;
        this.ricavi = ricavi;
        this.costi = costi;
    }

    public Integer getAnno() {
        return anno;
    }

    public Double getRicavi() {
        return ricavi;
    }

    public Double getCosti() {
        return costi;
    }

}
