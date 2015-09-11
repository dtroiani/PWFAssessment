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
public class FileRepositoryBustaPagaConsulente extends AbstractFileRepositoryBustaPaga {

    private final PagamentoConsulente bustaPaga;

    public FileRepositoryBustaPagaConsulente(PagamentoConsulente bustaPaga) {
        this.bustaPaga = bustaPaga;
    }

    @Override
    String preparaIntestazione() {
        StringBuilder intestazione = new StringBuilder();
        intestazione.append(bustaPaga.getRisorsa().getMatricola()).
                append(" | ").
                append(bustaPaga.getRisorsa().getCognome()).
                append(" | ").append(bustaPaga.getRisorsa().getNome());
        return intestazione.toString();
    }    
    
    @Override
    String preparaCorpo() {
        StringBuilder corpo = new StringBuilder();
        corpo.append("Giorni lavorati: ").append(bustaPaga.getNumGiorniLavorati()).
                append(" * ").
                append(PagamentoConsulente.COMPENSO_ORARIO_ORDINARIO).
                append(" = ").
                append(bustaPaga.getTotCompenso());
        return corpo.toString();
    }

    @Override
    // TODO: non mi piace, codice duplicato
    String impostaNomeFile() {
        StringBuilder nomeFile = new StringBuilder();
        nomeFile.append(bustaPaga.getAnno()).
                append(bustaPaga.getMese()).
                append("-").
                append(bustaPaga.getRisorsa().getCognome()).
                append("-").
                append(bustaPaga.getRisorsa().getNome()).
                append(".txt");
        return nomeFile.toString();
    }
 
}
