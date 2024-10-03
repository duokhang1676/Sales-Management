/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import components.AddContent;
import components.FormatJtable;
import components.Formater;
import components.ResizeContent;
import dao.HoaDonDao;
import entities.HoaDon;

/**
 *
 * @author LENOVO
 */
public class HoaDonPage extends javax.swing.JPanel {

	private DefaultTableModel model_hoaDon;
	private JTable tbl_hoaDon;

	/**
	 * Creates new form NewJPanel
	 */
	public HoaDonPage() {
		initComponents();
		ResizeContent.resizeContent(this);
		addTableHoaDon();
		FormatJtable.setFontJtable(tbl_hoaDon);
		dpTuNgay.setDate(LocalDate.now());
		dbDenNgay.setDate(LocalDate.now());
		getFromTo();
		if (dsHD == null)
			txtTongSoHoaDon.setText("0");
		else
			txtTongSoHoaDon.setText(dsHD.size() + "");
		phimTat();
		showCTHD();
	}

	private void showCTHD() {
		// TODO Auto-generated method stub
		tbl_hoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() >= 2) {
					int row = tbl_hoaDon.getSelectedRow();
					entities.HoaDon hd = dsHD.get(row);
					hoaDon = hd;
					ChiTietHoaDonPage cthd = new ChiTietHoaDonPage();

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
					cthd.lblThoiGian.setText("Thời gian tạo: " + cthd.hoaDon.getThoiGianLapHoaDon().format(formatter));
					cthd.txtMaHD.setText(hd.getMaHoaDon());

					cthd.txtTongTien.setText(Formater.decimalFormat(hd.getTongTien()));

					cthd.txtTienDua.setText(Formater.decimalFormat(hd.getTienKhachTra()));
					cthd.txtTienThua.setText(Formater.decimalFormat(hd.tinhTienThua()));
					cthd.txtGhiChu.setText(hd.getGhiChu());

					cthd.loadCTHD();

					AddContent.addContent(cthd);
				}
			}
		});
	}

	private void addToTable() {
		model_hoaDon.setRowCount(0);
		int stt = 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
		for (entities.HoaDon hd : dsHD) {
			String time = hd.getThoiGianLapHoaDon().format(formatter);
			model_hoaDon.addRow(new Object[] { stt++, hd.getMaHoaDon(), time, Formater.decimalFormat(hd.getTongTien()),
					hd.getGhiChu() });
		}
		txtTongSoHoaDon.setText(dsHD.size() + "");
	}

	private void phimTat() {
		// Tạo một Action và gán chức năng khi nhấn phím

		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timTheoTuKhoa1.requestFocus();
				timTheoTuKhoa1.selectAll();
			}
		};

		// Gắn hành động với phím tắt
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),
				"performF4Action");
		this.getActionMap().put("performF4Action", action);
	}

	private void getHDbyMa() {
		HoaDon hd = new HoaDonDao().getHDbyMa(timTheoTuKhoa1.getText());
		if (hd != null) {
			if (dsHD != null)
				dsHD.clear();
			dsHD.add(hd);
			addToTable();
			timTheoTuKhoa1.requestFocus();
			timTheoTuKhoa1.selectAll();
		} else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn");
			timTheoTuKhoa1.requestFocus();
			timTheoTuKhoa1.selectAll();
		}
	}

	private void getFromTo() {
		if (dsHD != null)
			dsHD.clear();

		dsHD = new HoaDonDao().getHoaDonFromTo(
				dpTuNgay.getDate() == null ? LocalDate.of(2024, 1, 1) : dpTuNgay.getDate(),
				dbDenNgay.getDate() == null ? LocalDate.now() : dbDenNgay.getDate());
		if (dsHD != null) {
			addToTable();
		}
	}

	private void getAll() {
		// TODO Auto-generated method stub
		if (dsHD != null)
			dsHD.clear();

		dsHD = new HoaDonDao().getHoaDonFromTo(LocalDate.of(2024, 1, 1), LocalDate.now());
		if (dsHD != null) {
			addToTable();
		}
	}

	private void addTableHoaDon() {
		// TODO Auto-generated method stub
		String[] colNames = { "STT", "Mã hóa đơn", "Thời gian lập hóa đơn", "Tổng tiền", "Ghi chú" };

		model_hoaDon = new DefaultTableModel(colNames, 0);
		tbl_hoaDon = new JTable(model_hoaDon);
		JScrollPane js_tableHangHoa = new JScrollPane(tbl_hoaDon);

		if (tbl_hoaDon.getColumnModel().getColumnCount() > 0) {
			tbl_hoaDon.getColumnModel().getColumn(0).setResizable(false);
			tbl_hoaDon.getColumnModel().getColumn(1).setResizable(false);
			tbl_hoaDon.getColumnModel().getColumn(2).setResizable(false);
			tbl_hoaDon.getColumnModel().getColumn(3).setResizable(false);
			tbl_hoaDon.getColumnModel().getColumn(4).setResizable(false);
		}

		JTableHeader headerTable = tbl_hoaDon.getTableHeader();
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));
		tbl_hoaDon.setRowHeight(40);
		FormatJtable.setCellEditable(tbl_hoaDon);
		pnlContain.add(js_tableHangHoa, BorderLayout.CENTER);
		tbl_hoaDon.getColumnModel().getColumn(0).setPreferredWidth(5);

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		pnlHeader = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		lblLocTheoThoiGian = new javax.swing.JLabel();
		cbLocTheoThoiGian = new javax.swing.JComboBox<>();
		jPanel3 = new javax.swing.JPanel();
		dpTuNgay = new com.github.lgooddatepicker.components.DatePicker();
		lblTuNgay = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		lblDenNgay = new javax.swing.JLabel();
		dbDenNgay = new com.github.lgooddatepicker.components.DatePicker();
		jPanel6 = new javax.swing.JPanel();
		lblTuKhoa = new javax.swing.JLabel();
		timTheoTuKhoa1 = new sampleUi.TimTheoTuKhoa();
		jPanel9 = new javax.swing.JPanel();
		btnTimKiem = new javax.swing.JButton();
		pnlCenter = new javax.swing.JPanel();
		pnlContain = new javax.swing.JPanel();
		pnlContainHeader = new javax.swing.JPanel();
		lblTongHoaDon = new javax.swing.JLabel();
		txtTongSoHoaDon = new javax.swing.JTextField();

		setLayout(new java.awt.BorderLayout());

		pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
		pnlHeader.setPreferredSize(new java.awt.Dimension(1920, 100));

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setPreferredSize(new java.awt.Dimension(145, 100));

		lblLocTheoThoiGian.setText("Lọc theo thời gian");
		lblLocTheoThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

		cbLocTheoThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Hôm qua",
				"7 ngày trước", "Tháng này", "Tháng trước", "Năm này", "Năm trước", "Tất cả" }));
		cbLocTheoThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		cbLocTheoThoiGian.setPreferredSize(new java.awt.Dimension(115, 25));
		cbLocTheoThoiGian.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbLocTheoThoiGianActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblLocTheoThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				.addGroup(jPanel2Layout.createSequentialGroup().addComponent(cbLocTheoThoiGian,
						javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cbLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)));

		pnlHeader.add(jPanel2);

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setPreferredSize(new java.awt.Dimension(195, 100));

		dpTuNgay.setPreferredSize(new java.awt.Dimension(143, 25));

		lblTuNgay.setText("Từ ngày");
		lblTuNgay.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(dpTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 184,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTuNgay))
						.addGap(0, 0, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(24, 24, 24).addComponent(lblTuNgay)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(dpTuNgay,
								javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));

		pnlHeader.add(jPanel3);

		jPanel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setPreferredSize(new java.awt.Dimension(195, 100));

		lblDenNgay.setText("Đến ngày");
		lblDenNgay.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

		dbDenNgay.setPreferredSize(new java.awt.Dimension(143, 25));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblDenNgay).addComponent(dbDenNgay,
										javax.swing.GroupLayout.PREFERRED_SIZE, 186,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(0, 9, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout
						.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE).addComponent(lblDenNgay)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(dbDenNgay,
								javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)));

		pnlHeader.add(jPanel4);

		jPanel6.setBackground(new java.awt.Color(255, 255, 255));
		jPanel6.setPreferredSize(new java.awt.Dimension(400, 100));

		lblTuKhoa.setText("Từ khoá tìm kiếm");
		lblTuKhoa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

		timTheoTuKhoa1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
		timTheoTuKhoa1.setPreferredSize(new java.awt.Dimension(350, 23));
		timTheoTuKhoa1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				timTheoTuKhoa1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addGap(30, 30, 30)
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblTuKhoa).addComponent(timTheoTuKhoa1,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addGap(23, 23, 23).addComponent(lblTuKhoa)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(timTheoTuKhoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		pnlHeader.add(jPanel6);

		jPanel9.setBackground(new java.awt.Color(255, 255, 255));

		btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-find-24.png"))); // NOI18N
		btnTimKiem.setText("Tìm kiếm");
		btnTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
		btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTimKiemActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel9Layout.createSequentialGroup().addComponent(btnTimKiem).addGap(0, 0, Short.MAX_VALUE)));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
						.addContainerGap(47, Short.MAX_VALUE).addComponent(btnTimKiem).addGap(22, 22, 22)));

		pnlHeader.add(jPanel9);

		add(pnlHeader, java.awt.BorderLayout.PAGE_START);

		pnlCenter.setBackground(new java.awt.Color(255, 255, 255));

		pnlContain.setBackground(new java.awt.Color(255, 255, 255));
		pnlContain.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hoá đơn  ",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Times New Roman", 1, 18))); // NOI18N
		pnlContain.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		pnlContain.setPreferredSize(new java.awt.Dimension(1920, 651));
		pnlContain.setLayout(new java.awt.BorderLayout());

		pnlContainHeader.setBackground(new java.awt.Color(255, 255, 255));
		pnlContainHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5));

		lblTongHoaDon.setText("Tổng số hoá đơn:");
		lblTongHoaDon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		pnlContainHeader.add(lblTongHoaDon);

		txtTongSoHoaDon.setEditable(false);
		txtTongSoHoaDon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		txtTongSoHoaDon.setEnabled(false);
		txtTongSoHoaDon.setPreferredSize(new java.awt.Dimension(100, 23));
		pnlContainHeader.add(txtTongSoHoaDon);

		pnlContain.add(pnlContainHeader, java.awt.BorderLayout.PAGE_START);

		javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
		pnlCenter.setLayout(pnlCenterLayout);
		pnlCenterLayout
				.setHorizontalGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(pnlContain, javax.swing.GroupLayout.DEFAULT_SIZE, 2768, Short.MAX_VALUE));
		pnlCenterLayout.setVerticalGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnlContain, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE));

		add(pnlCenter, java.awt.BorderLayout.CENTER);
	}// </editor-fold>//GEN-END:initComponents
		// Ham tao cac item cho combo box

	public static class tuyChon extends JComboBox {
		public tuyChon() {
			addItem("Xem chi tiết hóa đơn");
			addItem("Tạo phiếu trả hàng");
		}
	}

	private void timTheoTuKhoa1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_timTheoTuKhoa1ActionPerformed
		// TODO add your handling code here:
		if (!timTheoTuKhoa1.getText().equalsIgnoreCase(""))
			getHDbyMa();
	}// GEN-LAST:event_timTheoTuKhoa1ActionPerformed

	private void cbLocTheoThoiGianActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbLocTheoThoiGianActionPerformed
		// TODO add your handling code here:
		int i = cbLocTheoThoiGian.getSelectedIndex();
		LocalDate now = LocalDate.now();
		// Lấy ngày đầu tháng
		LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
		// Lấy ngày bắt đầu của tháng trước
		LocalDate firstDayOfLastMonth = now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
		// Lấy ngày kết thúc của tháng trước
		LocalDate lastDayOfLastMonth = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		// Lấy ngày bắt đầu của năm trước
		LocalDate firstDayOfLastYear = now.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
		// Lấy ngày kết thúc của năm trước
		LocalDate lastDayOfLastYear = now.minusYears(1).with(TemporalAdjusters.lastDayOfYear());
		// Lấy ngày bắt đầu của năm nay
		LocalDate firstDayOfYear = now.with(TemporalAdjusters.firstDayOfYear());

		if (i == 0) {
			dpTuNgay.setDate(now);
			dbDenNgay.setDate(now);
		} else if (i == 1) {
			dpTuNgay.setDate(now.minusDays(1));
			dbDenNgay.setDate(now.minusDays(1));
		} else if (i == 2) {
			dpTuNgay.setDate(now.minusDays(7));
			dbDenNgay.setDate(now);
		} else if (i == 3) {
			dpTuNgay.setDate(firstDayOfMonth);
			dbDenNgay.setDate(now);
		} else if (i == 4) {
			dpTuNgay.setDate(firstDayOfLastMonth);
			dbDenNgay.setDate(lastDayOfLastMonth);
		} else if (i == 5) {
			dpTuNgay.setDate(firstDayOfYear);
			dbDenNgay.setDate(now);
		} else if (i == 6) {
			dpTuNgay.setDate(firstDayOfLastYear);
			dbDenNgay.setDate(lastDayOfLastYear);
		} else {
			dpTuNgay.setText("");
			dbDenNgay.setText("");
		}

	}// GEN-LAST:event_cbLocTheoThoiGianActionPerformed

	private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTimKiemActionPerformed
		// TODO add your handling code here:
		if (!timTheoTuKhoa1.getText().equalsIgnoreCase("")) {
			getHDbyMa();
		} else {
			try {
				dpTuNgay.getDate();
				dbDenNgay.getDate();
				getFromTo();
			} catch (Exception e) {
				// TODO: handle exception
				getAll();
			}
		}
	}// GEN-LAST:event_btnTimKiemActionPerformed

	public static HoaDon hoaDon;
	private List<entities.HoaDon> dsHD = null;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnTimKiem;
	private javax.swing.JComboBox<String> cbLocTheoThoiGian;
	private com.github.lgooddatepicker.components.DatePicker dbDenNgay;
	private com.github.lgooddatepicker.components.DatePicker dpTuNgay;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JLabel lblDenNgay;
	private javax.swing.JLabel lblLocTheoThoiGian;
	private javax.swing.JLabel lblTongHoaDon;
	private javax.swing.JLabel lblTuKhoa;
	private javax.swing.JLabel lblTuNgay;
	private javax.swing.JPanel pnlCenter;
	private javax.swing.JPanel pnlContain;
	private javax.swing.JPanel pnlContainHeader;
	private javax.swing.JPanel pnlHeader;
	private sampleUi.TimTheoTuKhoa timTheoTuKhoa1;
	private javax.swing.JTextField txtTongSoHoaDon;
	// End of variables declaration//GEN-END:variables
}
