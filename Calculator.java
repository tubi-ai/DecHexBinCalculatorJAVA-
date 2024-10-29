package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//implementing ActionListener interface
public class Calculator  implements ActionListener {
	//Creating variables for our calculations
    double number,answer;
    int calculation,x1,y1;
    static String hexNum = null, binNum = null, decNum = null;
    JFrame frame;
    JLabel label = new JLabel();
    JTextField textField = new JTextField();
    JRadioButton onRadioButton = new JRadioButton("on");
    JRadioButton offRadioButton = new JRadioButton("off");
    JRadioButton hexRadioButton = new JRadioButton("Hex");
    JRadioButton binRadioButton = new JRadioButton("Bin");
    JRadioButton decRadioButton = new JRadioButton("Dec");
    JButton buttonZero = new JButton("0");
    JButton buttonOne = new JButton("1");
    JButton buttonTwo = new JButton("2");
    JButton buttonThree = new JButton("3");
    JButton buttonFour = new JButton("4");
    JButton buttonFive = new JButton("5");
    JButton buttonSix = new JButton("6");
    JButton buttonSeven = new JButton("7");
    JButton buttonEight = new JButton("8");
    JButton buttonNine = new JButton("9");
    JButton buttonDot = new JButton(".");
    JButton buttonClear = new JButton("Clear");
    JButton buttonDelete = new JButton("DEL");
    JButton buttonEqual = new JButton("=");
    JButton buttonMul = new JButton("x");
    JButton buttonDiv = new JButton("/");
    JButton buttonPlus = new JButton("+");
    JButton buttonMinus = new JButton("-");
    JButton buttonSquare = new JButton("x\u00B2");
    JButton buttonReciprocal = new JButton("1/x");
    JButton buttonSqrt = new JButton("\u221A");
    JButton buttonA = new JButton("A");
    JButton buttonB = new JButton("B");
    JButton buttonC = new JButton("C");
    JButton buttonD = new JButton("D");
    JButton buttonE = new JButton("E");
    JButton buttonF = new JButton("F");
    JButton buttonOr = new JButton("Or");
    JButton buttonAnd = new JButton("And");
    JButton buttonXor = new JButton("Xor");
    JButton buttonNot = new JButton("Not");
    JButton buttonSLeft = new JButton("S_Left");
    JButton buttonSRight = new JButton("S_Right");
    JButton[] allButtons = {buttonSqrt, buttonSquare, buttonReciprocal, buttonMinus, buttonOr, 
            buttonSeven, buttonEight, buttonNine, buttonMul, buttonAnd, buttonFour, buttonFive, buttonSix, 
            buttonPlus, buttonXor, buttonOne, buttonTwo, buttonThree, buttonDiv, buttonNot, buttonA, 
            buttonB, buttonC, buttonSLeft, buttonSRight, buttonD, buttonE, buttonF, buttonClear, buttonEqual, 
            buttonZero, buttonDot, buttonDelete };
    
    Calculator() {
        prepareGUI();
        addComponents();
        offRadioButton.setSelected(true);
        disable();
    }
    public void prepareGUI() {
    	frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(440, 610);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.black);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void addComponents() {
    	label.setBounds(250, 0, 50, 50);
        label.setForeground(Color.white);
        frame.add(label);

        textField.setBounds(10, 40, 270, 40);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(textField);
        
        int x = 10, y = 95;
        JRadioButton[] funcButtons = {onRadioButton, offRadioButton, 
        		hexRadioButton, binRadioButton, decRadioButton};
     // Add RadioButtons Buttons
        for (int j = 0; j < funcButtons.length; j++) {
        	funcButtons[j].setBounds(x, y, 60, 40);
        	//funcButtons[j].setSelected(true);
        	funcButtons[j].addActionListener(this);
        	funcButtons[j].setFont(new Font("Arial", Font.BOLD, 14));
        	funcButtons[j].setBackground(Color.black);
        	funcButtons[j].setForeground(Color.white);
            frame.add(funcButtons[j]);
            x+=70;
        }
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(onRadioButton);
        buttonGroup.add(offRadioButton);

        ButtonGroup bdx = new ButtonGroup();
        bdx.add(hexRadioButton);
        bdx.add(binRadioButton);
        bdx.add(decRadioButton);
        
        // Add Numeric Buttons
        for (int i = 0; i < allButtons.length; i++) {
        	x=10; y=170;
        	if (i==29) {buttonEqual.setBounds(290, 470, 60, 100);}
        	else if (i==30) {buttonZero.setBounds(10, 530, 130, 40);}
        	else if (i==31) {buttonDot.setBounds(150, 530, 60, 40);}
        	else if(i==32) {buttonDelete.setBounds(220, 530, 60, 40);}
        	else {
        		x1 = i%5;	y1 = i/5;
                x = x + (x1*70); y = y +(y1*60);// Move to the right
            allButtons[i].setBounds(x, y, 60, 40);}
            allButtons[i].addActionListener(this);
            allButtons[i].setBackground(Color.LIGHT_GRAY); 
            allButtons[i].setForeground(Color.BLACK); 
            frame.add(allButtons[i]);
            
        }
    }
    public void setButtonState(boolean enabled, JButton... buttons) {
        for (JButton button : buttons) {
            button.setEnabled(enabled);}
        }
    public void enable() {
        onRadioButton.setEnabled(false);
        hexRadioButton.setEnabled(true);
        binRadioButton.setEnabled(true);
        decRadioButton.setEnabled(true);
        offRadioButton.setEnabled(true);
        textField.setEnabled(true);
        setButtonState(true, allButtons);
        for (JButton button : allButtons) {
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
        }
    } 
    public void disable() {
    	onRadioButton.setEnabled(true);
        hexRadioButton.setEnabled(false);
        binRadioButton.setEnabled(false);
        decRadioButton.setEnabled(false);
        offRadioButton.setEnabled(false);
        textField.setText("");
        label.setText(" ");
        setButtonState(false, buttonSqrt, buttonSquare, buttonReciprocal, buttonMinus, buttonOr, 
                buttonSeven, buttonEight, buttonNine, buttonMul, buttonAnd, buttonFour, buttonFive, buttonSix, 
                buttonPlus, buttonXor, buttonOne, buttonTwo, buttonThree, buttonDiv, buttonNot, buttonA, 
                buttonB, buttonC, buttonSLeft, buttonSRight, buttonD, buttonE, buttonF, buttonClear, buttonEqual, 
                buttonZero, buttonDot, buttonDelete);
    } 
  //Overriding actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == onRadioButton) {
            enable();//Calling enable() function
        }
        if (source == hexRadioButton) {
            new HexClass().hexEnable();

        } else if (source == binRadioButton)
            new BinClass().binEnable();
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
        }
        else if (source == buttonZero) {
            if (textField.getText().equals("0")) {
                return;
            } else {
                textField.setText(textField.getText() + "0");
            }
        } else if (source == buttonOne) {
            textField.setText(textField.getText() + "1");
        } else if (source == buttonTwo) {
            textField.setText(textField.getText() + "2");
        } else if (source == buttonThree) {
            textField.setText(textField.getText() + "3");
        } else if (source == buttonFour) {
            textField.setText(textField.getText() + "4");
        } else if (source == buttonFive) {
            textField.setText(textField.getText() + "5");
        } else if (source == buttonSix) {
            textField.setText(textField.getText() + "6");
        } else if (source == buttonSeven) {
            textField.setText(textField.getText() + "7");
        } else if (source == buttonEight) {
            textField.setText(textField.getText() + "8");
        } else if (source == buttonNine) {
            textField.setText(textField.getText() + "9");
        } else if (source == buttonA) {
            textField.setText(textField.getText() + "A");
        } else if (source == buttonB) {
            textField.setText(textField.getText() + "B");
        } else if (source == buttonC) {
            textField.setText(textField.getText() + "C");
        } else if (source == buttonD) {
            textField.setText(textField.getText() + "D");
        } else if (source == buttonE) {
            textField.setText(textField.getText() + "E");
        } else if (source == buttonF) {
            textField.setText(textField.getText() + "F");
        } else if (source == buttonDot) {
            if (textField.getText().contains(".")) {
                return;
            } else {
                textField.setText(textField.getText() + ".");
            }
        } else if (source == buttonPlus) {
            String str = textField.getText();
            number = Double.parseDouble(textField.getText());
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
        } else if (source == buttonSqrt) {
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
            switch (calculation) {
                case 1:
                    answer = number + Double.parseDouble(textField.getText());
                    if (Double.toString(answer).endsWith(".0")) {
                        textField.setText(Double.toString(answer).replace(".0", ""));
                    } else {
                        textField.setText(Double.toString(answer));
                    }
                    label.setText("");
                    break;
                case 2:
                    answer = number - Double.parseDouble(textField.getText());
                    if (Double.toString(answer).endsWith(".0")) {
                        textField.setText(Double.toString(answer).replace(".0", ""));
                    } else {
                        textField.setText(Double.toString(answer));
                    }
                    label.setText("");
                    break;
                case 3:
                    answer = number * Double.parseDouble(textField.getText());
                    if (Double.toString(answer).endsWith(".0")) {
                        textField.setText(Double.toString(answer).replace(".0", ""));
                    } else {
                        textField.setText(Double.toString(answer));
                    }
                    label.setText("");
                    break;
                case 4:
                    answer = number / Double.parseDouble(textField.getText());
                    if (Double.toString(answer).endsWith(".0")) {
                        textField.setText(Double.toString(answer).replace(".0", ""));
                    } else {
                        textField.setText(Double.toString(answer));
                    }
                    label.setText("");
                    break;
            }
        }

    }

}