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
 * @author dtroiani
 */
public class FileRepositoryStrisciateBadge implements IRepositoryStrisciateBadge {

    String pathFile;
    String separatore;

    public FileRepositoryStrisciateBadge(String pathFile, String separatore) {
        this.pathFile = pathFile;
        this.separatore = separatore;
    }

    @Override
    public List<StrisciataBadge> trovaPer(String matricola, Integer anno, Integer mese) {
        List<StrisciataBadge> listaStrisciateBadge = new ArrayList<>();
        Path fileBadge = Paths.get(pathFile);
        try (BufferedReader reader = Files.newBufferedReader(fileBadge, StandardCharsets.ISO_8859_1)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer stLine = new StringTokenizer(line, separatore);
                if (stLine.countTokens() == 3) {
                    String curMatricola = stLine.nextToken();
                    String timestampIngresso = stLine.nextToken();
                    String timestampUscita = stLine.nextToken();
                    if (matricola.equals(curMatricola) && anno.equals(estraiAnnoDa(timestampIngresso)) && mese.equals(estraiMeseDa(timestampIngresso))) {
                        listaStrisciateBadge.add(estraiDa(matricola, timestampIngresso, timestampUscita));
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileRepositoryStrisciateBadge.class.getName()).log(Level.SEVERE, null, ex);
            throw new RepositoryStrisciateBadgeAccessException();
        }

        return listaStrisciateBadge;
    }

    private StrisciataBadge estraiDa(String matricola, String tsIng, String tsUsc) {
        return new StrisciataBadge(matricola, estraiAnnoDa(tsIng), estraiMeseDa(tsIng), estraiGiornoDa(tsIng),
                estraiOraDa(tsIng), estraiMinDa(tsIng),
                estraiOraDa(tsUsc), estraiMinDa(tsUsc));
    }

    private Integer estraiAnnoDa(String timestamp) {
        return Integer.parseInt(timestamp.substring(0, 4));
    }

    private Integer estraiMeseDa(String timestamp) {
        return Integer.parseInt(timestamp.substring(4, 6));
    }

    private Integer estraiGiornoDa(String timestamp) {
        return Integer.parseInt(timestamp.substring(6, 8));
    }

    private Integer estraiOraDa(String timestamp) {
        return Integer.parseInt(timestamp.substring(8, 10));
    }

    private Integer estraiMinDa(String timestamp) {
        return Integer.parseInt(timestamp.substring(10, 12));
    }
}
