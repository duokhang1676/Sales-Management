/*
 * Click nbfs            @Override
            public Dimension getScreenSize() throws HeadlessException {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int getScreenResolution() throws HeadlessException {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public ColorModel getColorModel() throws HeadlessException {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public String[] getFontList() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void sync() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Image getImage(String filename) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Image getImage(URL url) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Image createImage(String filename) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Image createImage(URL url) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean prepareImage(Image image, int width, int height, ImageObserver observer) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int checkImage(Image image, int width, int height, ImageObserver observer) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Image createImage(ImageProducer producer) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Image createImage(byte[] imagedata, int imageoffset, int imagelength) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void beep() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Clipboard getSystemClipboard() throws HeadlessException {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            protected EventQueue getSystemEventQueueImpl() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean isModalityTypeSupported(Dialog.ModalityType modalityType) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean isModalExclusionTypeSupported(Dialog.ModalExclusionType modalExclusionType) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Map<TextAttribute, ?> mapInputMethodHighlight(InputMethodHighlight highlight) throws HeadlessException {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }//nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;
import db.ConnectDB;
import entities.TaiKhoan;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.JFrame;

import components.HashPasswordSHA256;
import components.LoginInfo;
import components.Setting;
import dao.TaiKhoanDao;

/**
 *
 * @author ACER
 */
public class DangNhap extends javax.swing.JFrame {

    /**
     * Creates new form Login_Page
     */
    public DangNhap() {
        initComponents();
        Image img = new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage();
        setIconImage(img);
        setLocationRelativeTo(null);
       
        connectionDB(false);//parameter true dung clound db false dung local db
        boolean rememberTaiKhoan = Setting.getNhoTK();
        if(rememberTaiKhoan) {
        	checkNhoTK.setSelected(true);
        	txt_taiKhoan.setText(LoginInfo.latestTK.getTenDangNhap());
    		txtMatKhau.setText(LoginInfo.latestTK.getMatKhau());
        }
    }

 

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_main = new javax.swing.JPanel();
        jP_logo = new javax.swing.JPanel();
        jL_logo = new javax.swing.JLabel();
        jP_loginArea = new javax.swing.JPanel();
        jL_matKhau = new javax.swing.JLabel();
        jL_taiKhoan = new javax.swing.JLabel();
        txt_taiKhoan = new javax.swing.JTextField();
        btn_thoat = new javax.swing.JButton();
        btn_dangNhap = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        checkNhoTK = new javax.swing.JCheckBox();
        jP_wel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlbThongBao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setAlwaysOnTop(true);
        setResizable(false);

        jP_main.setBackground(new java.awt.Color(255, 255, 255));
        jP_main.setMaximumSize(new java.awt.Dimension(1000, 700));
        jP_main.setMinimumSize(new java.awt.Dimension(1000, 700));

        jP_logo.setBackground(new java.awt.Color(255, 255, 255));
        jP_logo.setLayout(null);

        jL_logo.setBackground(new java.awt.Color(255, 255, 255));
        jL_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logoStore.png"))); // NOI18N
        jP_logo.add(jL_logo);
        jL_logo.setBounds(110, 10, 760, 220);

        jP_loginArea.setBackground(new java.awt.Color(255, 255, 255));
        jP_loginArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jL_matKhau.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jL_matKhau.setText("Mật khẩu: ");
        jP_loginArea.add(jL_matKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        jL_taiKhoan.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jL_taiKhoan.setText("Tài khoản: ");
        jP_loginArea.add(jL_taiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        txt_taiKhoan.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_taiKhoan.setToolTipText("Mã nhân viên");
        txt_taiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_taiKhoanActionPerformed(evt);
            }
        });
        jP_loginArea.add(txt_taiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 500, 50));

        btn_thoat.setBackground(new java.awt.Color(193, 219, 208));
        btn_thoat.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        btn_thoat.setText("Thoát");
        btn_thoat.setMaximumSize(new java.awt.Dimension(160, 40));
        btn_thoat.setMinimumSize(new java.awt.Dimension(160, 40));
        btn_thoat.setPreferredSize(new java.awt.Dimension(160, 40));
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });
        jP_loginArea.add(btn_thoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, -1, -1));
        btn_thoat.getAccessibleContext().setAccessibleDescription("");

        btn_dangNhap.setBackground(new java.awt.Color(193, 219, 208));
        btn_dangNhap.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        btn_dangNhap.setText("Đăng nhập");
        btn_dangNhap.setMaximumSize(new java.awt.Dimension(160, 40));
        btn_dangNhap.setMinimumSize(new java.awt.Dimension(160, 40));
        btn_dangNhap.setPreferredSize(new java.awt.Dimension(160, 40));
        btn_dangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangNhapActionPerformed(evt);
            }
        });
        jP_loginArea.add(btn_dangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        jLabel2.setText("* By Nhom_02 PhatTrienUngDung DHKHMT17CTT");
        jP_loginArea.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, -1, -1));

        txtMatKhau.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        jP_loginArea.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 500, 50));

        checkNhoTK.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        checkNhoTK.setText("Ghi nhớ tài khoản");
        jP_loginArea.add(checkNhoTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, -1, -1));

        jP_wel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome!");

        jlbThongBao.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jlbThongBao.setForeground(new java.awt.Color(255, 0, 0));
        jlbThongBao.setText(" ");

        javax.swing.GroupLayout jP_welLayout = new javax.swing.GroupLayout(jP_wel);
        jP_wel.setLayout(jP_welLayout);
        jP_welLayout.setHorizontalGroup(
            jP_welLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_welLayout.createSequentialGroup()
                .addGap(414, 414, 414)
                .addGroup(jP_welLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_welLayout.setVerticalGroup(
            jP_welLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_welLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jP_mainLayout = new javax.swing.GroupLayout(jP_main);
        jP_main.setLayout(jP_mainLayout);
        jP_mainLayout.setHorizontalGroup(
            jP_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jP_loginArea, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addGroup(jP_mainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP_wel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jP_mainLayout.setVerticalGroup(
            jP_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_mainLayout.createSequentialGroup()
                .addComponent(jP_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jP_wel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jP_loginArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
        dangNhap();
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void btn_dangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangNhapActionPerformed
        // TODO add your handling code here
    	LoginInfo.latestTK.setTenDangNhap(txt_taiKhoan.getText().trim());
    	LoginInfo.latestTK.setMatKhau(txtMatKhau.getText().trim());
        Setting.setNhoTK(checkNhoTK.isSelected());
    	dangNhap();
    }//GEN-LAST:event_btn_dangNhapActionPerformed
    private void dangNhap() {
    	
    	if(!KiemTraDangNhap())
            return;
            this.setVisible(false);
            LoginInfo.rootFrame =  new RootFrame();
            LoginInfo.rootFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            LoginInfo.rootFrame.setVisible(true);
    }
    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void txt_taiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_taiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_taiKhoanActionPerformed
    
    private void connectionDB(boolean useSocialv2) {
    	if(!useSocialv2) {
    		try {
        		ConnectDB.getInstance().connect();
                System.out.println("Kết nối csld local thành công!");
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("Kết nối csld local thất bại!");
			}
    		return;
    	}
    	int TIMEOUT_SECONDS = 3;
    	boolean isConnected = false;
    	ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Void> future = executor.submit(() -> {
                //ConnectDB.getInstance().connect2();
                System.out.println("Kết nối csld trong server social-v2 thành công!");
            return null;
        });

        try {
            // Giới hạn thời gian chờ đợi
            future.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.err.println("Connection timed out after " + TIMEOUT_SECONDS + " seconds");
            future.cancel(true); // Hủy bỏ nếu vượt quá thời gian timeout
            try {
        		ConnectDB.getInstance().connect();
                System.out.println("Kết nối csld local thành công!");
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("Kết nối csld local thất bại!");
			}
        } catch (InterruptedException | ExecutionException e) {
            // 
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Đảm bảo executor sẽ được dừng sau khi sử dụng
        }
	}

	private boolean KiemTraDangNhap() {
		TaiKhoan tk = new TaiKhoanDao().timHangHoaTheoMa(txt_taiKhoan.getText().trim());
		if(tk!=null) {
			if(HashPasswordSHA256.hashPassword(txtMatKhau.getText().trim()).equalsIgnoreCase(tk.getMatKhau())) {
				LoginInfo.tk = tk;
				return true;
			}
			else {
				jlbThongBao.setText("Mật khẩu không đúng!");
				txtMatKhau.requestFocus();
				return false;
			}
		}else {	
			jlbThongBao.setText("Tài khoản không tồn tại!");
			txt_taiKhoan.requestFocus();
			return false;
		}
	}

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhap dangNhap = new DangNhap();
                dangNhap.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_dangNhap;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JCheckBox checkNhoTK;
    private javax.swing.JLabel jL_logo;
    private javax.swing.JLabel jL_matKhau;
    private javax.swing.JLabel jL_taiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jP_loginArea;
    private javax.swing.JPanel jP_logo;
    private javax.swing.JPanel jP_main;
    private javax.swing.JPanel jP_wel;
    private javax.swing.JLabel jlbThongBao;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txt_taiKhoan;
    // End of variables declaration//GEN-END:variables

    private void myModifyCode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        Image img = new Image("D:\\Nam3\\HK2\\PhatTrienUngDung\\HinhAnh\\K3TD_logo_small.png");
//        setIconImage(img.getImage());
//        setIconImage(new ImageIcon("D:\\Nam3\\HK2\\PhatTrienUngDung\\HinhAnh\\K3TD_logo_small.png").getImage());
//        Image img; 
//		img = Toolkit.getDefaultToolkit().createImage("img\\img_logoSmall.png");
//		setIconImage(img);
    }
}
