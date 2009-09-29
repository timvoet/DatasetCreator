/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.util.xml;

import com.sun.org.apache.xml.internal.serialize.DOMSerializer;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.Serializer;
import com.sun.org.apache.xml.internal.serialize.SerializerFactory;
import com.sun.org.apache.xml.internal.serializer.Method;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class XmlSerializer {

    public static DOMSerializer getXmlSerializer(){
        return XmlSerializer.getXmlSerializer( System.out );
    }
    public static DOMSerializer getXmlSerializer(OutputStream outputStream ) {
        try {
            SerializerFactory serializerFactory = SerializerFactory.getSerializerFactory( Method.XML );
            Serializer serializer = serializerFactory.makeSerializer( outputStream, new OutputFormat( Method.XML, "UTF-8", true ) );
            DOMSerializer domSerializer = serializer.asDOMSerializer();
            return domSerializer;
        } catch ( UnsupportedEncodingException ex ) {
            Logger.getLogger( XmlSerializer.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( IOException ex ) {
            Logger.getLogger( XmlSerializer.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return null;
    }
}
