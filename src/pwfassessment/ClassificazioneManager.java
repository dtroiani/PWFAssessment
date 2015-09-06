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
public class ClassificazioneManager implements IClassificazionePagamento {
    final static Integer PERC_REDDITO_PER_COMPENSO = 5;
    final static Integer NUM_MENSILITA = 12;
    
    @Override
    public void calcolaBustaPaga(Risorsa risorsa, Integer anno, Integer mese) {
        Double ricavi = calcolaRicavi();
        Double costi = calcolaCosti();
        Double compenso = (double) 0;
        
        if (ricavi > costi) {
            compenso = ((ricavi - costi) * 100 / PERC_REDDITO_PER_COMPENSO) / NUM_MENSILITA;
        }
        
        // TODO: stampa busta paga
        RegistrazioneBustaPagaManager rbp = new RegistrazioneBustaPagaManager();
        rbp.risorsa = risorsa;
        rbp.compenso = compenso;
        rbp.registra();
    }
    
    private Double calcolaRicavi() {
        //TODO: da implementare
        return (double) 0;
    }
    
    private Double calcolaCosti() {
        //TODO: da implementare
        return (double) 0;
    }
}
