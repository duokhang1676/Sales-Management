package components;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class ButtonColumnUtil {
    public static void addButtonColumn(JTable table, int columnIndex, ActionListener actionListener) {
        // Thêm Renderer
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(new ButtonRenderer());
        
        // Thêm Editor
        table.getColumnModel().getColumn(columnIndex).setCellEditor(new ButtonEditor(new JTextField(), actionListener));
    }
}

class ButtonRenderer extends DefaultTableCellRenderer {
    private JButton button;

    public ButtonRenderer() {
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(UIManager.getColor("Button.background"));
        }
        button.setText((value == null) ? "" : value.toString());
        return button;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private ActionListener actionListener;

    public ButtonEditor(JTextField textField, ActionListener actionListener) {
        super(textField);
        button = new JButton();
        button.setOpaque(true);
        this.actionListener = actionListener;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                ButtonEditor.this.actionListener.actionPerformed(e);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

//    @Override
//    public Object getCellEditorValue() {
//        if (isPushed) {
//            // Xử lý sự kiện khi nút được nhấn
//            JOptionPane.showMessageDialog(button, label + " Button clicked!");
//        }
//        isPushed = false;
//        return label;
//    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
