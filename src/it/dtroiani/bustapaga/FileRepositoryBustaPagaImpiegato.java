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
public class FileRepositoryBustaPagaImpiegato extends AbstractFileRepositoryBustaPaga {

    private final PagamentoImpiegato bustaPaga;

    public FileRepositoryBustaPagaImpiegato(PagamentoImpiegato bustaPaga) {
        this.bustaPaga = bustaPaga;
    }

    @Override
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
        corpo.append("Ore ordinarie lavorate: ").
                append(bustaPaga.getNumOreOrdinarie()).
                append(" * ").
                append(PagamentoImpiegato.COMPENSO_ORARIO_ORDINARIO).
                append(" = ").
                append(bustaPaga.getTotCompensoOrdinario());
        corpo.append("\n");
        corpo.append("Ore straordinario lavorate: ").
                append(bustaPaga.getNumOreStraordinarie()).
                append(" * ").
                append(PagamentoImpiegato.COMPENSO_ORARIO_STRAORDINARIO).
                append(" = ").
                append(bustaPaga.getTotCompensoStraordinario());
        corpo.append("\n");
        corpo.append("Totale: ").
                append(bustaPaga.getTotCompensi());
        return corpo.toString();
    }

}
