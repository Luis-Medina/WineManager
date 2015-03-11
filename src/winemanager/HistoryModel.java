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
public class HistoryModel extends AbstractTableModel{

    private ArrayList<Entry> datalist = new ArrayList<Entry>();
    private String[] columns = {
        WineMainFrame.strings.getString("lblWine"), 
        WineMainFrame.strings.getString("lblNewQuantity"), 
        WineMainFrame.strings.getString("lblOriginalQuantity")
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

    public HistoryModel() {
    }

    public HistoryModel(ArrayList l) {
        datalist = l;
    }

    public Object getValueAt(int row, int col) {
        Entry e = datalist.get(row);
        switch (col) {
            case 0:
                return e.getWineName();
            case 1:
                return e.getQuantity();
            case 2:
                return e.getOldQuantity();
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable() {
        return false;
    }

    public void addEntry(Entry e){
        datalist.add(e);
        fireTableDataChanged();
    }
}
