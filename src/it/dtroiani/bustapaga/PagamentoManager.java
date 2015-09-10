/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author DanieleT
 */
public class PagamentoManager implements IPagamento {
    final static Double PERC_COMPENSO_SU_REDDITO = (double)5;
    final static int NUM_MENSILITA = 12;
    
    private Risorsa risorsa;
    IRepositoryBilancio repBilancio;
    private Integer anno, mese;
    private BigDecimal compenso = BigDecimal.ZERO;

    public PagamentoManager(IRepositoryBilancio repBilancio) {
        this.repBilancio = repBilancio;
    }

    public Risorsa getRisorsa() {
        return risorsa;
    }

    public Integer getAnno() {
        return anno;
    }

    public Integer getMese() {
        return mese;
    }

    public BigDecimal getCompenso() {
        return compenso;
    }    
    
    @Override
    public void inizializza(Risorsa risorsa, Integer anno, Integer mese) {
        this.risorsa = risorsa;
        this.anno = anno;
        this.mese = mese;
    }    

    @Override
    public void calcolaBustaPaga() {
        Bilancio bilancio = repBilancio.trova(anno);
        
        // compenso solo in caso di utile, risultato con arrotondamento matematico a 2 cifre decimali
        if (bilancio.getRicavi().compareTo(bilancio.getCosti()) > 0) {
            compenso = ((bilancio.getRicavi().subtract(bilancio.getCosti())).
                    multiply(new BigDecimal(PERC_COMPENSO_SU_REDDITO)).
                    divide(new BigDecimal(100)).
                    divide(new BigDecimal(NUM_MENSILITA), 2, RoundingMode.HALF_UP));
        }
    }

    @Override
    public void registra() {
        FileRepositoryBustaPagaManager rbp = new FileRepositoryBustaPagaManager(this);
        rbp.registra();
    }    
}
