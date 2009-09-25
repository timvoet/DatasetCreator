/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.swing;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author dev
 */
public class MyNumericInputVerifier extends InputVerifier {

    @Override
    public boolean verify( JComponent input ) {
        JTextField field = (JTextField) input;
        String value = field.getText();
        try {
            Integer val = Integer.parseInt( value );
            return ( val.intValue() >= 0 );
        } catch ( NumberFormatException npe ) {
            return false;
        }
    }
}
