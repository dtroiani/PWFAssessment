/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwfassessment;

import java.util.Iterator;

/**
 *
 * @author DanieleT
 */
public class ClassificazioneConsulente implements IClassificazionePagamento {
    
    final static Integer COMPENSO_ORARIO_ORDINARIO = 150;    
    final static Integer GIORNO_LAVORATO_IN_MIN = 420;  // equivalente a 7 ore
    
    String pathFileStrisciateBadge;
    String sepFileStrisciateBadge;

    public ClassificazioneConsulente(String pathFileStrisciateBadge, String sepFileStrisciateBadge) {
        this.pathFileStrisciateBadge = pathFileStrisciateBadge;
        this.sepFileStrisciateBadge = sepFileStrisciateBadge;
    }
    
    @Override
    public void calcolaBustaPaga(Risorsa risorsa, Integer anno, Integer mese) {
        Integer numGiorniLavorati = calcolaNumGiorniLavorati(risorsa.getMatricola(), anno, mese);
        Double totCompenso = numGiorniLavorati * COMPENSO_ORARIO_ORDINARIO.doubleValue();
        
        RegistrazioneBustaPagaConsulente rbp = new RegistrazioneBustaPagaConsulente();
        rbp.risorsa = risorsa;
        rbp.numGiorni = numGiorniLavorati;
        rbp.compGiornaliero = COMPENSO_ORARIO_ORDINARIO;
        rbp.totCompensi = totCompenso;
        rbp.registra();
    }
    
    private Integer calcolaNumGiorniLavorati(String matricola, Integer anno, Integer mese) {
        Integer numGiorni = 0;
        IRepositoryStrisciateBadge fileStrisciateBadge = new FileRepositoryStrisciateBadge(pathFileStrisciateBadge, sepFileStrisciateBadge);
        Iterator<StrisciataBadge> strisciateBadge = fileStrisciateBadge.trovaPer(matricola, anno, mese);
        
        Integer curMinLavoro = 0;
        Integer curGiorno = 0;
        while (strisciateBadge.hasNext()) {
            StrisciataBadge sb = strisciateBadge.next();
            if (curGiorno == 0) curGiorno = sb.getGiorno();
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
