/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dao.ChiTietPhieuXuatTraDao;
import dao.ChiTietPhieuXuatTraDao;
import dao.PhieuXuatTraDao;
import dao.PhieuXuatTraDao;
import entities.ChiTietPhieuXuatTra;
import entities.PhieuXuatTra;
import entities.PhieuXuatTra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import components.AddContent;
import components.FormatJtable;
import components.Formater;
import components.PnlPhieuNhapPhieuXuat;
import components.ResizeContent;

/**
 *
 * @author LENOVO
 */
public class XuatTraPage extends javax.swing.JPanel {

	protected static DefaultTableModel model_phieuXuat;
	protected static JTable tbl_phieuXuat;
	private PhieuXuatTraDao phieuXT_dao = new PhieuXuatTraDao();
	private ChiTietPhieuXuatTraDao chiTietPXT_dao = new ChiTietPhieuXuatTraDao();
	/**
     * Creates new form XuatTra
     */
    public XuatTraPage() {
        initComponents();
        ResizeContent.resizeContent(this);
        addTablePhieuXT();
        loadDataPXT();
        txt_timKiemTheoMa.setText("");
    }

    private void loadDataPXT() {
		// TODO Auto-generated method stub
		int stt = 1;
		int count = 0;
		
		model_phieuXuat.setRowCount(0);
		List<PhieuXuatTra> dsPXT = phieuXT_dao.getAllDataPXT();
		for (PhieuXuatTra p : dsPXT) {
			model_phieuXuat.addRow(new Object[] {stt, p.getMaPhieuXuatTra(), p.getThoiGianTao(),
					p.tinhThanhTien(), p.getGhiChu()});
			
			stt++;
			count++;
		}
		jL_soLuong.setText("("+String.valueOf(count)+")");
	}
    private void loadDataPXTbyDate(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		int stt = 1;
		int count = 0;
		
		model_phieuXuat.setRowCount(0);
		List<PhieuXuatTra> dsPXT = phieuXT_dao.getAllDataPXTbyDate(from, to);
		for (PhieuXuatTra p : dsPXT) {
			model_phieuXuat.addRow(new Object[] {stt, p.getMaPhieuXuatTra(), p.getThoiGianTao(),
					p.tinhThanhTien(), p.getGhiChu()});
			
			stt++;
			count++;
		}
		jL_soLuong.setText("("+String.valueOf(count)+")");
	}

	private void addTablePhieuXT() {
		// TODO Auto-generated method stub
    	String[] colNames = {"STT","Mã phiếu xuất trả", "Ngày xuất", "Tổng tiền", "Ghi chú"};
        model_phieuXuat = new DefaultTableModel(colNames, 0);
        tbl_phieuXuat = new JTable(model_phieuXuat);
        FormatJtable.setFontJtable(tbl_phieuXuat);
        JScrollPane js_tableHangHoa = new JScrollPane(tbl_phieuXuat);       
        
        JTableHeader headerTable =  tbl_phieuXuat.getTableHeader();
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));
		tbl_phieuXuat.setRowHeight(40);
		TableColumnModel tb_col = tbl_phieuXuat.getColumnModel();
		tb_col.getColumn(0).setPreferredWidth(50);
		setCellEditable();
        jPanel1.add(js_tableHangHoa, BorderLayout.CENTER);
        
        tbl_phieuXuat.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {
					XemPhieuXuatTraPage PXT_info = new XemPhieuXuatTraPage();
					AddContent.addContent(PXT_info);
					int selectedRow = tbl_phieuXuat.getSelectedRow();
					String ma = model_phieuXuat.getValueAt(selectedRow, 1).toString();
				
					PhieuXuatTra PXT = phieuXT_dao.getPXTTheoMa(ma);
					
			    	List<ChiTietPhieuXuatTra> dsChiTiet= chiTietPXT_dao.getChiTietPXT(ma);			 
			    	
			    	PXT_info.txt_chietKhau.setText(Formater.decimalFormat(PXT.getTongGiamGia()));
			    	PXT_info.txt_ghiChu.setText(PXT.getGhiChu());
			    	PXT_info.txt_gio.setText(PXT.getThoiGianTao().getHour()+" : " + PXT.getThoiGianTao().getMinute());
			    	PXT_info.txt_maPhieuNhap1.setText(ma);
			    	PXT_info.txt_Ngay.setText(PXT.getThoiGianTao().getDayOfMonth()+"/"+PXT.getThoiGianTao().getMonthValue()+"/"+PXT.getThoiGianTao().getYear());
			    	PXT_info.txt_tongThanhTien.setText(Formater.decimalFormat(PXT.tinhThanhTien()));
			    	PXT_info.txt_tongTienNhap.setText(Formater.decimalFormat(PXT.getTongTienHang()));
			    	
			    	PXT_info.model_HH.setRowCount(0);
			    	int stt = 1;
			    	
			    	for (ChiTietPhieuXuatTra ct : dsChiTiet) {
			    		PXT_info.model_HH.addRow(new Object[] {stt++,ct.getHangHoa().getMaHangHoa(),ct.getHangHoa().getTenHangHoa() ,ct.getLoHang().getSoLo(), ct.getLoHang().getNgaySanXuat(),
								ct.getLoHang().getHanSuDung(), ct.getHangHoa().getDonViTinh(), ct.getSoLuong(), ct.getLoHang().getGiaNhap(),ct.getChietKhau(), ct.tinhThanhTien()});
			    	}
				}
			}
		});
        
	}
    public void setCellEditable() {
		for (int i = 0; i < tbl_phieuXuat.getColumnCount(); i++) {
			tbl_phieuXuat.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
					@Override
					public boolean isCellEditable(EventObject e) {
						// Trả về false để ngăn chặn chỉnh sửa trực tiếp
						return false;
					}
				});
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

        jPanel2 = new javax.swing.JPanel();
        btn_timKiem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dp_dateFrom = new com.github.lgooddatepicker.components.DatePicker();
        dp_dateTo = new com.github.lgooddatepicker.components.DatePicker();
        txt_timKiemTheoMa = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jL_danhSach = new javax.swing.JLabel();
        jL_soLuong = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(2013, 130));

        btn_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-16.png"))); // NOI18N
        btn_timKiem.setText("Tìm kiếm");
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });

        jLabel1.setText("Từ ngày");
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel2.setText("Đến ngày");
        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel3.setText("Tìm kiếm theo mã phiếu");
        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        dp_dateFrom.setMinimumSize(new java.awt.Dimension(200, 21));

        dp_dateTo.setMinimumSize(new java.awt.Dimension(200, 21));

        txt_timKiemTheoMa.setText("Tìm kiếm theo mã phiếu");
        txt_timKiemTheoMa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_timKiemTheoMa.setForeground(new java.awt.Color(204, 204, 204));
        txt_timKiemTheoMa.setPreferredSize(new java.awt.Dimension(200, 26));
        txt_timKiemTheoMa.setToolTipText("");
        txt_timKiemTheoMa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_timKiemTheoMaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_timKiemTheoMaFocusLost(evt);
            }
        });
        txt_timKiemTheoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemTheoMaActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Phiếu xuất trả");
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(22, 22, 22))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-16.png"))); // NOI18N
        jButton2.setText("Tạo phiếu xuất trả");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dp_dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dp_dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(100, 100, 100)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_timKiemTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_timKiem)
                        .addGap(72, 72, 72)
                        .addComponent(jButton2)))
                .addGap(86, 86, 86))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dp_dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dp_dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_timKiemTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_timKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LocalDate currentDate = LocalDate.now();
        dp_dateFrom.setDate(currentDate);
        dp_dateTo.setDate(currentDate);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(193, 219, 208));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(193, 219, 208));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jL_danhSach.setText("Danh sách phiếu xuất trả");
        jL_danhSach.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel4.add(jL_danhSach);
        jPanel4.add(jL_soLuong);

        jPanel1.add(jPanel4, java.awt.BorderLayout.NORTH);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        AddContent.addContent(PnlPhieuNhapPhieuXuat.taoPhieuXuat);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void timKiemTheoMa() {
    	 if (txt_timKiemTheoMa.getText().isEmpty()) {
             showMessage("Nhập thông tin cần tìm!");
         }else {
             PhieuXuatTra p = phieuXT_dao.getPXTTheoMa(txt_timKiemTheoMa.getText());

             if (p != null) {
                 model_phieuXuat.setRowCount(0);
                 model_phieuXuat.addRow(new Object[] {1, p.getMaPhieuXuatTra(), p.getThoiGianTao(),
                     p.tinhThanhTien(), p.getGhiChu()});
         }else {
             showMessage("Không tìm thấy!");
             loadDataPXT();
         }
         }

         txt_timKiemTheoMa.selectAll();
         txt_timKiemTheoMa.requestFocus();
    }
    private void txt_timKiemTheoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemTheoMaActionPerformed
        // TODO add your handling code here:
    	timKiemTheoMa();
       
    }//GEN-LAST:event_txt_timKiemTheoMaActionPerformed

    private void txt_timKiemTheoMaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemTheoMaFocusLost
        // TODO add your handling code here:
        //    	txt_timKiemTheoMa.setText("Tìm kiếm theo mã phiếu");
        txt_timKiemTheoMa.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txt_timKiemTheoMaFocusLost

    private void txt_timKiemTheoMaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemTheoMaFocusGained
        // TODO add your handling code here:
        txt_timKiemTheoMa.setText("");
        txt_timKiemTheoMa.setForeground(Color.black);
    }//GEN-LAST:event_txt_timKiemTheoMaFocusGained

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        // TODO add your handling code here:
        String ma = txt_timKiemTheoMa.getText().trim().toString();
        LocalDate dateFrom = dp_dateFrom.getDate();
        LocalDate dateTo = dp_dateTo.getDate();

        if (dateFrom == null && dateTo == null) {
            dateFrom = LocalDate.now();
            dateTo = LocalDate.now();
        }else if(dateTo == null){
            dateTo = dateFrom;
        }else if(dateFrom == null){
            dateFrom = dateTo;
        }

        if (ma.isEmpty()) {
            loadDataPXTbyDate(dateFrom,dateTo);
        }else {
            timKiemTheoMa();
        }
        
    }//GEN-LAST:event_btn_timKiemActionPerformed

    

    /**
     * @param args the command line arguments
     */
  
  
    private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_timKiem;
    private com.github.lgooddatepicker.components.DatePicker dp_dateFrom;
    private com.github.lgooddatepicker.components.DatePicker dp_dateTo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jL_danhSach;
    private javax.swing.JLabel jL_soLuong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txt_timKiemTheoMa;
    // End of variables declaration//GEN-END:variables
}
