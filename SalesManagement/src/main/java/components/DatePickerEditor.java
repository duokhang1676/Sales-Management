package components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.time.LocalDate;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.github.lgooddatepicker.components.DatePicker;

public class DatePickerEditor extends AbstractCellEditor implements TableCellEditor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 581914410128928599L;
	
	private JPanel panel = new JPanel();
    private DatePicker picker = new DatePicker();
    

    public DatePickerEditor() {
        panel.setLayout(new BorderLayout());
        panel.add(picker, BorderLayout.CENTER);
    }
    

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub 
        return panel;
	}
	@Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }


	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
