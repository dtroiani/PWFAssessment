/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.math.BigDecimal;

/**
 *
 * @author DanieleT
 */
class Bilancio {
    private final Integer anno;
    private final BigDecimal ricavi;
    private final BigDecimal costi;

    public Bilancio(Integer anno, BigDecimal ricavi, BigDecimal costi) {
        this.anno = anno;
        this.ricavi = ricavi;
        this.costi = costi;
    }

    public Integer getAnno() {
        return anno;
    }

    public BigDecimal getRicavi() {
        return ricavi;
    }

    public BigDecimal getCosti() {
        return costi;
    }

}
