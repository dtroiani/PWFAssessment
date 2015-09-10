/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author DanieleT
 */
public class PagamentoImpiegato implements IPagamento {
    final static BigDecimal COMPENSO_ORARIO_ORDINARIO = new BigDecimal(10);
    final static BigDecimal COMPENSO_ORARIO_STRAORDINARIO = new BigDecimal(15);
    final static int SOGLIA_MIN_ORARIO_ORDINARIO = 480; /* equivalente a 8 hh */
    
    private Risorsa risorsa;
    private Integer anno, mese;
    IRepositoryStrisciateBadge repBadge;
    
    private int numOreOrdinarie = 0;
    private int numOreStraordinarie = 0;
    private BigDecimal totCompensoOrdinario;
    private BigDecimal totCompensoStraordinario;
    private BigDecimal totCompensi;
    
    public PagamentoImpiegato(IRepositoryStrisciateBadge repBadge) {
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

    public int getNumOreOrdinarie() {
        return numOreOrdinarie;
    }

    public int getNumOreStraordinarie() {
        return numOreStraordinarie;
    }

    public BigDecimal getTotCompensoOrdinario() {
        return totCompensoOrdinario;
    }

    public BigDecimal getTotCompensoStraordinario() {
        return totCompensoStraordinario;
    }

    public BigDecimal getTotCompensi() {
        return totCompensi;
    }

    @Override
    public void inizializza(Risorsa risorsa, Integer anno, Integer mese) {
        this.risorsa = risorsa;
        this.anno = anno;
        this.mese = mese;
    }    
    
    @Override
    public void calcolaBustaPaga() {
        calcolaNumOreLavorate(risorsa.getMatricola(), anno, mese);
        totCompensoOrdinario = COMPENSO_ORARIO_ORDINARIO.multiply(new BigDecimal(numOreOrdinarie)).setScale(2);
        totCompensoStraordinario = COMPENSO_ORARIO_STRAORDINARIO.multiply(new BigDecimal(numOreStraordinarie)).setScale(2);
        totCompensi = totCompensoOrdinario.add(totCompensoStraordinario);
    }
    
    @Override
    public void registra() {
        FileRepositoryBustaPagaImpiegato rb = new FileRepositoryBustaPagaImpiegato(this);
        rb.registra();
    }
    
    private void calcolaNumOreLavorate(String matricola, Integer anno, Integer mese) {
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
                // stesso giorno
                curMinLavoro += sb.calcolaMinutiTempoLavorato();
            } else {
                // giorno cambiato
                numOreOrdinarie += convertiInNumOreOrdinarie(curMinLavoro);
                numOreStraordinarie += convertiInNumOreStraordinarie(curMinLavoro);

                curMinLavoro = sb.calcolaMinutiTempoLavorato();
                curGiorno = sb.getGiorno();
            }
        }
        numOreOrdinarie += convertiInNumOreOrdinarie(curMinLavoro);
        numOreStraordinarie += convertiInNumOreStraordinarie(curMinLavoro);
    }
    
    private int convertiInNumOreOrdinarie(Integer minLavorati) {
        if (minLavorati >= SOGLIA_MIN_ORARIO_ORDINARIO) {
            return SOGLIA_MIN_ORARIO_ORDINARIO / 60;
        } else {
            return minLavorati / 60;
        }
    }
    
    private int convertiInNumOreStraordinarie(Integer minLavorati) {
        Integer minStraordinari = minLavorati - SOGLIA_MIN_ORARIO_ORDINARIO;
        if (minStraordinari > 0) {
            Integer ore = minStraordinari / 60;
            if ((minStraordinari % 60) > 0) {
                // frazione di 1 ora conta come 1 ora
                ++ore; 
            }
            return ore;
        }
        return 0;
    }
    
}
