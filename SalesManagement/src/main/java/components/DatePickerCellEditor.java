package components;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;

public class DatePickerCellEditor extends AbstractCellEditor implements TableCellEditor {
	private JDateChooser dateChooser;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DatePickerCellEditor() {
        dateChooser = new JDateChooser();
    }

    @Override
    public Object getCellEditorValue() {
        return dateChooser.getDate();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Kiểm tra nếu giá trị là String, chuyển đổi sang Date
        if (value instanceof String) {
            String dateStr = (String) value;
            if (dateStr.trim().isEmpty()) {
                // Nếu chuỗi rỗng, không set giá trị cho JDateChooser
                dateChooser.setDate(null);
            } else {
                try {
                    Date date = dateFormat.parse(dateStr);
                    dateChooser.setDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    dateChooser.setDate(null);
                }
            }
        } else if (value instanceof Date) {
            dateChooser.setDate((Date) value);
        }

        return dateChooser;
    }
}
