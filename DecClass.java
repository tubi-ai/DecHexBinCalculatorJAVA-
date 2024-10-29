package com.company;

import java.awt.event.ActionEvent;

public class DecClass extends Calculator{
    public void decEnable() {
        onRadioButton.setEnabled(false);
        hexRadioButton.setEnabled(true);
        binRadioButton.setEnabled(true);
        decRadioButton.setEnabled(false);
        offRadioButton.setEnabled(true);
        textField.setEnabled(true);
        setButtonState(true, buttonSqrt, buttonReciprocal, buttonSquare, buttonMinus, 
                buttonSeven, buttonEight, buttonNine, buttonMul, buttonFour, buttonFive, buttonSix, 
                buttonPlus, buttonOne, buttonTwo, buttonThree, buttonDiv, buttonClear, buttonEqual, 
                buttonZero, buttonDot, buttonDelete);
        setButtonState(false,  buttonOr, buttonXor, buttonNot, buttonA, buttonAnd,
                buttonB, buttonC, buttonSLeft, buttonSRight, buttonD, buttonE, buttonF);
    }

    //Overriding actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == onRadioButton) {
            enable();//Calling enable() function
        }
        if (source == hexRadioButton)
            new HexClass().hexEnable();
        else if (source == binRadioButton)
            new BinClass().binEnable();
        else if (source == decRadioButton)
            decEnable();
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
                decNum += digit;
            }
        }
        
        // Decimal point
        else if (source == buttonDot) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
                decNum += ".";
            }
        }
     // Basic arithmetic operations
        else if (source == buttonPlus || source == buttonMinus || 
                source == buttonMul || source == buttonDiv) {
            String str = textField.getText();
            number = Double.parseDouble(textField.getText());
            textField.setText("");
            String op = source == buttonPlus ? "+" :
                       source == buttonMinus ? "-" :
                       source == buttonMul ? "X" : "/";
            		
            label.setText(str + op);
            calculation = source == buttonPlus ? 1 :
                         source == buttonMinus ? 2 :
                         source == buttonMul ? 3 : 4;
        }
        else if (source == buttonSqrt) {
            number = Double.parseDouble(textField.getText());
            Double sqrt = Math.sqrt(number);
            textField.setText(Double.toString(sqrt));

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
        } else if (source == buttonReciprocal) {
            number = Double.parseDouble(textField.getText());
            double reciprocal = 1 / number;
            String string = Double.toString(reciprocal);
            if (string.endsWith(".0")) {
                textField.setText(string.replace(".0", ""));
            } else {
                textField.setText(string);
            }
        } else if (source == buttonEqual) {
            //Setting functionality for equal(=) button
        	double input = Double.parseDouble(textField.getText());
            switch (calculation) {
                case 1:
                    answer = number + input; break;
                case 2:
                    answer = number - input; break;
                case 3:
                    answer = number * input; break;
                case 4:
                    answer = number / input; break;
            }
            if (Double.toString(answer).endsWith(".0")) {
                textField.setText(Double.toString(answer).replace(".0", ""));
            } else {
                textField.setText(Double.toString(answer));
            }
            label.setText("");
        }
    } 

   }
