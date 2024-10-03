/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sampleUi;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 *
 * @author thuthao
 */
public class TimSDTKhachHang extends JTextField{
    private String hintText ="Nhập số điện thoại";
    /**
     * @return the prefixIcon
     */
    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    /**
     * @param prefixIcon the prefixIcon to set
     */
    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }
    private Icon prefixIcon;
    
    
    public TimSDTKhachHang() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        paintIcon(g);
        
        //Paint border
        if (isFocusOwner()) {
            g.setColor(new Color(43, 124, 220));
            g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        }
        else{
            g.setColor(new Color(105, 122, 143));
            g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(hintText, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    
    private void paintIcon(Graphics g){
        Graphics2D g2 =  (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight())/2;
            g2.drawImage(prefix,0,y,this);
        }
    }
    
    private void initBorder(){
        int left = 5;
        int right = 5;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth();
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, left, 5, right));
    }
}
