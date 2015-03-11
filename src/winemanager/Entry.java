/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winemanager;

import java.io.Serializable;

/**
 *
 * @author Luis
 */
public class Entry implements Serializable {

    private String wineName;
    private int newQuantity;
    private int oldQuantity;

    public Entry(String name, int cantidad, int original) {
        wineName = name;
        newQuantity = cantidad;
        oldQuantity = original;
    }

    public int getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(int old) {
        this.oldQuantity = old;
    }

    public int getQuantity() {
        return newQuantity;
    }

    public void setQuantity(int quantity) {
        this.newQuantity = quantity;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }
}
