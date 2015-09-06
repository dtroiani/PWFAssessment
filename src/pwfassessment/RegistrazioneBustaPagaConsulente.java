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
public class RegistrazioneBustaPagaConsulente {

    Risorsa risorsa;
    Integer numGiorni;
    Integer compGiornaliero;
    Double totCompensi;
    
    public void registra() {
        // TODO: da implementare
        System.out.println("----------");
        System.out.println(risorsa.getMatricola() + " | " + risorsa.getCognome() + " | " + risorsa.getNome());
        System.out.println("Giorni lavorati: " + numGiorni + " * " + compGiornaliero + " = " + totCompensi);
        System.out.println("----------");
    }

}
