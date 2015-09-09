/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.dtroiani.bustapaga;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dtroiani
 */
public class XmlRepositoryBilancio implements IRepositoryBilancio {

    String pathFile;

    public XmlRepositoryBilancio(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public Bilancio trova(Integer anno) {
        Bilancio bilancio = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom;
            dom = db.parse(pathFile);

            Element doc = dom.getDocumentElement();

            Double dRicavi = (double)0;
            String sRicavi = getValoreTag(null, doc, "ricavi");
            if (sRicavi != null) {
                if (!sRicavi.isEmpty()) {
                    dRicavi = Double.parseDouble(sRicavi);
                }
            }
            Double dCosti = (double)0;
            String sCosti = getValoreTag(null, doc, "costi");
            if (sCosti != null) {
                if (!sCosti.isEmpty()) {
                    dCosti = Double.parseDouble(sCosti);
                }
            }
            bilancio = new Bilancio(anno, dRicavi, dCosti);
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlRepositoryBilancio.class.getName()).log(Level.SEVERE, null, ex);
            throw new RepositoryBilancioAccessException();
        } catch (SAXException ex) {
            Logger.getLogger(XmlRepositoryBilancio.class.getName()).log(Level.SEVERE, null, ex);
            throw new RepositoryBilancioAccessException();
        } catch (IOException ex) {
            Logger.getLogger(XmlRepositoryBilancio.class.getName()).log(Level.SEVERE, null, ex);
            throw new RepositoryBilancioAccessException();
        }
        
        return bilancio;
    }

    private String getValoreTag(String def, Element doc, String tag) {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
}
