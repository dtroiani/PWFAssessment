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
public class ClassificazionePagamentoFactory {

    static public IClassificazionePagamento creaIstanza(ClassificazioneRisorsaEnum cr) {
        if (cr.equals(ClassificazioneRisorsaEnum.Impiegato)) {
            return new ClassificazioneImpiegato();
        } else if (cr.equals(ClassificazioneRisorsaEnum.Manager)) {
            return new ClassificazioneManager();
        } else if (cr.equals(ClassificazioneRisorsaEnum.Consulente)) {
            return new ClassificazioneConsulente();
        } else {
            throw new IllegalArgumentException(cr.toString());
        }

    }
}
