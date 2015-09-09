/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author DanieleT
 */
public class PagamentoConsulente implements IPagamento {
    
    public final static Integer COMPENSO_ORARIO_ORDINARIO = 150;    
    public final static Integer GIORNO_LAVORATO_IN_MIN = 420;  // equivalente a 7 ore
    
    private Risorsa risorsa;
    private Integer anno, mese;
    private final IRepositoryStrisciateBadge repBadge; 
    
    private Integer numGiorniLavorati;
    private Double totCompenso;
    
    public PagamentoConsulente(IRepositoryStrisciateBadge repBadge) {
        this.repBadge = repBadge;
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

    public Integer getNumGiorniLavorati() {
        return numGiorniLavorati;
    }

    public Double getTotCompenso() {
        return totCompenso;
    }

    // TODO: non mi piace tanto! fatto per non spostare i parametri nel costruttore e a ritroso nella factory
    public void inizializza(Risorsa risorsa, Integer anno, Integer mese) {
        this.risorsa = risorsa;
        this.anno = anno;
        this.mese = mese;
    }    
    
    @Override
    public void calcolaBustaPaga() {
        numGiorniLavorati = calcolaNumGiorniLavorati(risorsa.getMatricola(), anno, mese);
        totCompenso = numGiorniLavorati * COMPENSO_ORARIO_ORDINARIO.doubleValue();
    }

    @Override    
    public void registra() {
        FileRepositoryBustaPagaConsulente rbp = new FileRepositoryBustaPagaConsulente(this);
        rbp.registra();
    }
    
    private Integer calcolaNumGiorniLavorati(String matricola, Integer anno, Integer mese) {
        Integer numGiorni = 0;
        List<StrisciataBadge> strisciateBadge = repBadge.trovaPer(matricola, anno, mese);
        
        Integer curMinLavoro = 0;
        Integer curGiorno = 0;
        Iterator<StrisciataBadge> itrStrisciateBadge = strisciateBadge.iterator();
        while (itrStrisciateBadge.hasNext()) {
            StrisciataBadge sb = itrStrisciateBadge.next();
            if (curGiorno == 0) {
                curGiorno = sb.getGiorno();
            }
            if (curGiorno.equals(sb.getGiorno())) {
                curMinLavoro += sb.calcolaMinutiTempoLavorato();
            } else {
                if (curMinLavoro >= GIORNO_LAVORATO_IN_MIN) {
                   ++numGiorni; 
                }
                curMinLavoro = sb.calcolaMinutiTempoLavorato();
                curGiorno = sb.getGiorno();
            }
        }
        if (curMinLavoro >= GIORNO_LAVORATO_IN_MIN) {
           ++numGiorni; 
        }
        
        return numGiorni;
    }
    
}
