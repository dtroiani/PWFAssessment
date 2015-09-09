/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DanieleT
 */
public class FileRepositoryRisorse implements IRepositoryRisorse {

    String pathFile;
    String separatore;

    public FileRepositoryRisorse(String pathFile, String separatore) {
        this.pathFile = pathFile;
        this.separatore = separatore;
    }

    @Override
    public List<Risorsa> trovaTutte() {
        List<Risorsa> listaRisorse = new ArrayList<>();
        Path fileRisorse = Paths.get(pathFile);
        try (BufferedReader reader = Files.newBufferedReader(fileRisorse, StandardCharsets.ISO_8859_1)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer stLine = new StringTokenizer(line, separatore);
                if (stLine.countTokens() == 4) {
                    String matricola = stLine.nextToken();
                    String nome = stLine.nextToken();
                    String cognome = stLine.nextToken();
                    String classificazione = stLine.nextToken();
                    Risorsa risorsa = new Risorsa(matricola, nome, cognome, RuoloRisorsaEnum.valueOf(classificazione));
                    listaRisorse.add(risorsa);
                }
            }
            // TODO: chiudere file?
        } catch (IOException ex) {
            Logger.getLogger(FileRepositoryRisorse.class.getName()).log(Level.SEVERE, null, ex);
            throw new RepositoryRisorseAccessException();
        }
        
        return listaRisorse;
    }

}
