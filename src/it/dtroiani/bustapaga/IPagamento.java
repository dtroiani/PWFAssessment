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
public interface IPagamento {

    public void inizializza(Risorsa risorsa, Integer anno, Integer mese);
    public void calcolaBustaPaga();
    public void registra();
}
