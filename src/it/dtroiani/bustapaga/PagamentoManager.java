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
public class PagamentoManager implements IPagamento {
    final static Integer PERC_COMPENSO_SU_REDDITO = 5;
    final static Integer NUM_MENSILITA = 12;
    
    private Risorsa risorsa;
    IRepositoryBilancio repBilancio;
    private Integer anno, mese;
    private Double compenso;

    public PagamentoManager(IRepositoryBilancio repBilancio) {
        this.repBilancio = repBilancio;
    }

    public Risorsa getRisorsa() {
        return risorsa;
    }

    public Integer getAnno() {
        return anno;
    }

    public Integer getMese() {
        return mese;
    }

    public Double getCompenso() {
        return compenso;
    }    
    
    @Override
    public void inizializza(Risorsa risorsa, Integer anno, Integer mese) {
        this.risorsa = risorsa;
        this.anno = anno;
        this.mese = mese;
    }    

    @Override
    public void calcolaBustaPaga() {
        Bilancio bilancio = repBilancio.trova(anno);
        
        if (bilancio.getRicavi() > bilancio.getCosti()) {
            compenso = ((bilancio.getRicavi() - bilancio.getCosti()) * PERC_COMPENSO_SU_REDDITO / 100) / NUM_MENSILITA;
        }
        // TODO: meglio arrotondamento matematico a 2 cifre decimali
        //new BigDecimal(d).setScale(2 , BigDecimal.ROUND_UP).doubleValue();
        compenso = (Math.rint(compenso * 100) / 100);
    }

    @Override
    public void registra() {
        FileRepositoryBustaPagaManager rbp = new FileRepositoryBustaPagaManager(this);
        rbp.registra();
    }    
}
