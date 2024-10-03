/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author ACER
 */
public class ResizeContent {
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static void resizeContent(JPanel p){
        
        p.setPreferredSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight() -220));
    }
    public static int getWidth(){
        return (int)screenSize.getWidth();
    }
    public static int getHeight(){
        return (int)screenSize.getHeight();
    }
    public static double getScale() {
    	return toolkit.getScreenResolution() / 96.0;
    }
}

