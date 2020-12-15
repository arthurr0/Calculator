package pl.arturekdev.objects;

import pl.arturekdev.enums.ButtonType;
import pl.arturekdev.enums.CalculatorOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Calculator implements ActionListener {

    private final JFrame frame;
    private final JTextField textField;
    private final JTextField contentField;
    private final List<Button> upperButtons = new ArrayList<>();
    private final List<Button> mainButtons = new ArrayList<>();

    private double numberOne, result;
    private CalculatorOperator operator;

    public Calculator() {
        frame = new JFrame("Kalkulator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(450, 600);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0x263640));
        frame.setIconImage(new ImageIcon("calculator.png").getImage());
        frame.setLayout(null);
        Font font = new Font("Roboto", Font.BOLD, 20);
        frame.setFont(font);

        //content
        contentField = new JTextField();
        contentField.setFont(new Font("Roboto", Font.BOLD, 15));
        contentField.setHorizontalAlignment(JTextField.RIGHT);
        contentField.setBackground(new Color(0x263640));
        contentField.setBounds(50, 15, 335, 20);
        contentField.setEditable(false);
        contentField.setForeground(Color.WHITE);
        contentField.setBorder(BorderFactory.createLineBorder(new Color(0x63adfc), 2));
        frame.add(contentField);

        //textField
        textField = new JTextField();
        textField.setFont(font);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(new Color(0x263640));
        textField.setBounds(50, 50, 335, 50);
        textField.setEditable(false);
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(new Color(0x63adfc), 3));
        frame.add(textField);

        //mainButtons
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1, 2, 10, 10));
        upperPanel.setBounds(50, 120, 335, 40);
        upperPanel.setBackground(new Color(0x263640));
        Button delete = new Button("Delete", new Color(0xfd6f71), this, null, ButtonType.DELETE);
        upperButtons.add(delete);
        upperPanel.add(delete.getButton());
        Button clear = new Button("Clear", new Color(0xfd6f71), this, null, ButtonType.CLEAR);
        upperButtons.add(clear);
        upperPanel.add(clear.getButton());
        frame.add(upperPanel);

        //buttonsPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4, 10, 10));
        mainPanel.setBounds(50, 180, 335, 335);
        mainPanel.setBackground(new Color(0x263640));
        mainButtons.add(new Button("1", new Color(0x1cb7a0), this, 1, ButtonType.NUMBER));
        mainButtons.add(new Button("2", new Color(0x1cb7a0), this, 2, ButtonType.NUMBER));
        mainButtons.add(new Button("3", new Color(0x1cb7a0), this, 3, ButtonType.NUMBER));
        mainButtons.add(new Button("+", new Color(0x919fa7), this, CalculatorOperator.ADDITION, ButtonType.OPERATOR));
        mainButtons.add(new Button("4", new Color(0x1cb7a0), this, 4, ButtonType.NUMBER));
        mainButtons.add(new Button("5", new Color(0x1cb7a0), this, 5, ButtonType.NUMBER));
        mainButtons.add(new Button("6", new Color(0x1cb7a0), this, 6, ButtonType.NUMBER));
        mainButtons.add(new Button("-", new Color(0x919fa7), this, CalculatorOperator.SUBTRACTION, ButtonType.OPERATOR));
        mainButtons.add(new Button("7", new Color(0x1cb7a0), this, 7, ButtonType.NUMBER));
        mainButtons.add(new Button("8", new Color(0x1cb7a0), this, 8, ButtonType.NUMBER));
        mainButtons.add(new Button("9", new Color(0x1cb7a0), this, 9, ButtonType.NUMBER));
        mainButtons.add(new Button("*", new Color(0x919fa7), this, CalculatorOperator.MULTIPLICATION, ButtonType.OPERATOR));
        mainButtons.add(new Button(".", new Color(0x919fa7), this, null, ButtonType.DOT));
        mainButtons.add(new Button("0", new Color(0x1cb7a0), this, 0, ButtonType.NUMBER));
        mainButtons.add(new Button("=", new Color(0x63adfc), this, null, ButtonType.EQUALS));
        mainButtons.add(new Button("/", new Color(0x919fa7), this, CalculatorOperator.DIVISION, ButtonType.OPERATOR));
        for (Button button : mainButtons) {
            mainPanel.add(button.getButton());
        }
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    public static String formatNumber(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JButton eventButton = (JButton) event.getSource();
        Button button = getButton(eventButton);

        if (button == null) {
            return;
        }

        if (button.getType() == ButtonType.CLEAR) {
            textField.setText("");
        } else if (button.getType() == ButtonType.DELETE) {
            textField.setText("");
            contentField.setText("");
            numberOne = 0;
        } else if (button.getType() == ButtonType.NUMBER) {
            textField.setText(textField.getText().concat(String.valueOf(button.getValue())));
        } else if (button.getType() == ButtonType.DOT) {
            textField.setText(textField.getText().concat("."));
        } else if (button.getType() == ButtonType.OPERATOR) {
            operator = button.getOperator();
            numberOne = Double.parseDouble(textField.getText());
            textField.setText("");

            contentField.setText(formatNumber(numberOne) + operator.getSign());
        } else if (button.getType() == ButtonType.EQUALS) {
            double numberTwo = Double.parseDouble(textField.getText());

            if (operator == CalculatorOperator.ADDITION) {
                result = numberOne + numberTwo;
            } else if (operator == CalculatorOperator.SUBTRACTION) {
                result = numberOne - numberTwo;
            } else if (operator == CalculatorOperator.MULTIPLICATION) {
                result = numberOne * numberTwo;
            } else if (operator == CalculatorOperator.DIVISION) {
                result = numberOne / numberTwo;
            }

            numberOne = result;
            textField.setText(formatNumber(result));
            contentField.setText("");
        }
    }

    private Button getButton(JButton jButton) {
        for (Button button : mainButtons) {
            if (button.getButton() == jButton) {
                return button;
            }
        }
        for (Button upperButton : upperButtons) {
            if (upperButton.getButton() == jButton) {
                return upperButton;
            }
        }
        return null;
    }


}
