/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import components.ColorSample;
import components.FormatJtable;
import components.Formater;
import dao.ChiTietHoaDonDao;
import entities.ChiTietHoaDon;
import entities.HangHoa;

/**
 *
 * @author LENOVO
 */
public class ThongKeHangHoaPage extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeHangHoaPage
     */
    public ThongKeHangHoaPage() {
        initComponents();
        config();
    }

    private void config() {
		// TODO Auto-generated method stub
    	txtTongDoanhThu.setEditable(false);
    	cbLocTheoThoiGian.setSelectedIndex(3);
    	dpTuNgay.setDate(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
		dpDenNgay.setDate(LocalDate.now());
    	String[] headerTB = "STT,Mã hàng hóa,Tên hàng hóa,Đơn vị tính,Số lượng bán,Doanh thu, Lợi nhuận gộp".split(",");
    	tableModel = new DefaultTableModel(headerTB,0);
    	table.setModel(tableModel);
    	table.setRowHeight(35);
    	table.getColumnModel().getColumn(0).setPreferredWidth(10);
    	FormatJtable.setCellEditable(table);
    	FormatJtable.setFontJtable(table);
    	pieChartPanel = new ChartPanel(null);
    	barChartPanel = new ChartPanel(null);
    		
        pieChartPanel.setPreferredSize(new Dimension(400, 300));
        pieChartPanel.setBackground(Color.green); // Set background color for ChartPanel
        pnlRight.add(pieChartPanel);
        
        // Add Bar Chart
        barChartPanel.setPreferredSize(new Dimension(400, 300));
        barChartPanel.setBackground(Color.lightGray); // Set background color for ChartPanel
        pnlRight.add(barChartPanel);
        
        addTable();
	}
    private void addTable() {
    	dsNH.clear();
    	dsSL.clear();
    	tableModel.setRowCount(0);
    	LocalDate tuNgay = LocalDate.of(2024, 1, 1);
    	LocalDate denNgay = LocalDate.now();
    	try {
			dpTuNgay.getDate();
			dpDenNgay.getDate();
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
    	if(dpTuNgay.getDate()!=null)
    		tuNgay = dpTuNgay.getDate();
    	if(dpDenNgay.getDate()!=null)
    		denNgay = dpDenNgay.getDate();
    	
		// TODO Auto-generated method stub
		dsCTHD = new ChiTietHoaDonDao().getCTHDFromTo(tuNgay, denNgay);
		int stt = 1;

		for (ChiTietHoaDon ct : dsCTHD) {
			String nh = ct.getHangHoa().getNhomHang();
			tableModel.addRow(new Object[] {stt++,ct.getHangHoa().getMaHangHoa(),ct.getHangHoa().getTenHangHoa(),ct.getHangHoa().getDonViTinh(),Formater.decimalFormat(ct.getSoLuong()),Formater.decimalFormat(ct.getDonGia()),Formater.decimalFormat(ct.getLoiNhuan())});
		
			if(!dsNH.contains(nh)) {
				dsNH.add(nh);
				dsSL.add(ct.getSoLuong());
			}else	
				dsSL.set(dsNH.indexOf(nh), dsSL.get(dsNH.indexOf(nh))+ct.getSoLuong());
			
		}
		DefaultPieDataset pieDataset = createPieDataset();
        JFreeChart pieChart = createPieChart(pieDataset);
	    CategoryDataset barDataset = createCategoryDataset();
        JFreeChart barChart = createBarChart(barDataset);
        pieChartPanel.setChart(pieChart);
        barChartPanel.setChart(barChart);
        double tongTien = 0;
        double tongLoiNhuanGop = 0;
        for(int i=0;i<table.getRowCount();i++) {
        	tongTien+=Double.parseDouble(table.getValueAt(i, 5).toString().replaceAll(",", ""));
        	tongLoiNhuanGop+=Double.parseDouble(table.getValueAt(i, 6).toString().replaceAll(",", ""));
        }
        txtTongDoanhThu.setText(Formater.decimalFormat(tongTien));
        txtTongLoiNhuanGop.setText(Formater.decimalFormat(tongLoiNhuanGop));
	}

	private DefaultPieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int slKhac = 0;
        for(int i = 0;i<dsNH.size();i++) {
        	if(i<5)
        		dataset.setValue(dsNH.get(i)+" ", dsSL.get(i));
        	else
        		slKhac+=dsSL.get(i);
        }
        if(slKhac!=0)
        	dataset.setValue("Khác", slKhac);
        return dataset;
    }
    private JFreeChart createPieChart(DefaultPieDataset dataset) {
    	JFreeChart chart = ChartFactory.createPieChart(
                "Nhóm hàng bán chạy",
                dataset,
                true,
                true,
                false
        );
        chart.setBackgroundPaint(Color.white); // Set background color for the chart
        PiePlot plot = (PiePlot) chart.getPlot();
        
        plot.setBackgroundPaint(ColorSample.main); // Set background color for the plot
        return chart;
    }
    private CategoryDataset createCategoryDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0;i<5;i++) {
        	if(dsCTHD.size()<i+1)
        		return dataset;
        	dataset.addValue(dsCTHD.get(i).getDonGia(), dsCTHD.get(i).getHangHoa().getTenHangHoa(), "Top "+(i+1));
        }
        return dataset;
    }

    private JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Top 5 hàng hóa có doanh thu cao nhất",
                "Tên hàng hóa",
                "Doanh thu",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chart.setBackgroundPaint(Color.white); // Set background color for the chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(ColorSample.main); // Set background color for the plot
        return chart;
    }
	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        pnlHeader = new javax.swing.JPanel();
        cbLocTheoThoiGian = new javax.swing.JComboBox<>();
        lblLocTheoThoiGian = new javax.swing.JLabel();
        dpTuNgay = new com.github.lgooddatepicker.components.DatePicker();
        lblTuNgay = new javax.swing.JLabel();
        lblDenNgay = new javax.swing.JLabel();
        dpDenNgay = new com.github.lgooddatepicker.components.DatePicker();
        btnTimKiem = new javax.swing.JButton();
        txtTongDoanhThu = new javax.swing.JTextField();
        lblDenNgay1 = new javax.swing.JLabel();
        lblDenNgay2 = new javax.swing.JLabel();
        txtTongLoiNhuanGop = new javax.swing.JTextField();
        pnlRight = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1920, 920));
        setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Tên nhân viên", "Số lượng bán", "Tổng tiền", "Số lượng trả", "Tổng tiền trả", "Doanh thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1500, 80));

        cbLocTheoThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Hôm qua", "7 ngày trước", "Tháng này", "Tháng trước", "Năm này", "Năm trước", "Tất cả" }));
        cbLocTheoThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbLocTheoThoiGian.setPreferredSize(new java.awt.Dimension(115, 25));
        cbLocTheoThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocTheoThoiGianActionPerformed(evt);
            }
        });

        lblLocTheoThoiGian.setText("Lọc theo thời gian");
        lblLocTheoThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        dpTuNgay.setPreferredSize(new java.awt.Dimension(143, 25));

        lblTuNgay.setText("Từ ngày");
        lblTuNgay.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblDenNgay.setText("Đến ngày");
        lblDenNgay.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        dpDenNgay.setPreferredSize(new java.awt.Dimension(143, 25));

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-find-24.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtTongDoanhThu.setText("0");
        txtTongDoanhThu.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N

        lblDenNgay1.setText("Tổng doanh thu");
        lblDenNgay1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblDenNgay2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDenNgay2.setText("Tổng lợi nhuận gộp");

        txtTongLoiNhuanGop.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTongLoiNhuanGop.setText("0");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(cbLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dpTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTuNgay))
                .addGap(18, 18, 18)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(dpDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem))
                    .addComponent(lblDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongDoanhThu)
                    .addComponent(lblDenNgay1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDenNgay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongLoiNhuanGop))
                .addContainerGap(457, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocTheoThoiGian)
                    .addComponent(lblTuNgay)
                    .addComponent(lblDenNgay)
                    .addComponent(lblDenNgay1)
                    .addComponent(lblDenNgay2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongLoiNhuanGop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlRight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlRight.setPreferredSize(new java.awt.Dimension(500, 302));
        pnlRight.setLayout(new java.awt.GridLayout(2, 1));
        add(pnlRight, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void cbLocTheoThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocTheoThoiGianActionPerformed
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
        
    	if(i==0) {
    		dpTuNgay.setDate(now);
    		dpDenNgay.setDate(now);
    	}else if(i==1) {
    		dpTuNgay.setDate(now.minusDays(1));
    		dpDenNgay.setDate(now.minusDays(1));
    	}else if(i==2) {
    		dpTuNgay.setDate(now.minusDays(7));
    		dpDenNgay.setDate(now);
    	}else if(i==3) {
    		dpTuNgay.setDate(firstDayOfMonth);
    		dpDenNgay.setDate(now);
    	}else if(i==4) {
    		dpTuNgay.setDate(firstDayOfLastMonth);
    		dpDenNgay.setDate(lastDayOfLastMonth);
    	}else if(i==5) {
    		dpTuNgay.setDate(firstDayOfYear);
    		dpDenNgay.setDate(now);
    	}else if(i==6) {
    		dpTuNgay.setDate(firstDayOfLastYear);
    		dpDenNgay.setDate(lastDayOfLastYear);
    	}else {
    		dpTuNgay.setText("");
    		dpDenNgay.setText("");
    	}
    }//GEN-LAST:event_cbLocTheoThoiGianActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    	addTable();
    }//GEN-LAST:event_btnTimKiemActionPerformed
    private ChartPanel barChartPanel;
    private ChartPanel pieChartPanel;
	private List<String> dsNH = new ArrayList<>();
	private List<Integer> dsSL = new ArrayList<>();
    private List<ChiTietHoaDon> dsCTHD = null;
    private DefaultTableModel tableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cbLocTheoThoiGian;
    private com.github.lgooddatepicker.components.DatePicker dpDenNgay;
    private com.github.lgooddatepicker.components.DatePicker dpTuNgay;
    private javax.swing.JLabel lblDenNgay;
    private javax.swing.JLabel lblDenNgay1;
    private javax.swing.JLabel lblDenNgay2;
    private javax.swing.JLabel lblLocTheoThoiGian;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtTongDoanhThu;
    private javax.swing.JTextField txtTongLoiNhuanGop;
    // End of variables declaration//GEN-END:variables
}
