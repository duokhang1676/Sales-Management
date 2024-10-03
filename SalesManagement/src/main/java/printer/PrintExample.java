package printer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;


public class PrintExample {
    // Phương thức in nội dung của file PDF
 // Phương thức in nội dung của file PDF mà không hiển thị hộp thoại in
	
	 public static void printContent() {
	        PrinterJob job = PrinterJob.getPrinterJob();

	        try {
	            // Đọc file PDF
	            File file = new File("data/latestBill.pdf");
	            PDDocument document = PDDocument.load(file);

	            // Tạo một PDFPrintable từ file PDF với tỷ lệ phù hợp
	            PDFPrintable printable = new PDFPrintable(document, Scaling.ACTUAL_SIZE);

	            // Tạo một Printable tùy chỉnh để dịch chuyển nội dung
	            Printable shiftedPrintable = new Printable() {
	                @Override
	                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	                    Graphics2D g2d = (Graphics2D) graphics;
	                    g2d.translate(190, 0); // Dịch chuyển nội dung sang phải 100px
	                    return printable.print(g2d, pageFormat, pageIndex);
	                }
	            };

	            // Đặt Printable của job là Printable tùy chỉnh
	            job.setPrintable(shiftedPrintable);

	            // In mà không hiển thị hộp thoại in
	            job.print();

	            // Đóng document
	            document.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

}
