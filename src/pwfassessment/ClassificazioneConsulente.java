/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwfassessment;

/**
 *
 * @author DanieleT
 */
public class ClassificazioneConsulente implements IClassificazionePagamento {

final static Integer COMPENSO_ORARIO_ORDINARIO = 150;    
    
    @Override
    public void calcolaBustaPaga(Risorsa risorsa, Integer anno, Integer mese) {
        Integer numGiorniLavorati = calcolaNumGiorniLavorati();
        Double totCompenso = numGiorniLavorati * COMPENSO_ORARIO_ORDINARIO.doubleValue();
        
        // TODO: chiama stampa busta paga
        RegistrazioneBustaPagaConsulente rbp = new RegistrazioneBustaPagaConsulente();
        rbp.risorsa = risorsa;
        rbp.numGiorni = numGiorniLavorati;
        rbp.compGiornaliero = COMPENSO_ORARIO_ORDINARIO;
        rbp.totCompensi = totCompenso;
        rbp.registra();
    }

    private Integer calcolaNumGiorniLavorati() {
        // TODO: da implementare
        return 0;
    }
    
}
