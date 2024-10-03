package components;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formater {
	public static String decimalFormat(double number) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(number);
        return formattedNumber;
	}
	public static String dateTimeFormater(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm:ss");
		return time.format(formatter);
	}
	public static String dateFormater(LocalDate time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return time.format(formatter);
	}
    public static double roundToNearest500(double number) {
        // Chia số ban đầu cho 500
    	double quotient = number / 500;
        // Làm tròn kết quả chia
    	double roundedQuotient = Math.round(quotient);
        // Nhân kết quả tròn với 500
    	double roundedNumber = roundedQuotient * 500;
        return roundedNumber;
    }
}
