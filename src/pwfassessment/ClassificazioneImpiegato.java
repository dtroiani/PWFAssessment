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
public class ClassificazioneImpiegato implements IClassificazionePagamento {
    final static Integer COMPENSO_ORARIO_ORDINARIO = 10;
    final static Integer COMPENSO_ORARIO_STRAORDINARIO = 15;
    
    @Override
    public void calcolaBustaPaga(Risorsa risorsa, Integer anno, Integer mese) {
        Integer numOreOrdinarie = calcolaNumOreOrdinarie();
        Integer numOreStraordinarie = calcolaNumOreStraordinarie();
        
        Double totCompOrdinario = numOreOrdinarie * COMPENSO_ORARIO_ORDINARIO.doubleValue();
        Double totCompStraordinario = numOreStraordinarie * COMPENSO_ORARIO_STRAORDINARIO.doubleValue();
        Double totCompensi = totCompOrdinario + totCompStraordinario;
        
        RegistrazioneBustaPagaImpiegato rb = new RegistrazioneBustaPagaImpiegato();
        rb.risorsa = risorsa;
        rb.numOreOrdinarie = numOreOrdinarie;
        rb.numOreStraordinarie = numOreStraordinarie;
        rb.compOrarioOrdinario = COMPENSO_ORARIO_ORDINARIO;
        rb.compOrarioStraordinario = COMPENSO_ORARIO_STRAORDINARIO;
        rb.totCompensoOrdinario = totCompOrdinario;
        rb.totCompensoStraordinario = totCompStraordinario;
        rb.totCompensi = totCompensi;
        rb.registra();
    }
    
    private Integer calcolaNumOreOrdinarie() {
        // TODO: da implementare
        return 0;
    }
    
    private Integer calcolaNumOreStraordinarie() {
        // TODO: da implementare
        return 0;
    }
}