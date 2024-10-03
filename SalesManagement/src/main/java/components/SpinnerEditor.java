package components;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class SpinnerEditor extends DefaultCellEditor{
	public final JSpinner spinner;

    public SpinnerEditor() {
        super(new JTextField());
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1)); // Minimum value is set to 1
        spinner.setValue(1); // Initial value
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }
}
