/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import dao.HangHoaDao;
import dao.LoHangDao;

import entities.HangHoa;
import entities.LoHang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import components.AddContent;
import components.FormatJtable;
import components.PnlHangHoaInfo;
import components.StatusMenu;

/**
 *
 * @author LENOVO
 */
public class HangHoaPage extends javax.swing.JPanel implements MouseListener{


    private HangHoaDao hangHoa_dao = new HangHoaDao();
    private HangHoaDao hangHoaDao = new HangHoaDao();
    private LoHangDao loHang_dao = new LoHangDao();
    private DefaultTableModel model_hangHoa;
    private JTable tbl_hangHoa;
	/**
     * Creates new form HangHoa
     */
    public HangHoaPage() {
        initComponents();
        addTableHangHoa();
   
        loadDataTable();
    }

    private void loadDataTable() {
		// TODO Auto-generated method stub
    	int count = 0;
    	int stt = 1;
    	String loaiHang = null;
    	model_hangHoa.setNumRows(0);
		
		List<entities.HangHoa> dsHangHoa = hangHoa_dao.getAllDataHangHoa();
		for (entities.HangHoa hh : dsHangHoa) {
//String[] colNames = {"STT","Mã hàng hóa", "Tên hàng hóa", "Nhóm hàng",  "Đơn vị tính","Số lượng","Giá bán", "Mô tả"};
			model_hangHoa.addRow(new Object[] {stt, hh.getMaHangHoa(), hh.getTenHangHoa(),
					hh.getNhomHang(),hh.getDonViTinh(), hh.getSoLuongDinhMuc(), hh.getGiaBan(),hh.getMoTa()});
			
			
			stt++;
			count++;
		}
		jL_soLuongHH.setText("("+String.valueOf(count)+")");
		
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
        txt_timKiem = new javax.swing.JTextField();
        btn_timKiem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_NhapExcel = new javax.swing.JButton();
        btn_XuatExcel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btn_themHH = new javax.swing.JButton();
        jL_soLuongHH = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1920, 70));

        txt_timKiem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_timKiem.setForeground(new java.awt.Color(204, 204, 204));
        txt_timKiem.setText("Tìm kiếm theo mã hàng hóa hoặc mã vạch.");
        txt_timKiem.setToolTipText("Tìm kiếm theo mã hàng hóa hoặc mã vạch.");
        txt_timKiem.setPreferredSize(new java.awt.Dimension(200, 26));
        txt_timKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusLost(evt);
            }
        });
        txt_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemActionPerformed(evt);
            }
        });

        btn_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-16.png"))); // NOI18N
        btn_timKiem.setText("Tìm kiếm");
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Từ khóa tìm kiếm");

        btn_NhapExcel.setText("Nhập từ file excel");
        btn_NhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NhapExcelActionPerformed(evt);
            }
        });

        btn_XuatExcel.setText("Xuất ra file excel");
        btn_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(1773, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_NhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btn_XuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_NhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(193, 219, 208));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(193, 219, 208));
        jPanel3.setPreferredSize(new java.awt.Dimension(1991, 50));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel5.setText("Danh sách hàng hóa");

        btn_themHH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-16.png"))); // NOI18N
        btn_themHH.setText("Thêm hàng hóa mới");
        btn_themHH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themHHActionPerformed(evt);
            }
        });

        jL_soLuongHH.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_soLuongHH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1648, Short.MAX_VALUE)
                .addComponent(btn_themHH)
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btn_themHH)
                    .addComponent(jL_soLuongHH))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        // TODO add your handling code here:
    	timKiem();
    }//GEN-LAST:event_btn_timKiemActionPerformed

    private void timKiem() {
		// TODO Auto-generated method stub
    	String keyword = txt_timKiem.getText().trim();
    	if (keyword.isEmpty()) {
			showMessage("Nhập thông tin cần tìm!");
			txt_timKiem.requestFocus();
			loadDataTable();
		}else {
			entities.HangHoa hh = hangHoa_dao.timHangHoaTheoMaVachVaMaHH(keyword);
			
			if (hh == null) {
				showMessage("Không tìm thấy!");
				txt_timKiem.requestFocus();
		    	txt_timKiem.selectAll();
		    	loadDataTable();
				return;
			}
			model_hangHoa.setNumRows(0);
			model_hangHoa.addRow(new Object[] {1, hh.getMaHangHoa(), hh.getTenHangHoa(),
					hh.getNhomHang(),hh.getDonViTinh(), hh.getSoLuongDinhMuc(), hh.getGiaBan(),hh.getMoTa()});
			jL_soLuongHH.setText("1");
		}
    	txt_timKiem.requestFocus();
    	txt_timKiem.selectAll();
	}

	private void txt_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemActionPerformed
        // TODO add your handling code here:
    	timKiem();
    }//GEN-LAST:event_txt_timKiemActionPerformed


    private void btn_themHHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themHHActionPerformed
        // TODO add your handling code here:
    	AddContent.addContent(new TaoHangHoaPage());
    }//GEN-LAST:event_btn_themHHActionPerformed

    private void txt_timKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusGained
        // TODO add your handling code here:
    	txt_timKiem.setText("");
    	txt_timKiem.setForeground(Color.black);
    }//GEN-LAST:event_txt_timKiemFocusGained

    private void txt_timKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusLost
        // TODO add your handling code here:
//    	txt_timKiem.setText("Tìm kiếm theo mã hàng, tên hàng");
    	txt_timKiem.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txt_timKiemFocusLost
    
    // Hàm đọc file Excel và chèn dữ liệu vào bảng sử dụng themHangHoa từ Dao
    public void importExcelToDatabase(String excelFilePath) {
        HangHoaDao hangHoaDao = new HangHoaDao(); // Tạo đối tượng HangHoaDao
        int temp = 0;
        List<HangHoa> dsHH = new ArrayList<HangHoa>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Lấy sheet đầu tiên từ file Excel
            Sheet sheet = workbook.getSheetAt(0);
            
            // Bắt đầu đọc từ dòng thứ 1 (bỏ qua tiêu đề nếu có)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            	temp = i+1;
                Row row = sheet.getRow(i);

                // Đọc các giá trị từ các cột          
                String tenHangHoa = row.getCell(1).getStringCellValue();
                String nhomHang = row.getCell(2).getStringCellValue();
                String donViTinh = row.getCell(3).getStringCellValue();
                String moTa = row.getCell(4).getStringCellValue();
                String maVach = row.getCell(5).getStringCellValue();
                double giaBan = row.getCell(6).getNumericCellValue();
                int soLuongDinhMuc = 0;
                int soLuongCanhBao = (int) row.getCell(7).getNumericCellValue();

                // Tạo đối tượng HangHoa
                HangHoa hangHoa = new HangHoa(null,tenHangHoa, nhomHang, donViTinh, moTa, maVach, giaBan, soLuongDinhMuc, soLuongCanhBao);
                dsHH.add(hangHoa);
            }
            for (int i = 0; i < sheet.getLastRowNum(); i++) 
            	hangHoa_dao.themHangHoa(dsHH.get(i));
            
            JOptionPane.showMessageDialog(this, "Nhập thành công "+sheet.getLastRowNum()+" sản phẩm");
            loadDataTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi tại dòng thứ "+temp+" trong file excel");
            
        }
    }
    
    public static String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        
        // Chỉ cho phép chọn file với phần mở rộng là Excel
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null); // Hiển thị cửa sổ chọn file
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); // Lấy file đã chọn
            return selectedFile.getAbsolutePath(); // Trả về đường dẫn tuyệt đối của file
        } else {
            System.out.println("Không có file nào được chọn.");
            return null; // Trường hợp không chọn file
        }
    }
    
    private void btn_NhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NhapExcelActionPerformed
        // TODO add your handling code here:
    	int response = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn tải file nhập mẫu về máy?",
                "Xác Nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    	if(response==JOptionPane.YES_OPTION) {
    		JOptionPane.showMessageDialog(null, "Chọn nơi lưu file nhập mẫu!");
    		String filePath = "data/PhieuNhapMau.xlsx";
            // Tạo JFileChooser để chọn nơi lưu file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file");
         // Thiết lập tên file mặc định
            fileChooser.setSelectedFile(new File("PhieuNhapMau.xlsx"));
            // Mở hộp thoại lưu file
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Người dùng đã chọn vị trí lưu file
                File saveFile = fileChooser.getSelectedFile();

                // Thực hiện sao chép file
                try (FileInputStream inputStream = new FileInputStream(filePath);
                     FileOutputStream outputStream = new FileOutputStream(saveFile)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    // Sao chép dữ liệu từ file gốc tới file đã chọn
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    JOptionPane.showMessageDialog(null, "File đã được lưu thành công tại: " + saveFile.getAbsolutePath());

                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Lỗi khi lưu file.");
                }
            }
    	}else {
    	JOptionPane.showMessageDialog(null, "Chọn file xlsx cần nhập!");
    	String path = chooseFile();
    	if(path == null)
    		return;
    	importExcelToDatabase(path);
    	}
    }//GEN-LAST:event_btn_NhapExcelActionPerformed
    public static void exportToExcel(String excelFilePath) {
        HangHoaDao hangHoaDao = new HangHoaDao();
        List<HangHoa> hangHoaList = hangHoaDao.getAllDataHangHoa();

        try (Workbook workbook = new XSSFWorkbook()) {
            // Tạo sheet mới trong workbook
            Sheet sheet = workbook.createSheet("Danh sách hàng hóa");

            // Tạo hàng đầu tiên cho tiêu đề các cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"STT","MÃ HÀNG HÓA", "TÊN HÀNG HÓA", "NHÓM HÀNG", "ĐƠN VỊ TÍNH", "MÔ TẢ", "MÃ VẠCH", "GIÁ BÁN", "SỐ LƯỢNG ĐỊNH MỨC", "SỐ LƯỢNG CẢNH BÁO"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Duyệt qua danh sách hàng hóa và thêm dữ liệu vào Excel
            int rowNum = 1; // Bắt đầu từ dòng 1, vì dòng 0 là tiêu đề
            for (HangHoa hangHoa : hangHoaList) {
                Row row = sheet.createRow(rowNum++);
                
                row.createCell(0).setCellValue(rowNum-1);
                row.createCell(1).setCellValue(hangHoa.getMaHangHoa());
                row.createCell(2).setCellValue(hangHoa.getTenHangHoa());
                row.createCell(3).setCellValue(hangHoa.getNhomHang());
                row.createCell(4).setCellValue(hangHoa.getDonViTinh());
                row.createCell(5).setCellValue(hangHoa.getMoTa());
                row.createCell(6).setCellValue(hangHoa.getMaVach());
                row.createCell(7).setCellValue(hangHoa.getGiaBan());
                row.createCell(8).setCellValue(hangHoa.getSoLuongDinhMuc());
                row.createCell(9).setCellValue(hangHoa.getSoLuongCanhBao());
            }

            // Tạo file Excel và lưu dữ liệu vào file
            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "File Excel đã được tạo thành công tại: " + excelFilePath);
            }

        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null,"Lỗi khi lưu file!");
            e.printStackTrace();
        }
    }
    private void btn_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcelActionPerformed
        // TODO add your handling code here:
    	String path = chooseFile();
    	if(path==null)
    		return;
    	exportToExcel(path);
    }//GEN-LAST:event_btn_XuatExcelActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_NhapExcel;
    private javax.swing.JButton btn_XuatExcel;
    private javax.swing.JButton btn_themHH;
    private javax.swing.JButton btn_timKiem;
    private javax.swing.JLabel jL_soLuongHH;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables

    private void addTableHangHoa() {
        String[] colNames = {"STT","Mã hàng hóa", "Tên hàng hóa", "Nhóm hàng",  "Đơn vị tính","Số lượng","Giá bán", "Mô tả"};
        
        model_hangHoa = new DefaultTableModel(colNames, 0);
        tbl_hangHoa = new JTable(model_hangHoa);
        FormatJtable.setFontJtable(tbl_hangHoa);
        JScrollPane js_tableHangHoa = new JScrollPane(tbl_hangHoa);
        
        if (tbl_hangHoa.getColumnModel().getColumnCount() > 0) {
            tbl_hangHoa.getColumnModel().getColumn(0).setResizable(false);
            tbl_hangHoa.getColumnModel().getColumn(1).setResizable(false);
            tbl_hangHoa.getColumnModel().getColumn(2).setResizable(false);
            tbl_hangHoa.getColumnModel().getColumn(3).setResizable(false);
            tbl_hangHoa.getColumnModel().getColumn(4).setResizable(false);
            tbl_hangHoa.getColumnModel().getColumn(5).setResizable(false);
            tbl_hangHoa.getColumnModel().getColumn(6).setResizable(false);
            tbl_hangHoa.getColumnModel().getColumn(7).setResizable(false);
        }
        
        JTableHeader headerTable =  tbl_hangHoa.getTableHeader();
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));
		tbl_hangHoa.setRowHeight(40);
		TableColumnModel tb_col = tbl_hangHoa.getColumnModel();
		tb_col.getColumn(0).setPreferredWidth(50);
		setCellEditable();
        jPanel1.add(js_tableHangHoa, BorderLayout.CENTER);
        
        tbl_hangHoa.addMouseListener(this);
    }
    public void setCellEditable() {
		for (int i = 0; i < tbl_hangHoa.getColumnCount(); i++) {
			tbl_hangHoa.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
					@Override
					public boolean isCellEditable(EventObject e) {
						// Trả về false để ngăn chặn chỉnh sửa trực tiếp
						return false;
					}
				});
			}
	}
    
    private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			PnlHangHoaInfo.hangHoaInfo = new XemThongTinHangHoaPage();
			AddContent.addContent(PnlHangHoaInfo.hangHoaInfo);
	    	
		
	    	int row = tbl_hangHoa.getSelectedRow();
	    	String maHH = tbl_hangHoa.getValueAt(row, 1).toString();
	    	
	    	HangHoa hh = hangHoa_dao.timHangHoaTheoMa(maHH);
	    	
	    	PnlHangHoaInfo.hangHoaInfo.txt_maHangHoa.setText(maHH);
	    	PnlHangHoaInfo.hangHoaInfo.txt_tenHangHoa1.setText(hh.getTenHangHoa());
	    	PnlHangHoaInfo.hangHoaInfo.txt_donViTinh.setText(hh.getDonViTinh());
	    	PnlHangHoaInfo.hangHoaInfo.txt_giaBan.setText(hh.getGiaBan()+"");
	    	PnlHangHoaInfo.hangHoaInfo.txt_maVach.setText(hh.getMaVach());
	    	PnlHangHoaInfo.hangHoaInfo.txt_moTa.setText(hh.getMoTa());
	    	PnlHangHoaInfo.hangHoaInfo.txt_nhomHH.setText(hh.getNhomHang());
	    	PnlHangHoaInfo.hangHoaInfo.txt_soLuongCanhBao.setText(hh.getSoLuongCanhBao()+"");
	    	PnlHangHoaInfo.hangHoaInfo.txt_soLuongDinhMuc1.setText(hh.getSoLuongDinhMuc()+"");
	    	
	    	
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
