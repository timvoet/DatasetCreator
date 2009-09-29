/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.swing;

import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author tvoet
 */
public class MyCheckBoxRenderer extends JCheckBox
        implements TableCellRenderer {

    public MyCheckBoxRenderer() {

        setHorizontalAlignment( JLabel.CENTER );
    }

    @Override
    public Component getTableCellRendererComponent( JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column ) {
        setHorizontalAlignment( JLabel.CENTER );
        if ( isSelected ) {
            //setSelected(true);
            setForeground( table.getSelectionForeground() );
            //super.setBackground(table.getSelectionBackground());
            setBackground( table.getSelectionBackground() );
        } else {
            //setSelected(false);
            setForeground( table.getForeground() );
            setBackground( table.getBackground() );
        }
        //setSelected( (value != null && ( (Boolean) value).booleanValue()));
        setSelected( ( (Boolean) value ).booleanValue() );
        return this;
    }

    @Override
    protected void processMouseEvent( MouseEvent e ) {
        setHorizontalAlignment( JLabel.CENTER );
        super.processMouseEvent( e );
    }


}
