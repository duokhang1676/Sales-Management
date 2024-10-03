package components;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TableActionCellEditor extends DefaultCellEditor{

	private TableActionEvent event;

	public TableActionCellEditor(TableActionEvent event) {
		super(new JCheckBox());
		// TODO Auto-generated constructor stub
		this.event =  event;
	}



	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		PanelAction action = new PanelAction();
		action.initEvent(event, row);
		action.setBackground(table.getSelectionBackground());
		return action;
	}
	
	
}
