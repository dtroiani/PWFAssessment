/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 *
 * @author DanieleT
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        // lettura parametri di configurazione
        InputStream propStream;
        if (args.length > 0) {
            propStream = new FileInputStream(new File(args[0]));
        } else {
            propStream = Main.class.getResourceAsStream("resources/config.properties"); 
        }
        Properties properties = new Properties();
        if (propStream != null) {
            properties.load(propStream);
        } else {
            throw new FileNotFoundException("File di configurazione config.properties non trovato");
        }
        String pathFileRisorse = properties.getProperty("percorso_risorse");
        String pathFileBadges = properties.getProperty("percorso_badge");
        String pathDirBilanci = properties.getProperty("cartella_bilanci");
        
        while (true) {
            // lettura e controllo parametri di input da utente
            System.out.print("Digitare anno e mese [nel formato yyyy<spazio>mm] oppure Q per uscire: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input = br.readLine();

            StringTokenizer stInput = new StringTokenizer(input, " ");
            if (input.equals("Q")) {
                break;
            }

            if (stInput.countTokens() != 2) {
                throw new IllegalArgumentException("Data " + input + " non e' valida o non ha il formato corretto");
            }

            Integer anno = Integer.parseInt(stInput.nextToken());
            Integer mese = Integer.parseInt(stInput.nextToken());

            if (!validaInput(anno, mese)) {
                throw new IllegalArgumentException("Data " + anno + "-" + mese + " non ammessa, e' una data futura");
            }

            // inizializza repository risorse
            IRepositoryRisorse fileRisorse = new FileRepositoryRisorse(pathFileRisorse, ",");
            // inizializza repository dati badge
            IRepositoryStrisciateBadge fileStrisciateBadge = new FileRepositoryStrisciateBadge(pathFileBadges, ",");
            // inizializza repository bilancio anno precedente
            IRepositoryBilancio fileBilancio = new XmlRepositoryBilancio(pathDirBilanci + File.separator + "bilancio" + (anno - 1) + ".xml");

            List<Risorsa> risorse = fileRisorse.trovaTutte();
            Iterator<Risorsa> itrRisorse = risorse.iterator();
            while (itrRisorse.hasNext()) {
                Risorsa risorsa = itrRisorse.next();
                System.out.println("Elaborazione busta paga matricola " + risorsa.getMatricola() + " in corso ...");
                IPagamento pagamento = PagamentoFactory.creaIstanza(risorsa.getRuolo(),
                        fileStrisciateBadge, fileBilancio);
                pagamento.inizializza(risorsa, anno, mese);
                pagamento.calcolaBustaPaga();
                pagamento.registra();
            }
        }

    }

    private static boolean validaInput(Integer anno, Integer mese) {
        Calendar oggi = Calendar.getInstance();
        int curAnno = oggi.get(Calendar.YEAR);
        int curMese = oggi.get(Calendar.MONTH) + 1;

        return ((anno < curAnno) || (anno == curAnno && mese <= curMese));
    }

}
