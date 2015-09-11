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

/**
 *
 * @author dtroiani
 */
public abstract class AbstractFileRepositoryBustaPaga implements IRepositoryBustaPaga {

    public AbstractFileRepositoryBustaPaga() {
    }

    @Override
    public void salva() {
        FileWriter fw = null;
        try {
            String contenuto = preparaContenuto();
            fw = scriviFile(impostaNomeFile(), fw, contenuto);
            //scriviSuConsole(contenuto);
        } catch (IOException ex) {
            throw new FileRepositoryBustaPagaException();
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                throw new FileRepositoryBustaPagaException();
            }
        }
    }

    protected String preparaContenuto() {
        StringBuilder contenuto = new StringBuilder();
        contenuto.append(preparaIntestazione()).append("\n").append(preparaCorpo());
        return contenuto.toString();
    }

    FileWriter scriviFile(String nomeFile, FileWriter fw, String contenuto) throws IOException {
        // scrittura su file txt
        File file = new File(nomeFile);
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(contenuto);
        bw.close();
        return fw;
    }

    protected void scriviSuConsole(String contenuto) {
        System.out.println("----------");
        System.out.println(contenuto);
        System.out.println("----------");
    }    
    
    abstract String impostaNomeFile();

    abstract String preparaIntestazione();

    abstract String preparaCorpo();
    
}
