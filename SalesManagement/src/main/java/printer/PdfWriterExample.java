package printer;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import components.Formater;
import entities.HoaDon;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.table.TableModel;

public class PdfWriterExample {
	public static void writePdf(TableModel tb, HoaDon hd) {
		try {
            // Tạo một đối tượng Document
            Document document = new Document();
            
            document.setMargins(5, document.topMargin(), document.rightMargin(), document.bottomMargin());

            
            // Tạo một đối tượng PdfWriter với đường dẫn đến file PDF
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("data/latestBill.pdf"));

            // Mở tài liệu
            document.open();
            BaseFont baseFont = BaseFont.createFont("font/arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontHeader = new Font(baseFont, 14, Font.BOLD);
            Font fontHeader2 = new Font(baseFont, 10, Font.BOLD);
            Font fontContent = new Font(baseFont, 10);
            
            String imagePath = "src/main/java/icon/logoStore_small.png"; 
            // Tạo đối tượng Image từ đường dẫn hình ảnh
            Image image1 = Image.getInstance(imagePath);
            //flag
            image1.setIndentationLeft(70);
            // Chèn hình ảnh vào tài liệu PDF
            document.add(image1);
            
            Paragraph p1 = new Paragraph("PHIẾU THANH TOÁN",fontHeader);
            p1.setIndentationLeft(35);
            document.add(p1);
            document.add(new Paragraph(" "));
            Paragraph p11 = new Paragraph("Số HD:",fontContent);
            p11.add(Chunk.TABBING);

            p11.add(hd.getMaHoaDon());
            document.add(p11);
            Paragraph p12 = new Paragraph("Ngày:",fontContent);
            p12.add(Chunk.TABBING);

            LocalDateTime now = hd.getThoiGianLapHoaDon();
            
            // Định dạng thời gian
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            
            // Chuyển đổi LocalDateTime thành chuỗi theo định dạng mong muốn
            String formattedDateTime = now.format(formatter);
            
            p12.add(formattedDateTime);
            document.add(p12);
            Paragraph p13 = new Paragraph("NV:",fontContent);
            p13.add(Chunk.TABBING);
           
            p13.add("Chị Nị");
            
            document.add(p13);
             	
            Paragraph p14 = new Paragraph("KH:",fontContent);
            p14.add(Chunk.TABBING);
            p14.add("Vãng lai");
            document.add(p14);
			
            
            
            
            
            document.add(new Paragraph("---------------------------------------------------"));
            
            Paragraph p2 = new Paragraph("   SL",fontHeader2);
            p2.add(Chunk.TABBING);
            p2.add("Giá bán");
            p2.add(Chunk.TABBING);
            p2.add(Chunk.TABBING);
            p2.add(Chunk.TABBING);
            p2.add("T.Tiền");
            document.add(p2);
            
            Paragraph p21;
            Paragraph p22;
            for(int i=0;i<tb.getRowCount();i++) {
            	p21 = new Paragraph(tb.getValueAt(i, 0).toString()+" - "+tb.getValueAt(i, 1).toString(),fontContent);
                document.add(p21);
                p22 = new Paragraph("   "+tb.getValueAt(i, 2),fontContent);
                p22.add(Chunk.TABBING);
                p22.add(Formater.decimalFormat(Double.parseDouble(tb.getValueAt(i, 3).toString().replaceAll(",", ""))));
                p22.add(Chunk.TABBING);
                p22.add(Chunk.TABBING);
                p22.add(Chunk.TABBING);
                p22.add(tb.getValueAt(i, 4)+"");
                document.add(p22);
            }
            
            
            
            
            
            
            
            document.add(new Paragraph("---------------------------------------------------"));
            Paragraph p3 = new Paragraph("Tổng tiền:",fontContent);
            p3.add(Chunk.TABBING);
            p3.add(Chunk.TABBING);
            p3.add(Chunk.TABBING);
            p3.add(Formater.decimalFormat(hd.getTongTien()));
            document.add(p3);
            Paragraph p4 = new Paragraph(" ",fontContent);
            p4.add(Chunk.TABBING);
            p4.add(Chunk.TABBING);
            p4.add(Chunk.TABBING);
            document.add(p4);
            Paragraph p5 = new Paragraph("Thanh toán (đã làm tròn):",fontHeader2);
            p5.add(Chunk.TABBING);
            p5.add(Formater.decimalFormat(hd.getTongTien()));
            document.add(p5);
            document.add(new Paragraph("---------------------------------------------------"));
            Paragraph p6 = new Paragraph("Tiền mặt:",fontContent);
            p6.add(Chunk.TABBING);
            p6.add(Chunk.TABBING);
            p6.add(Chunk.TABBING);
            p6.add(Formater.decimalFormat(hd.getTienKhachTra()));
            document.add(p6);
            Paragraph p7 = new Paragraph("Tiền thối lại:",fontContent);
            p7.add(Chunk.TABBING);
            p7.add(Chunk.TABBING);
            p7.add(Chunk.TABBING);
            p7.add(Formater.decimalFormat(hd.tinhTienThua()));
            document.add(p7);
            document.add(new Paragraph("---------------------------------------------------"));
            
            // Tạo mã vạch Code 128
            Barcode128 barcode = new Barcode128();
            barcode.setCode(hd.getMaHoaDon()); 

            // Chèn mã vạch vào tài liệu PDF
            com.itextpdf.text.Image image = barcode.createImageWithBarcode(writer.getDirectContent(), null, null);
            image.setIndentationLeft(50);
            document.add(image);
            Paragraph p8 = new Paragraph("\nXin cảm ơn quý khách!",fontContent);
            p8.setIndentationLeft(50);
            document.add(p8);
            // Đóng tài liệu
            document.close();

            System.out.println("PDF đã được tạo thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo PDF: " + e.getMessage());
        }
	}
}
