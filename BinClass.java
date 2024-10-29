package com.company;

import java.awt.event.ActionEvent;
public class BinClass extends Calculator{
    public void binEnable() {
        onRadioButton.setEnabled(false);
        hexRadioButton.setEnabled(true);
        binRadioButton.setEnabled(false);
        decRadioButton.setEnabled(true);
        offRadioButton.setEnabled(true);
        textField.setText("");
        label.setText(" ");
        setButtonState(true, buttonSquare, buttonMinus, buttonOr, 
                buttonMul, buttonAnd,  
                buttonPlus, buttonXor, buttonOne, buttonDiv, buttonNot,
                buttonSLeft, buttonSRight,buttonClear, buttonEqual, 
                buttonZero, buttonDot, buttonDelete);

        setButtonState(false, buttonSqrt, buttonReciprocal,
                buttonSeven, buttonEight, buttonNine, buttonFour, buttonFive, buttonSix, 
                buttonTwo, buttonThree, buttonA, 
                buttonB, buttonC, buttonD, buttonE, buttonF);
    }
    public static double binToDec(String binary) {
    	double decimal = 0;
        int pointIndex = binary.indexOf('.'); // Find the index of dot

        // Take integer part
        if (pointIndex == -1) pointIndex = binary.length(); // Nokta yoksa tamamı tam kısım
        for (int i = 0; i < pointIndex; i++) {
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, pointIndex - 1 - i);
            }
        }
        // Take fractional part
        for (int i = pointIndex + 1; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, -(i - pointIndex));
            }
        }
        return decimal;
    }
    public static String decToBin(double dec) {
    	// Process integer part
        long integralPart = (long) dec;
        double fractionalPart = dec - integralPart;

        String integralBinary = Long.toBinaryString(integralPart);

        // Process fractional part
        StringBuilder fractionalBinary = new StringBuilder();
        while (fractionalPart > 0 && fractionalBinary.length() < 10) { // En fazla 10 basamak
            fractionalPart *= 2;
            if (fractionalPart >= 1) {
                fractionalBinary.append("1");
                fractionalPart -= 1;
            } else {
                fractionalBinary.append("0");
            }
        }

        return fractionalBinary.length() > 0
                ? integralBinary + "." + fractionalBinary.toString()
                : integralBinary;
    } 
    public static String hexToBin(String hex) {
        // Convert the hexadecimal number to a binary number
        String bin = Integer.toBinaryString(Integer.parseInt(hex, 16));
        return bin;
    }
    //Overriding actionPerformed() method
    //@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == onRadioButton) {
            enable();//Calling enable() function
        }
        if (source == hexRadioButton)
            new HexClass();
        else if (source == binRadioButton)
            binEnable();
        else if (source == decRadioButton)
            new DecClass().decEnable();
        else if (source == offRadioButton) {
            disable();//Calling disable function
        } else if (source == buttonClear) {
            //Clearing texts of label and text field
            label.setText("");
            textField.setText("");
        } else if (source == buttonDelete) {
            //Setting functionality for delete button(backspace)
            int length = textField.getText().length();
            int number = length - 1;

            if (length > 0) {
                StringBuilder back = new StringBuilder(textField.getText());
                back.deleteCharAt(number);
                textField.setText(back.toString());

            }
            if (textField.getText().endsWith("")) {
                label.setText("");
            }
        } else if (source == buttonZero) {
            if (textField.getText().equals("0")) {
                return;
            } else {
                textField.setText(textField.getText() + "0");
            }
        } else if (source == buttonOne) {
            textField.setText(textField.getText() + "1");
        } else if (source == buttonDot) {
            if (textField.getText().contains(".")) {
                return;
            } else {
                textField.setText(textField.getText() + ".");
            }
        } else if (source == buttonPlus) {
            String str = textField.getText();
            number = binToDec(textField.getText());
            textField.setText("");
            label.setText(str + "+");
            calculation = 1;
        } else if (source == buttonMinus) {
            String str = textField.getText();
            number = Double.parseDouble(textField.getText());
            textField.setText("");
            label.setText(str + "-");
            calculation = 2;
        } else if (source == buttonMul) {
            String str = textField.getText();
            number = Double.parseDouble(textField.getText());
            textField.setText("");
            label.setText(str + "X");
            calculation = 3;
        } else if (source == buttonDiv) {
            String str = textField.getText();
            number = Double.parseDouble(textField.getText());
            textField.setText("");
            label.setText(str + "/");
            calculation = 4;
            
        } else if (source == buttonSquare) {
            String str = textField.getText();
            number = Double.parseDouble(textField.getText());
            double square = Math.pow(number, 2);
            String string = Double.toString(square);
            if (string.endsWith(".0")) {
                textField.setText(string.replace(".0", ""));
            } else {
                textField.setText(string);
            }
            label.setText("(sqr)" + str);
        } else if (source == buttonAnd) {
            String str = textField.getText();
            number = binToDec(textField.getText());
            textField.setText("");
            label.setText(str + "&");
            calculation = 5;

        } else if (source == buttonOr) {
            String str = textField.getText();
            number = binToDec(textField.getText());
            textField.setText("");
            label.setText(str + "|");
            calculation = 6;

        } else if (source == buttonXor) {
            String str = textField.getText();
            number = binToDec(textField.getText());
            textField.setText("");
            label.setText(str + "^");
            calculation = 7;

        } else if (source == buttonNot) {
            number = binToDec (textField.getText());
            int not = ~(int) (number);
            binNum = Integer.toBinaryString(not);
            textField.setText(binNum);

        } else if (source == buttonSLeft) {
            String str = textField.getText();
            number = binToDec(textField.getText());
            textField.setText("");
            label.setText(str + "<<");
            calculation = 8;

        } else if (source == buttonSRight) {
            String str = textField.getText();
            number = binToDec(textField.getText());
            textField.setText("");
            label.setText(str + ">>");
            calculation = 9;
        } 
        else if (source == buttonEqual) {
        	double input = binToDec(textField.getText());
            //Setting functionality for equal(=) button
            switch (calculation) {
            case 1:
            	answer = number + input; break;
            case 2:
            	answer = number - input; break;
            case 3:
            	answer = number * input; break;
            case 4:
            	answer = number / input; break;
            case 5:
                answer = (int)number & (int)input; break;
            case 6:
            	answer = (int)number | (int)input; break;
            case 7:
            	answer = (int)number ^ (int)input; break;
            case 8:
            	answer = (int)number << (int)input; break;
            case 9:
            	answer = (int)number >> (int)input; break;
            }
            textField.setText(decToBin(answer));
            label.setText("");
        }
    }
}
