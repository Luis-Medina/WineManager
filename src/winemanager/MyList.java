/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winemanager;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Luis
 */
public class MyList extends ArrayList<Pair> {

    public void myAdd(String s, int bottles) {
        if (this.size() == 0) {
            add(new Pair(s, 1, bottles));
        } else {
            ListIterator iter = this.listIterator();
            boolean found = false;
            while (iter.hasNext()) {
                Pair pair = (Pair) iter.next();
                if (pair.getName().equals(s)) {
                    pair.setQuantity(pair.getQuantity() + 1);
                    pair.setBottles(pair.getBottles() + bottles);
                    found = true;
                    break;
                }
            }
            if (!found) {
                add(new Pair(s, 1, bottles));
            }
        }
    }
}
