/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.util;

import java.io.Serializable;

/**
 *
 * @author tvoet
 */
public class Tuple<T extends Serializable, U extends Serializable> {
    private T first;
    private U second;

    public Tuple( T first, U second ){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public U getSecond(){
        return second;
    }

    public void setFirst( T first ){
        this.first = first;
    }

    public void setSecond( U second ) {
        this.second = second;
    }

    @Override
    public String toString(){
        return second.toString();
    }
}
