	package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;


public class ActionButton extends JButton{
	private boolean mousePress;
	
	public ActionButton() {
		setContentAreaFilled(false);
		setBorder(new EmptyBorder(3,3,3,3));
//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				mousePress=true;
//			}
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				mousePress=false;
//			}
//		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		int width = getWidth();
//		int height = getHeight();
//		int size=Math.min(width, height);
//		int x = (width-size)/2;
//		int y = (height-size)/2;
//		if(mousePress) {
//			g2.setColor(Color.blue);
//		}else {
//			g2.setColor(Color.gray);
//		}
		g2.dispose();
		super.paintComponent(g);
	}
}
