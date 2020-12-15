package pl.arturekdev.objects;

import pl.arturekdev.enums.ButtonType;
import pl.arturekdev.enums.CalculatorOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button {

    private JButton button;
    private int value;
    private CalculatorOperator operator;
    private ButtonType type;

    @Override
    public String toString() {
        return "Button{" +
                "button=" + button +
                ", value=" + value +
                ", operator=" + operator +
                ", type=" + type +
                '}';
    }

    public Button(String title, Color color, ActionListener actionListener, CalculatorOperator operator, ButtonType type) {
        this.button = buildStyleButton(title, color, actionListener);
        this.operator = operator;
        this.type = type;
    }

    public Button(String title, Color color, ActionListener actionListener, int value, ButtonType type) {
        this.button = buildStyleButton(title, color, actionListener);
        this.value = value;
        this.type = type;
    }

    private JButton buildStyleButton(String title, Color color, ActionListener actionListener) {
        JButton jButton = new JButton(title);
        jButton.setBackground(color);
        jButton.setFocusable(false);
        jButton.setForeground(Color.WHITE);
        jButton.setFont(new Font("Roboto", Font.BOLD, 20));
        jButton.addActionListener(actionListener);
        jButton.setBorder(BorderFactory.createLineBorder(color, 2));
        return jButton;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CalculatorOperator getOperator() {
        return operator;
    }

    public void setOperator(CalculatorOperator operator) {
        this.operator = operator;
    }

    public ButtonType getType() {
        return type;
    }

    public void setType(ButtonType type) {
        this.type = type;
    }
}
