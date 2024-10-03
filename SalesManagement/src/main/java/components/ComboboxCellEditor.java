package components;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class ComboboxCellEditor extends DefaultCellEditor {
	private final Map<Integer, JComboBox<String>> comboBoxes;
	public ComboboxCellEditor() {
		 super(new JComboBox<String>());
	        this.comboBoxes = new HashMap<>();
	}
    
    public void addComboBoxForRow(int row, JComboBox<String> comboBox) {
        comboBoxes.put(row, comboBox);
    }
	
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JComboBox<String> comboBox = comboBoxes.get(row);
        if (comboBox != null) {
            comboBox.setSelectedItem(value);
            return comboBox;
        }
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
        JComboBox<String> comboBox = (JComboBox<String>) getComponent();
        return comboBox.getSelectedItem();
    }

    // Lấy combobox hiện tại
    public JComboBox<String> getComboBox() {
        return (JComboBox<String>) getComponent();
    }

}
