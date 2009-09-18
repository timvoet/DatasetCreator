/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.swing;

import com.voet.datasetcreator.util.Tuple;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author tvoet
 */
public class MyComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<Tuple<String,String>> contents;
    private Tuple<String,String> selected = null;
    public MyComboBoxModel(){
        contents = new ArrayList<Tuple<String,String>>();
    }

    @Override
    public int getSize() {
        return contents.size();
    }

    @Override
    public Object getElementAt( int index ) {
        return contents.get( index );
    }

    @Override
    public void setSelectedItem( Object anItem ) {
        this.selected = (Tuple<String, String>) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return this.selected;
    }

    public void add( Tuple<String,String> element ){
        contents.add( element );
    }
}
