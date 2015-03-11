/*
 * TableData.java
 *
 * Created on August 23, 2006, 8:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package winemanager;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luiso
 */
public class BasicTableModel extends AbstractTableModel {

    private ArrayList<Vino> datalist;
    private String[] columns = {
        WineMainFrame.strings.getString("lblName"), 
        WineMainFrame.strings.getString("lblCountry"), 
        WineMainFrame.strings.getString("lblType"), 
        WineMainFrame.strings.getString("lblColor"), 
        WineMainFrame.strings.getString("lblRegion"), 
        WineMainFrame.strings.getString("lblPrice"), 
        WineMainFrame.strings.getString("lblYear"), 
        WineMainFrame.strings.getString("lblPoints"), 
        WineMainFrame.strings.getString("lblQuantity"),  
        WineMainFrame.strings.getString("lblTotalPrice")
    };

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }
    
    public String[] getColumns() {
        return columns;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    public BasicTableModel() {
    }

    public BasicTableModel(ArrayList l) {
        datalist = l;
    }

    public Object getValueAt(int row, int col) {
        Vino v = (Vino) datalist.get(row);
        switch (col) {
            case 0:
                return v.getName();
            case 1:
                return v.getCountry();
            case 2:
                return v.getType();
            case 3:
                return v.getColor();
            case 4:
                return v.getRegion();
            case 5:
                //if(v.getPrice() == 0){
                //    return "";
                //}
                return v.getPrice();
            case 6:
                //if (v.getYear() == 0) {
                //    return "";
                //}
                return v.getYear();
            case 7:
                //if (v.getPoints() == 0) {
                //    return "";
                //}
                return v.getPoints();
            case 8:
                return v.getQuantity();
            case 9:
                //if(v.getTotalAmount() == 0){
                //    return "";
                //}
                return v.getTotalAmount();
            default:
                return null;
        }
    }

    public Vino getWineAt(int row) {
        return (Vino) datalist.get(row);
    }

    @Override
    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return Double.class;
            case 6:
                return Integer.class;
            case 7:
                return Integer.class;
            case 8:
                return Integer.class;
            case 9:
                return Double.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable() {
        return false;
    }
}
