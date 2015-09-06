/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwfassessment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author DanieleT
 */
public class PWFAssessment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        while (true) {
            // lettura e controllo parametri di input da utente
            System.out.print("Digitare anno e mese [nel formato yyyy<spazio>mm] oppure Q per uscire");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input = br.readLine();

            StringTokenizer stInput = new StringTokenizer(input, " ");
            if (input.equals("Q")) {
                break;
            }

            String sAnno = stInput.nextToken();
            String sMese = stInput.nextToken();

            Integer anno = null, mese = null;

            // inizializza dati risorse
            IRepositoryRisorse fileRisorse = new FileRepositoryRisorse("C:\\Users\\DanieleT\\Dropbox\\assignment-daniele\\risorse.txt", ",");
            Iterator<Risorsa> itrRisorse = fileRisorse.trovaTutte();
            // TODO: inizializza dati bilancio
            Bilancio bilancio = null;

            while (itrRisorse.hasNext()) {
                Risorsa risorsa = itrRisorse.next();
                System.out.println("Elaborazione busta paga matricola " + risorsa.getMatricola() + " in corso ...");
                IClassificazionePagamento classePagamento = ClassificazionePagamentoFactory.creaIstanza(risorsa.getClassificazione());
                classePagamento.calcolaBustaPaga(risorsa, anno, mese);
            }
        }

    }

}
