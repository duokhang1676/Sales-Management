package components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.github.lgooddatepicker.components.DatePicker;

public class DatePickerRenderer extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126954614692063371L;
	private JPanel panel = new JPanel();
    private DatePicker picker = new DatePicker();

    public DatePickerRenderer() {
        panel.setLayout(new BorderLayout());
        panel.add(picker, BorderLayout.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
       
        return panel;
    }
}
