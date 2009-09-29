/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.util.xml;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author dev
 */
public class DOMFactoryUtil {

    public static Document createDocument( String rootNodeName ){
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement( rootNodeName );
            doc.appendChild( rootElement );
            return doc;
        } catch ( ParserConfigurationException ex ) {
            Logger.getLogger( DOMFactoryUtil.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return null;
    }
}
