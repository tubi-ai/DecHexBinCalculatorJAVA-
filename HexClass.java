package com.company;

import java.awt.event.ActionEvent;


public class HexClass extends Calculator {
    public void hexEnable() {
        onRadioButton.setEnabled(false);
        hexRadioButton.setEnabled(false);
        binRadioButton.setEnabled(true);
        decRadioButton.setEnabled(true);
        offRadioButton.setEnabled(true);
        textField.setEnabled(true);
        label.setEnabled(true);
        setButtonState(true, buttonSquare, buttonMinus, buttonOr, 
                buttonSeven, buttonEight, buttonNine, buttonMul, buttonAnd, buttonFour, buttonFive, buttonSix, 
                buttonPlus, buttonXor, buttonOne, buttonTwo, buttonThree, buttonDiv, buttonNot, buttonA, 
                buttonB, buttonC, buttonSLeft, buttonSRight, buttonD, buttonE, buttonF, buttonClear, buttonEqual, 
                buttonZero, buttonDot, buttonDelete);
        setButtonState(false,buttonSqrt, buttonReciprocal);
    } 

    public static int hexToDec(String hex) {
        // Parse a hexadecimal string and return the corresponding integer value
        return Integer.parseInt(hex, 16);
    }
    public static String decToHex(double dec) {
        // Handle integer part
        int integerPart = (int) dec;
        String hexInteger = Integer.toHexString(integerPart).toUpperCase();
        
        // Handle fractional part
        double fractionalPart = dec - integerPart;
        if (fractionalPart == 0) {
            return hexInteger;
        }
        
        // Convert fractional part to hex with precision
        StringBuilder hexFraction = new StringBuilder();
        hexFraction.append(".");
        
        for (int i = 0; i < 8 && fractionalPart > 0; i++) {  // 8 digits precision
            fractionalPart *= 16;
            int digit = (int) fractionalPart;
            hexFraction.append(Integer.toHexString(digit).toUpperCase());
            fractionalPart -= digit;
        }
        
        return hexInteger + hexFraction.toString();
    }
    public static double hexToDecDouble(String hex) {
        try {
            // Handle hex strings that represent floating point numbers
            if (hex.contains(".")) {
                String[] parts = hex.split("\\.");
                double integerPart = Integer.parseInt(parts[0], 16);
                double fractionalPart = 0;
                if (parts.length > 1) {
                    fractionalPart = Integer.parseInt(parts[1], 16) / Math.pow(16, parts[1].length());
                }
                return integerPart + fractionalPart;
            } else {
                return Integer.parseInt(hex, 16);
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    //Overriding actionPerformed() method
    //@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == onRadioButton) {
            enable();//Calling enable() function
        }
        if (source == hexRadioButton)
            hexEnable();
        else if (source == binRadioButton) {
            if (hexNum != null) {
                String bin = Integer.toBinaryString(Integer.parseInt(hexNum, 16));
                textField.setText(bin);
                //textField.setText(BinClass.parseBin((int)answer));
            }  new BinClass().binEnable();
        }
        else if(source == decRadioButton)
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
        } 
        // Number and letter buttons
        else if (source == buttonZero || source == buttonOne || source == buttonTwo ||
                source == buttonThree || source == buttonFour || source == buttonFive ||
                source == buttonSix || source == buttonSeven || source == buttonEight ||
                source == buttonNine || source == buttonA || source == buttonB ||
                source == buttonC || source == buttonD || source == buttonE || source == buttonF) {
            
            String digit = source == buttonZero ? "0" :
                           source == buttonOne ? "1" :
                           source == buttonTwo ? "2" :
                           source == buttonThree ? "3" :
                           source == buttonFour ? "4" :
                           source == buttonFive ? "5" :
                           source == buttonSix ? "6" :
                           source == buttonSeven ? "7" :
                           source == buttonEight ? "8" :
                           source == buttonNine ? "9" :
                           source == buttonA ? "A" :
                           source == buttonB ? "B" :
                           source == buttonC ? "C" :
                           source == buttonD ? "D" :
                           source == buttonE ? "E" : "F";
                         
            if (!(textField.getText().equals("0") && digit.equals("0"))) {
                textField.setText(textField.getText() + digit);
                hexNum += digit;
            }
        }
        
        // Decimal point
        else if (source == buttonDot) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
                hexNum += ".";
            }
        }
        
        // Basic arithmetic operations
        else if (source == buttonPlus || source == buttonMinus || 
                source == buttonMul || source == buttonDiv) {
            String str = textField.getText();
            number = hexToDecDouble(textField.getText());
            textField.setText("");
            String op = source == buttonPlus ? "+" :
                       source == buttonMinus ? "-" :
                       source == buttonMul ? "X" : "/";
            label.setText(str + op);
            calculation = source == buttonPlus ? 1 :
                         source == buttonMinus ? 2 :
                         source == buttonMul ? 3 : 4;
        }
        
        // Square root
        else if (source == buttonSqrt) {
            double num = hexToDecDouble(textField.getText());
            textField.setText(decToHex(Math.sqrt(num)));
        }
        else if (source == buttonNot) {
            number = hexToDec (textField.getText());
            int not = ~(int) (number);
            hexNum = Integer.toHexString(not);
            textField.setText(hexNum);
        } 
        else if (source == buttonAnd || source == buttonOr || 
                source == buttonXor || source == buttonSLeft || 
                source == buttonSRight) {
           String str = textField.getText();
           number = (int)hexToDecDouble(textField.getText());  // Convert to int for bitwise ops
           textField.setText("");
           String op = source == buttonAnd ? "&" :
                      source == buttonOr ? "|" :
                      source == buttonXor ? "^" :
                      source == buttonSLeft ? "<<" : ">>";
           label.setText(str + op);
           calculation = source == buttonAnd ? 5 :
                        source == buttonOr ? 6 :
                        source == buttonXor ? 7 :
                        source == buttonSLeft ? 8 : 9;
       }
        else if (source == buttonEqual) {
        	int input = hexToDec(textField.getText());
            //Setting functionality for equal(=) button
            switch (calculation) {
                case 1:
                    answer = number + input;  break;
                case 2:
                	answer = number - input;  break;
                case 3:
                	answer = number * input;  break;
                case 4:
                	answer = number / input;  break;
                case 5:
                	answer = (int)number & (int)input;  break;
                case 6:
                	answer = (int)number | (int)input;  break;
                case 7:
                	answer = (int)number ^ (int)input;  break;
                case 8:
                	answer = (int)number << (int)input;  break;
                case 9:
                	answer = (int)number >> (int)input;  break;
            }
            textField.setText(decToHex(answer).toUpperCase());
            label.setText("");
        }
    }
}