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
class Risorsa {
    String matricola;
    String nome;
    String cognome;
    ClassificazioneRisorsaEnum classificazione;

    public Risorsa(String matricola, String nome, String cognome, ClassificazioneRisorsaEnum classificazione) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.classificazione = classificazione;
    }

    public String getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
    
    public ClassificazioneRisorsaEnum getClassificazione() {
        return this.classificazione;
    }
    
}
