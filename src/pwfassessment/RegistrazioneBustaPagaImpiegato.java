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
public class RegistrazioneBustaPagaImpiegato {

    Risorsa risorsa;
    Integer numOreOrdinarie, numOreStraordinarie;
    Double compOrarioOrdinario, compOrarioStraordinario;
    Double totCompensi;
    
    public void registra() {
        // TODO: da implementare
        System.out.println("----------");
        System.out.println(risorsa.getMatricola() + " | " + risorsa.getCognome() + " | " + risorsa.getNome());
        System.out.println("Ore ordinarie lavorate: " + numOreOrdinarie + " * " + compOrarioOrdinario + " = " + compOrarioOrdinario);
        System.out.println("Ore straordinario lavorate: " + numOreStraordinarie + " * " + compOrarioStraordinario + " = " + compOrarioStraordinario);
        System.out.println("Totale: " + (totCompensi));
        System.out.println("----------");
    }

}
