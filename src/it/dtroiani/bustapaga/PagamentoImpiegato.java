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
public class PagamentoImpiegato implements IPagamento {
    final static Integer COMPENSO_ORARIO_ORDINARIO = 10;
    final static Integer COMPENSO_ORARIO_STRAORDINARIO = 15;
    final static Integer SOGLIA_MIN_ORARIO_ORDINARIO = 480; /* equivalente a 8 hh */
    
    private Risorsa risorsa;
    private Integer anno, mese;
    IRepositoryStrisciateBadge repBadge;
    
    private Integer numOreOrdinarie = 0;
    private Integer numOreStraordinarie = 0;
    private Double totCompensoOrdinario;
    private Double totCompensoStraordinario;
    private Double totCompensi;
    
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

    public Integer getNumOreOrdinarie() {
        return numOreOrdinarie;
    }

    public Integer getNumOreStraordinarie() {
        return numOreStraordinarie;
    }

    public Double getTotCompensoOrdinario() {
        return totCompensoOrdinario;
    }

    public Double getTotCompensoStraordinario() {
        return totCompensoStraordinario;
    }

    public Double getTotCompensi() {
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
        
        totCompensoOrdinario = numOreOrdinarie * COMPENSO_ORARIO_ORDINARIO.doubleValue();
        totCompensoStraordinario = numOreStraordinarie * COMPENSO_ORARIO_STRAORDINARIO.doubleValue();
        totCompensi = totCompensoOrdinario + totCompensoStraordinario;
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
    
    private Integer convertiInNumOreOrdinarie(Integer minLavorati) {
        if (minLavorati >= SOGLIA_MIN_ORARIO_ORDINARIO) {
            return SOGLIA_MIN_ORARIO_ORDINARIO / 60;
        } else {
            return minLavorati / 60;
        }
    }
    
    private Integer convertiInNumOreStraordinarie(Integer minLavorati) {
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
