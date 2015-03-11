/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winemanager;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luis
 */
public class DetailStatsModel extends AbstractTableModel{
    
    private ArrayList<DetailStat> datalist;
    private String[] columns = {
        WineMainFrame.strings.getString("lblCountry"), 
        WineMainFrame.strings.getString("lblBottles"), 
        WineMainFrame.strings.getString("lblTotalValue")
    };

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    public DetailStatsModel() {
    }

    public DetailStatsModel(ArrayList l) {
        datalist = l;
    }

    public Object getValueAt(int row, int col) {
        DetailStat v = (DetailStat) datalist.get(row);
        switch (col) {
            case 0:
                return v.getCountry();
            case 1:
                return v.getBottleCount();
            case 2:
                return v.getTotalValue();
            default:
                return null;
        }
    }

    public DetailStat getWineAt(int row) {
        return (DetailStat) datalist.get(row);
    }

    @Override
    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return Double.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable() {
        return false;
    }
}
