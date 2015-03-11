/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winemanager;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Luis
 */
public class RatingVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        JTextField field = (JTextField) input;
        String s = field.getText();
        if(s.equals("")) {
            return true;
        }
        try {
            int number = Integer.parseInt(s);
            if(number >= 0 && number <= 100) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        boolean inputOK = verify(input);
        JTextField field = (JTextField) input;
        if (field.isEditable()) {
            if (inputOK) {
                input.setBackground(Color.WHITE);
                return true;
            } else {
                input.setBackground(Color.RED);
                Toolkit.getDefaultToolkit().beep();
                return false;
            }
        } else {
            return true;
        }
    }
}

