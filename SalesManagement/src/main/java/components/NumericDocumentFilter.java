package components;

import javax.swing.*;
import javax.swing.text.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumericDocumentFilter extends DocumentFilter {
    private final NumberFormat numberFormat;

    public NumericDocumentFilter() {
        numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setGroupingUsed(true);
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) {
            return;
        }
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
        if (isNumeric(newText)) {
            super.insertString(fb, offset, string, attr);
            reformat(fb);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            return;
        }
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
        if (isNumeric(newText)) {
            super.replace(fb, offset, length, text, attrs);
            reformat(fb);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
        reformat(fb);
    }

    private boolean isNumeric(String text) {
        if (text.isEmpty())
            return true;
        try {
            numberFormat.parse(text.replace(",", ""));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void reformat(FilterBypass fb) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        if (currentText.isEmpty()) {
            return;
        }
        try {
            Number number = numberFormat.parse(currentText.replace(",", ""));
            String formattedText = numberFormat.format(number);
            fb.remove(0, fb.getDocument().getLength());
            fb.insertString(0, formattedText, null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Numeric JTextField Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        JTextField numericField = new JTextField();
        ((AbstractDocument) numericField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        frame.getContentPane().add(numericField);
        frame.setVisible(true);
    }
}
