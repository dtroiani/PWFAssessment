/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DanieleT
 */
public class FileRepositoryBustaPagaImpiegato implements IRepositoryBustaPaga {

    private final PagamentoImpiegato bustaPaga;    

    public FileRepositoryBustaPagaImpiegato(PagamentoImpiegato bustaPaga) {
        this.bustaPaga = bustaPaga;
    }

    // TODO: se getto all'esterno l'exception poi mi cambia l'interfaccia!
    @Override
    public void registra() {        
        FileWriter fw = null;
        try {
            // pattern nome file yyyymm-cognome-nome.txt (esempio: 201212-rossi-mario.txt)
            StringBuilder nomeFile = new StringBuilder();
            nomeFile.append(bustaPaga.getAnno()).
                    append(bustaPaga.getMese()).
                    append("-").
                    append(bustaPaga.getRisorsa().getCognome()).
                    append("-").
                    append(bustaPaga.getRisorsa().getNome()).
                    append(".txt");
            
            StringBuilder contenuto = new StringBuilder();
            // intestazione
            contenuto.append(bustaPaga.getRisorsa().getMatricola()).
                    append(" | ").
                    append(bustaPaga.getRisorsa().getCognome()).
                    append(" | ").append(bustaPaga.getRisorsa().getNome());
            contenuto.append("\n");
            // corpo
            contenuto.append("Ore ordinarie lavorate: ").
                    append(bustaPaga.getNumOreOrdinarie()).
                    append(" * ").
                    append(PagamentoConsulente.COMPENSO_ORARIO_ORDINARIO).
                    append(" = ").
                    append(bustaPaga.getTotCompensoOrdinario());
            contenuto.append("\n");
            contenuto.append("Ore straordinario lavorate: ").
                    append(bustaPaga.getNumOreStraordinarie()).
                    append(" * ").
                    append(PagamentoImpiegato.COMPENSO_ORARIO_STRAORDINARIO).
                    append(" = ").
                    append(bustaPaga.getTotCompensoStraordinario());
            contenuto.append("\n");
            contenuto.append("Totale: ").
                    append(bustaPaga.getTotCompensi());
            // scrittura su file txt
            File file = new File(nomeFile.toString());
            if (!file.exists()) {
                file.createNewFile();
            }   fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenuto.toString());
            bw.close();
            System.out.println("----------");
            System.out.println(contenuto.toString());
            System.out.println("----------");
        } catch (IOException ex) {
            Logger.getLogger(FileRepositoryBustaPagaImpiegato.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileRepositoryBustaPagaImpiegato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
