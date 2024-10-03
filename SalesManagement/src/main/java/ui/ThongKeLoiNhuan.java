/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import dao.ChiTietPhieuNhapHangDao;
import dao.HoaDonDao;
import dao.PhieuNhapHangDao;
import dao.PhieuXuatTraDao;
import entities.ChiTietHoaDon;
import entities.ChiTietPhieuNhapHang;
import entities.HangHoa;

/**
 *
 * @author LENOVO
 */
public class ThongKeLoiNhuan extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeHangHoaPage
     */
    public ThongKeLoiNhuan() {
        initComponents();
        config();
    }

    private void config() {
		// TODO Auto-generated method stub
    	txtTongLoiNhuan.setEditable(false);
    	int currentYear = LocalDate.now().getYear();
    	cbLocTheoThoiGian.setSelectedItem(currentYear);
    	String[] headerTB = "Tháng,Doanh thu,Chi phí,Lợi nhuận".split(",");
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
    	tableModel.setRowCount(0);
    	
		int year = Integer.parseInt(cbLocTheoThoiGian.getSelectedItem().toString().trim());
		for (int i = 1;i<=12;i++) {
			YearMonth ym = YearMonth.of(year, i);
			double doanhThu = new HoaDonDao().getDoanhThuTheoThangNam(ym);
			double chiPhi = new PhieuNhapHangDao().getChiPhiTheoThangNam(ym);
			double tienTra = new PhieuXuatTraDao().getTienTraTheoThangNam(ym);
			chiPhi = chiPhi - tienTra;
			double loiNhuan = doanhThu - chiPhi;
			tableModel.addRow(new Object[] {i,Formater.decimalFormat(doanhThu),Formater.decimalFormat(chiPhi),Formater.decimalFormat(loiNhuan)});
	
		}
		DefaultPieDataset pieDataset = createPieDataset();
        JFreeChart pieChart = createPieChart(pieDataset);
	    CategoryDataset barDataset = createCategoryDataset();
        JFreeChart barChart = createBarChart(barDataset);
        pieChartPanel.setChart(pieChart);
        barChartPanel.setChart(barChart);
        double doanhThu = 0;
        double chiPhi = 0;
        double loiNhuan = 0;
        for(int i=0;i<table.getRowCount();i++) {
        	doanhThu +=Double.parseDouble(table.getValueAt(i, 1).toString().replaceAll(",", ""));
        	chiPhi +=Double.parseDouble(table.getValueAt(i, 2).toString().replaceAll(",", ""));
        	loiNhuan +=Double.parseDouble(table.getValueAt(i, 3).toString().replaceAll(",", ""));
        }
        txtTongDoanhThu.setText(Formater.decimalFormat(doanhThu));
        txtTongChiPhi.setText(Formater.decimalFormat(chiPhi));
        txtTongLoiNhuan.setText(Formater.decimalFormat(loiNhuan));
	}

	private DefaultPieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
      
        for(int i = 0;i<12;i++) {
        	dataset.setValue("Tháng"+(i+1),Double.parseDouble(tableModel.getValueAt(i, 1).toString().replace(",", "")));
        }
        return dataset;
    }
    private JFreeChart createPieChart(DefaultPieDataset dataset) {
    	JFreeChart chart = ChartFactory.createPieChart(
                "Doanh thu các tháng",
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
        List<Object[]> dsLN = new ArrayList<>(); // Sử dụng Object[] để dễ dàng truy cập
        for (int i = 0; i < 12; i++) {
            dsLN.add(new Object[]{
                Double.parseDouble(tableModel.getValueAt(i, 3).toString().replaceAll(",", "")), // Giá trị số thực
                i + 1 // Tháng
            });
        }

        // Sắp xếp danh sách theo thứ tự giảm dần
        dsLN.sort((o1, o2) -> Double.compare((Double) o2[0], (Double) o1[0]));

        // Lấy top 5 giá trị lớn nhất
        for (int i = 0; i < 5; i++) {
            Object[] entry = dsLN.get(i); // Lấy entry
            Double value = (Double) entry[0]; // Giá trị
            Integer month = (Integer) entry[1]; // Tháng
            dataset.addValue(value, "Tháng " + month, "Top " + (i + 1)); // Thêm vào dataset
        }

        return dataset; // Trả về dataset
    }


    private JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Top 5 tháng có lợi nhuận cao nhất",
                "Tháng",
                "Lợi nhuận",
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
        txtTongLoiNhuan = new javax.swing.JTextField();
        lblDenNgay1 = new javax.swing.JLabel();
        txtTongChiPhi = new javax.swing.JTextField();
        lblDenNgay2 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JTextField();
        lblDenNgay3 = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1920, 920));
        setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tháng", "Doanh thu", "Chi phí", "Lợi nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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

        cbLocTheoThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbLocTheoThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024\t", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040" }));
        cbLocTheoThoiGian.setPreferredSize(new java.awt.Dimension(115, 25));
        cbLocTheoThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocTheoThoiGianActionPerformed(evt);
            }
        });

        lblLocTheoThoiGian.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLocTheoThoiGian.setText("Lọc theo năm");

        txtTongLoiNhuan.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTongLoiNhuan.setText("0");

        lblDenNgay1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDenNgay1.setText("Tổng lợi nhuận");

        txtTongChiPhi.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTongChiPhi.setText("0");

        lblDenNgay2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDenNgay2.setText("Tổng chi phí");

        txtTongDoanhThu.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtTongDoanhThu.setText("0");

        lblDenNgay3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDenNgay3.setText("Tổng doanh thu");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbLocTheoThoiGian, 0, 134, Short.MAX_VALUE)
                    .addComponent(lblLocTheoThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(120, 120, 120)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongDoanhThu)
                    .addComponent(lblDenNgay3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongChiPhi)
                    .addComponent(lblDenNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongLoiNhuan)
                    .addComponent(lblDenNgay1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(503, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblDenNgay3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblDenNgay2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblDenNgay1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblLocTheoThoiGian)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlRight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlRight.setPreferredSize(new java.awt.Dimension(500, 302));
        pnlRight.setLayout(new java.awt.GridLayout(2, 1));
        add(pnlRight, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void cbLocTheoThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocTheoThoiGianActionPerformed
        // TODO add your handling code here:
    	addTable();
    }//GEN-LAST:event_cbLocTheoThoiGianActionPerformed
    private ChartPanel barChartPanel;
    private ChartPanel pieChartPanel;
    private List<ChiTietPhieuNhapHang> dsCTPNH = null;
    private DefaultTableModel tableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbLocTheoThoiGian;
    private javax.swing.JLabel lblDenNgay1;
    private javax.swing.JLabel lblDenNgay2;
    private javax.swing.JLabel lblDenNgay3;
    private javax.swing.JLabel lblLocTheoThoiGian;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtTongChiPhi;
    private javax.swing.JTextField txtTongDoanhThu;
    private javax.swing.JTextField txtTongLoiNhuan;
    // End of variables declaration//GEN-END:variables
}
