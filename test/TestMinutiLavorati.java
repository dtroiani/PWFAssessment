/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import it.dtroiani.bustapaga.StrisciataBadge;

/**
 *
 * @author dtroiani
 */
public class TestMinutiLavorati {
    
    public TestMinutiLavorati() {
    }
    
    @Test
    public void minutiLavoratiCaso1() {
        StrisciataBadge sb = new StrisciataBadge("1234", 2011, 1, 1, 8, 0, 9, 45);
        Integer totMin = sb.calcolaMinutiTempoLavorato();
        assertEquals((long)totMin, 105);
    }
    
    @Test
    public void minutiLavoratiCaso2() {
        StrisciataBadge sb = new StrisciataBadge("1234", 2011, 1, 1, 13, 30, 17, 15);
        Integer totMin = sb.calcolaMinutiTempoLavorato();
        assertEquals((long)totMin, 225);
    }       
}
