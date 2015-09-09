/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

/**
 *
 * @author DanieleT
 */
class Risorsa {
    String matricola;
    String nome;
    String cognome;
    RuoloRisorsaEnum ruolo;

    public Risorsa(String matricola, String nome, String cognome, RuoloRisorsaEnum ruolo) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
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
    
    public RuoloRisorsaEnum getRuolo() {
        return this.ruolo;
    }
    
}
