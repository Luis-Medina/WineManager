/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package winemanager;

/**
 *
 * @author Luis
 */
public class Pair {

    private String name;
    private int quantity;
    private int bottles;

    public Pair(String s, int c, int b){
        name = s;
        quantity = c;
        bottles = b;
    }

    public int getBottles() {
        return bottles;
    }

    public void setBottles(int bottles) {
        this.bottles = bottles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
