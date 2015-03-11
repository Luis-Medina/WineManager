/*
 * CurrencyRenderer.java
 *
 * Created on June 12, 2007, 7:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package winemanager;

import java.text.NumberFormat;
import javax.swing.table.DefaultTableCellRenderer;

  public class CurrencyRenderer extends DefaultTableCellRenderer {

    public CurrencyRenderer() {
      super();
      setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    }

      @Override
    public void setValue(Object value) {
      if ((value != null) && (value instanceof Number)) {
        Number numberValue = (Number) value;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        value = formatter.format(numberValue.doubleValue());
      } 
      super.setValue(value);
    } 

  } 
