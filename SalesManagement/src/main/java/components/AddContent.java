/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class AddContent {
    public static JPanel pnlContent;
    public static void setContent(JPanel content){
        pnlContent = content;
    }
    public static void addContent(JPanel content) {
    	StatusMenu.setFalseAll();
    	pnlContent.removeAll();
    	pnlContent.add(content);
    	pnlContent.revalidate();
    	pnlContent.repaint();
    }
}
