/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winemanager;

import java.util.Comparator;

/**
 *
 * @author Luis
 */
class MyComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        try {
            Number n1 = (Number) o1;
            Number n2 = (Number) o2;
            double value1 = n1.doubleValue();
            double value2 = n2.doubleValue();
            if (value1 == value2) {
                return 0;
            } else if (value1 < value2) {
                return -1;
            }
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }
}
