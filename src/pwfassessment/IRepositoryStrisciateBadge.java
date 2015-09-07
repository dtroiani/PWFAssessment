/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwfassessment;

import java.util.Iterator;

/**
 *
 * @author dtroiani
 */
public interface IRepositoryStrisciateBadge {

    public Iterator<StrisciataBadge> trovaPer(String matricola, Integer anno, Integer mese);
}
