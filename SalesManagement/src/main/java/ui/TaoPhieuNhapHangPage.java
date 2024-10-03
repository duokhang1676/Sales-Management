/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.ChiTietPhieuNhapHangDao;

import dao.HangHoaDao;
import dao.LoHangDao;

import dao.PhieuNhapHangDao;
import entities.ChiTietPhieuNhapHang;
import entities.HangHoa;
import entities.LoHang;

import entities.PhieuNhapHang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;

import com.github.lgooddatepicker.components.DatePicker;

import components.AddContent;
import components.ButtonRender;
import components.ComboboxCellEditor;
import components.DatePickerCellEditor;
import components.DatePickerEditor;
import components.DatePickerRenderer;
import components.FormatJtable;
import components.Formater;
import components.NumericCellEditor;
import components.NumericDocumentFilter;
import components.PnlPhieuNhapPhieuXuat;
import components.ResizeContent;
import components.SpinnerEditor;
import components.TableActionCellEditor;
import components.TableActionEvent;

/**
 *
 * @author ACER
 */
public class TaoPhieuNhapHangPage extends javax.swing.JPanel {


	private PhieuNhapHangDao phieuNhapHang_dao = new PhieuNhapHangDao();
	private HangHoaDao hangHoa_dao = new HangHoaDao();

	private ChiTietPhieuNhapHangDao chiTietPNH_dao = new ChiTietPhieuNhapHangDao();
	private LoHangDao loHang_dao = new LoHangDao();
	protected static JTable tbl_HH;
	protected static DefaultTableModel model_HH;
	private DatePicker datePicker;
	private JComboBox cb_tenDVT;
	
	/**
     * Creates new form NhapHang
     */
    public TaoPhieuNhapHangPage() {
        initComponents();
        new ResizeContent().resizeContent(this);
        addNhapHangTable();
        setTable();
        setFont();
    
    }
    private void setFont() {
		// TODO Auto-generated method stub
    	Font font = new Font("Arial", Font.BOLD, 16);
    	txt_chietKhau.setFont(font);
    	txt_ghiChu.setFont(font);
    	txt_tongThanhTien.setFont(font);
    	txt_tongTienNhap.setFont(font);
    	((AbstractDocument) txt_tongThanhTien.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    	((AbstractDocument) txt_tongTienNhap.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }
	public void setTable() {
    	TableColumn column = tbl_HH.getColumnModel().getColumn(7);
        column.setCellEditor(new SpinnerEditor());
        TableActionEvent event = new TableActionEvent() {
			
			@Override
			public void onDelete(int row) {
				if(tbl_HH.isEditing()) {
					tbl_HH.getCellEditor().stopCellEditing();
				}
				DefaultTableModel model = (DefaultTableModel)tbl_HH.getModel();
				model.removeRow(row);
				
				reload();
				
			}

			
		};
		tbl_HH.getColumnModel().getColumn(12).setCellEditor(new TableActionCellEditor(event));
		tbl_HH.getColumnModel().getColumn(12).setCellRenderer(new ButtonRender());
        
		model_HH.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {//Bắt sự kiện thay đổi số lượng trên jtable
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 6) {
                	int row = tbl_HH.getSelectedRow();
                	if(row==-1)return; //Trường hợp sl thay đổi khi thêm mới 
                	
                   
                   reload();
                }
            }	
        });
    }

    private void reload() {
		// TODO Auto-generated method stub
		
	}
    private void addNhapHangTable() {
		// TODO Auto-generated method stub
    	
    	String[] colNames = {"STT", "Mã HH", "Tên HH", "Số lô","Ngày sản xuất", "Hạn sử dụng", "DVT", "Số lượng", "Giá nhập", "VAT (%)", "Chiết khấu", "Thành tiền", "Xóa"};
        
        model_HH = new DefaultTableModel(colNames, 0);
        tbl_HH = new JTable(model_HH);
        FormatJtable.setFontJtable(tbl_HH);
        FormatJtable.setCellEditableForPNH(tbl_HH);
        JScrollPane js_tableHangHoa = new JScrollPane(tbl_HH);
        
        if (tbl_HH.getColumnModel().getColumnCount() > 0) {
        	tbl_HH.getColumnModel().getColumn(0).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(1).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(2).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(3).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(4).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(5).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(6).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(7).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(8).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(9).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(10).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(11).setResizable(false);
        	tbl_HH.getColumnModel().getColumn(12).setResizable(false);
        }
        
        tbl_HH.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_HH.getColumnModel().getColumn(4).setCellEditor(new DatePickerCellEditor());
        tbl_HH.getColumnModel().getColumn(5).setCellEditor(new DatePickerCellEditor());
        tbl_HH.getColumnModel().getColumn(8).setCellEditor(new NumericCellEditor());

        /*
         * add combobox vao table
         */
       
        
        JTableHeader headerTable =  tbl_HH.getTableHeader();
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));
		headerTable.setBackground(Color.white);
		tbl_HH.setRowHeight(40);
		
//		setCellEditable();
        jP_tableContent.add(js_tableHangHoa, BorderLayout.CENTER);
        
        tbl_HH.addMouseListener(new MouseListener() {
			
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
				int row = tbl_HH.getSelectedRow();
				
			}
		});
      //su kien thay doi data table
		model_HH.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if (e.getType() == TableModelEvent.UPDATE || e.getType() == TableModelEvent.INSERT ) {
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    if (col == 3) {
						String soLo = model_HH.getValueAt(row, 3).toString();
                        String maHH = model_HH.getValueAt(row, 1).toString();
						// set du lieu cua lo
						LoHang loHH = loHang_dao.timLoHangTheoMa(soLo);
						if (loHH != null) {
							model_HH.setValueAt(loHH.getNgaySanXuat(), row, 4);
							model_HH.setValueAt(loHH.getHanSuDung(), row, 5);
//							model_HH.setValueAt(loHH.getSoLuong(), row, 7);

						}
                    }
                    if (col == 7 || col == 8 || col == 10) { // Kiểm tra nếu cột "Số lượng" hoặc "Giá nhập" đã được thay đổi             
                        int soLuong = Integer.parseInt(model_HH.getValueAt(row, 7).toString());
                        double giaNhap = 0;
                        if(!model_HH.getValueAt(row, 8).toString().isEmpty())
                        giaNhap = Double.parseDouble(model_HH.getValueAt(row, 8).toString().replaceAll(",", ""));
                        model_HH.setValueAt(Formater.decimalFormat(soLuong*giaNhap), row, 11);        
                    }
                    
                    double tongGiamGia = 0;
                    double tongTienHang = 0;
                    double tongThanhTien = 0;
                    double tienNhap = 0;
                    double soLuong = 0;
                    for (int i = 0; i < model_HH.getRowCount(); i++) {
                        if(!model_HH.getValueAt(row, 8).toString().isEmpty())
                        	tienNhap = Double.parseDouble(model_HH.getValueAt(i, 8).toString().replaceAll(",", ""));
                        soLuong = Double.parseDouble(model_HH.getValueAt(i, 7).toString());
                        
                        tongTienHang += tienNhap*soLuong;
                        tongThanhTien += Double.parseDouble(model_HH.getValueAt(i, 11).toString().replaceAll(",", ""));
                        tongGiamGia =  tongTienHang - tongThanhTien;
                        
                    }
                    txt_tongTienNhap.setText(Double.toString(tongTienHang));
                    txt_tongThanhTien.setText(Double.toString(tongThanhTien));
                    txt_chietKhau.setText(Double.toString(tongGiamGia));
                }
			}
		});
	}
   



	

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_header = new javax.swing.JPanel();
        jL_maPhieuNhap = new javax.swing.JLabel();
        txt_maPhieuNhap = new javax.swing.JTextField();
        dTP_phieuNhap = new com.github.lgooddatepicker.components.DateTimePicker();
        jL_ngayLap = new javax.swing.JLabel();
        jL_gioNhap = new javax.swing.JLabel();
        jL_ghiChu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_ghiChu = new javax.swing.JTextArea();
        jL_tongTienNhap = new javax.swing.JLabel();
        txt_tongTienNhap = new javax.swing.JTextField();
        btn_taoPhieuNhap1 = new javax.swing.JButton();
        jP_body = new javax.swing.JPanel();
        jP_bodyHeader = new javax.swing.JPanel();
        jL_timHangHoa = new javax.swing.JLabel();
        txt_timHangHoa = new javax.swing.JTextField();
        txt_chietKhau = new javax.swing.JTextField();
        jL_chietKhau = new javax.swing.JLabel();
        jL_iconTimKiem = new javax.swing.JLabel();
        txt_tongThanhTien = new javax.swing.JTextField();
        jL_tongThanhTien = new javax.swing.JLabel();
        btn_thoat1 = new javax.swing.JButton();
        jP_tableContent = new javax.swing.JPanel();
        jL_chiTietPhieuGhi = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1860, 700));
        setLayout(new java.awt.BorderLayout());

        jP_header.setBackground(new java.awt.Color(255, 255, 255));
        jP_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jL_maPhieuNhap.setText("Mã phiếu nhập:");
        jL_maPhieuNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_header.add(jL_maPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txt_maPhieuNhap.setEditable(false);
        txt_maPhieuNhap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_maPhieuNhap.setPreferredSize(new java.awt.Dimension(400, 35));
        txt_maPhieuNhap.setToolTipText("Mã tự phát sinh");
        txt_maPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maPhieuNhapActionPerformed(evt);
            }
        });
        jP_header.add(txt_maPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 200, -1));

        dTP_phieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        dTP_phieuNhap.setPreferredSize(new java.awt.Dimension(400, 35));
        jP_header.add(dTP_phieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 300, -1));
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        dTP_phieuNhap.setDateTimePermissive(currentDateTime);

        jL_ngayLap.setText("Ngày lập phiếu:");
        jL_ngayLap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_header.add(jL_ngayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        jL_gioNhap.setText("Giờ nhập phiếu:");
        jL_gioNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_header.add(jL_gioNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        jL_ghiChu.setText("Ghi chú:");
        jL_ghiChu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_header.add(jL_ghiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, -1, -1));

        txt_ghiChu.setColumns(20);
        txt_ghiChu.setRows(5);
        jScrollPane2.setViewportView(txt_ghiChu);

        jP_header.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 350, 35));

        jL_tongTienNhap.setText("Tổng tiền hàng:");
        jL_tongTienNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_header.add(jL_tongTienNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 100, -1));

        txt_tongTienNhap.setEditable(false);
        txt_tongTienNhap.setBackground(new java.awt.Color(255, 255, 255));
        txt_tongTienNhap.setPreferredSize(new java.awt.Dimension(150, 35));
        txt_tongTienNhap.setToolTipText("");
        txt_tongTienNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tongTienNhapActionPerformed(evt);
            }
        });
        jP_header.add(txt_tongTienNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 280, -1));
        txt_tongTienNhap.setEditable(false);

        btn_taoPhieuNhap1.setText("Tạo phiếu nhập");
        btn_taoPhieuNhap1.setBackground(new java.awt.Color(193, 219, 208));
        btn_taoPhieuNhap1.setBorderPainted(false);
        btn_taoPhieuNhap1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btn_taoPhieuNhap1.setForeground(new java.awt.Color(218, 80, 90));
        btn_taoPhieuNhap1.setPreferredSize(new java.awt.Dimension(150, 45));
        btn_taoPhieuNhap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoPhieuNhap1ActionPerformed(evt);
            }
        });
        jP_header.add(btn_taoPhieuNhap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 40, 130, 35));

        add(jP_header, java.awt.BorderLayout.PAGE_START);

        jP_body.setBackground(new java.awt.Color(255, 255, 255));
        jP_body.setLayout(new java.awt.BorderLayout(0, 10));

        jP_bodyHeader.setBackground(new java.awt.Color(255, 255, 255));
        jP_bodyHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jL_timHangHoa.setText("Tìm hàng hóa:");
        jL_timHangHoa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_bodyHeader.add(jL_timHangHoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txt_timHangHoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_timHangHoa.setPreferredSize(new java.awt.Dimension(400, 35));
        txt_timHangHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timHangHoaActionPerformed(evt);
            }
        });
        jP_bodyHeader.add(txt_timHangHoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 520, -1));

        txt_chietKhau.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_chietKhau.setPreferredSize(new java.awt.Dimension(100, 35));
        txt_chietKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chietKhauActionPerformed(evt);
            }
        });
        jP_bodyHeader.add(txt_chietKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 280, -1));
        txt_chietKhau.setEditable(false);

        jL_chietKhau.setText("Tổng giảm giá:");
        jL_chietKhau.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_bodyHeader.add(jL_chietKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        jL_iconTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-32.png"))); // NOI18N
        jP_bodyHeader.add(jL_iconTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        txt_tongThanhTien.setEditable(false);
        txt_tongThanhTien.setBackground(new java.awt.Color(255, 255, 255));
        txt_tongThanhTien.setPreferredSize(new java.awt.Dimension(150, 35));
        txt_tongThanhTien.setToolTipText("");
        jP_bodyHeader.add(txt_tongThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 350, 35));
        txt_tongThanhTien.setEditable(false);

        jL_tongThanhTien.setText("Tổng thành tiền");
        jL_tongThanhTien.setBackground(new java.awt.Color(255, 255, 255));
        jL_tongThanhTien.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jL_tongThanhTien.setForeground(new java.awt.Color(255, 102, 102));
        jP_bodyHeader.add(jL_tongThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 180, 30));

        btn_thoat1.setText("Thoát");
        btn_thoat1.setBackground(new java.awt.Color(193, 219, 208));
        btn_thoat1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btn_thoat1.setForeground(new java.awt.Color(218, 80, 90));
        btn_thoat1.setPreferredSize(new java.awt.Dimension(100, 45));
        btn_thoat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoat1ActionPerformed(evt);
            }
        });
        jP_bodyHeader.add(btn_thoat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 40, 130, 35));

        jP_body.add(jP_bodyHeader, java.awt.BorderLayout.PAGE_START);

        jP_tableContent.setLayout(new java.awt.BorderLayout(0, 10));

        jL_chiTietPhieuGhi.setText("Chi tiết phiếu ghi");
        jL_chiTietPhieuGhi.setBackground(new java.awt.Color(255, 255, 255));
        jL_chiTietPhieuGhi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jP_tableContent.add(jL_chiTietPhieuGhi, java.awt.BorderLayout.NORTH);

        jP_body.add(jP_tableContent, java.awt.BorderLayout.CENTER);

        add(jP_body, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_timHangHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timHangHoaActionPerformed
        // TODO add your handling code here:
    	int rowCount = model_HH.getRowCount();
    	if (txt_timHangHoa.getText().trim().isEmpty()) 
    		return;
    	HangHoa hh = hangHoa_dao.timHangHoaTheoMaVachVaMaHH(txt_timHangHoa.getText().trim());
    	
    	if(hh==null) {
    		showMessage("Không tìm thấy sản phẩm!");
			txt_timHangHoa.selectAll();
			txt_timHangHoa.requestFocus();
			return;
    	}else {
    		model_HH.addRow(new Object[] {rowCount+1,hh.getMaHangHoa(), hh.getTenHangHoa(),"","","",hh.getDonViTinh(), 1,0,0, 0, 0, ""});
			txt_timHangHoa.setText("");
			txt_timHangHoa.requestFocus();
    	}
    }//GEN-LAST:event_txt_timHangHoaActionPerformed

	private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}

	private void txt_chietKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chietKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chietKhauActionPerformed

    private void txt_tongTienNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tongTienNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tongTienNhapActionPerformed

    private void btn_taoPhieuNhap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoPhieuNhap1ActionPerformed
        // TODO add your handling code here:
    	//flag1
    	if(checkTable()) {
    		//tao phieu nhap hang
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        	String dateTimePicked = dTP_phieuNhap.getDateTimePermissive().toString();
        	LocalDateTime tgTao = LocalDateTime.now();
        	if(!dateTimePicked.trim().isEmpty()) 
        		tgTao = LocalDateTime.parse(dateTimePicked, formatter);
        	
        	String ghiChu = txt_ghiChu.getText();
        	Double tongGiamGia = Double.parseDouble(txt_chietKhau.getText());
        	Double tongTienHang = Double.parseDouble(txt_tongTienNhap.getText().replaceAll(",", ""));
        	Double thanhTien = Double.parseDouble(txt_tongThanhTien.getText().replaceAll(",", ""));
        	
        
        	String maPN = "PN"+System.currentTimeMillis();
        	PhieuNhapHang pnh = new PhieuNhapHang(maPN, tgTao, ghiChu, tongGiamGia, tongTienHang);
 	
        	
        	if (phieuNhapHang_dao.createPNH(pnh)) {
        		//tao chi tiet pnh
        		for (int i = 0; i < tbl_HH.getRowCount(); i++) {
        			String maHH = model_HH.getValueAt(i, 1).toString();
        			String soLo = model_HH.getValueAt(i, 3).toString().trim();
        			LocalDate nsx = null;
        			LocalDate hsd = null;
        			DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy");     
        			try {
            			nsx = LocalDate.parse(model_HH.getValueAt(i, 4).toString(),formatterr);
        				
					} catch (Exception e) {
						// TODO: handle exception
						nsx = tgTao.toLocalDate();
					}
        			
        			hsd = LocalDate.parse(model_HH.getValueAt(i, 5).toString(),formatterr);
					
        			
        			 
        			int soLuong = Integer.parseInt(model_HH.getValueAt(i, 7).toString());
        			double giaNhap = Double.parseDouble(model_HH.getValueAt(i, 8).toString().replaceAll(",", ""));
        			
        			HangHoa hh = hangHoa_dao.timHangHoaTheoMa(maHH);
        			hh.setSoLuongDinhMuc(hh.getSoLuongDinhMuc()+soLuong);
        			LoHang loHang = loHang_dao.timLoHangTheoMa(soLo);
        			if(loHang==null) {
        				loHang = new LoHang(soLo, hh, soLuong, nsx, hsd, giaNhap, tgTao.toLocalDate());
        				loHang_dao.createLoHang(loHang);
        			}else {
        				showMessage("Số lô đã tồn tại tại dòng thứ "+(i+1));
        				return;
        			}
        			
        			ChiTietPhieuNhapHang ctPNHang = new ChiTietPhieuNhapHang(pnh, loHang, hh, soLuong, 0);
        			
        			chiTietPNH_dao.addChiTietPNH(ctPNHang);
        			
        			//Cập nhật số lượng
        			hangHoa_dao.capNhatSoLuongHangHoa(hh);
        			
        		}
        		showMessage("Thêm thành công!");
        		PnlPhieuNhapPhieuXuat.taoPhieuNhap = new TaoPhieuNhapHangPage();//flag
        		AddContent.addContent(PnlPhieuNhapPhieuXuat.taoPhieuNhap);
    		}else {
    			showMessage("Thêm không thành công!");
    		}
    	}
    	
    }//GEN-LAST:event_btn_taoPhieuNhap1ActionPerformed

   

    private boolean checkTable() {
		// TODO Auto-generated method stub
    	if(model_HH.getRowCount()==0) {
    		showMessage("Lỗi danh sách phiếu ghi trống!");
			return false;
    	}
    	DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy");
    	Set<String> dsLo = new HashSet<>();
    	for(int i = 0;i<model_HH.getRowCount();i++) {
    		String soLo = model_HH.getValueAt(i, 3).toString().trim();
    		String hsd = model_HH.getValueAt(i, 5).toString();
    		
    		if(soLo.isEmpty()) {
    			showMessage("Lỗi Số lô trống tại STT"+(i+1));
    			return false;
    		}
    		if(hsd.isEmpty()) {
    			showMessage("Lỗi Hạn sử dụng trống tại STT"+(i+1));
    			return false;
    		}
    		try {
				LocalDate.parse(model_HH.getValueAt(i, 5).toString(),formatterr);
			} catch (Exception e) {
				// TODO: handle exception
				showMessage("Hạn sử dụng không hợp lệ tại STT"+ (i+1));
				return false;
			}
    		if(!dsLo.add(soLo)) {
    			showMessage("Tồn tại 2 lô hàng có cùng số lô trong danh sách!");
    			return false;
    		}
    	}
    	
    	return true;
	}
	private void txt_maPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maPhieuNhapActionPerformed

    private void btn_thoat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoat1ActionPerformed
        // TODO add your handling code here:
    	AddContent.addContent(new NhapHangPage());
    }//GEN-LAST:event_btn_thoat1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_taoPhieuNhap1;
    private javax.swing.JButton btn_thoat1;
    protected static com.github.lgooddatepicker.components.DateTimePicker dTP_phieuNhap;
    private javax.swing.JLabel jL_chiTietPhieuGhi;
    private javax.swing.JLabel jL_chietKhau;
    private javax.swing.JLabel jL_ghiChu;
    private javax.swing.JLabel jL_gioNhap;
    private javax.swing.JLabel jL_iconTimKiem;
    private javax.swing.JLabel jL_maPhieuNhap;
    private javax.swing.JLabel jL_ngayLap;
    private javax.swing.JLabel jL_timHangHoa;
    private javax.swing.JLabel jL_tongThanhTien;
    private javax.swing.JLabel jL_tongTienNhap;
    private javax.swing.JPanel jP_body;
    private javax.swing.JPanel jP_bodyHeader;
    private javax.swing.JPanel jP_header;
    private javax.swing.JPanel jP_tableContent;
    private javax.swing.JScrollPane jScrollPane2;
    protected static javax.swing.JTextField txt_chietKhau;
    protected static javax.swing.JTextArea txt_ghiChu;
    protected static javax.swing.JTextField txt_maPhieuNhap;
    private javax.swing.JTextField txt_timHangHoa;
    protected static javax.swing.JTextField txt_tongThanhTien;
    protected static javax.swing.JTextField txt_tongTienNhap;
    // End of variables declaration//GEN-END:variables
}
