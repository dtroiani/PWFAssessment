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
public class PagamentoFactory {

    static public IPagamento creaIstanza(RuoloRisorsaEnum cr, IRepositoryStrisciateBadge repBadge, IRepositoryBilancio repBilancio) {
        if (cr.equals(RuoloRisorsaEnum.Impiegato)) {
            return new PagamentoImpiegato(repBadge);
        } else if (cr.equals(RuoloRisorsaEnum.Manager)) {
            return new PagamentoManager(repBilancio);
        } else if (cr.equals(RuoloRisorsaEnum.Consulente)) {
            return new PagamentoConsulente(repBadge);
        } else {
            throw new IllegalArgumentException(cr.toString());
        }

    }

    private PagamentoFactory() {
    }
}
