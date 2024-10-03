package components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class NumericCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JTextField textField;

    public NumericCellEditor() {
        textField = new JTextField();
        // Áp dụng NumericDocumentFilter cho JTextField
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText(); // Trả về giá trị số dưới dạng String
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Thiết lập giá trị ban đầu cho ô
        textField.setText((value != null) ? value.toString() : "");
        return textField;
    }
}
