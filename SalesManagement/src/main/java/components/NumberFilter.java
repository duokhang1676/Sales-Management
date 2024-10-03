package components;

import javax.swing.*;
import javax.swing.text.*;
import java.text.*;

public class NumberFilter extends DocumentFilter {
    private static final String DECIMAL_FORMAT_PATTERN = "#,###";

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        StringBuilder builder = new StringBuilder();
        builder.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        builder.insert(offset, string);

        if (isNumeric(builder.toString())) {
        	String formatted = formatNumber(builder.toString());
        	super.replace(fb, 0, fb.getDocument().getLength(), formatted, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        StringBuilder builder = new StringBuilder();
        builder.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        builder.replace(offset, offset + length, text);

        String formatted = formatNumber(builder.toString());
        super.replace(fb, 0, fb.getDocument().getLength(), formatted, attrs);
        
    }
    private boolean isNumeric(String str) {
        return str.matches("[\\d]*");
    }
    private String formatNumber(String number) {
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
        try {
            return df.format(Long.parseLong(number.replaceAll(",", "")));
        } catch (NumberFormatException e) {
            return number;
        }
    }
}
