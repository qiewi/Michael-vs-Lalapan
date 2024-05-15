package gui;

import javax.swing.*;
import java.awt.*;

class MyButton extends JButton {
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    private Color disabledBackgroundColor; // New color for disabled state
    private static boolean border1 = false;
    private static boolean border2 = false;

    public MyButton() {
        this(null);
    }

    public MyButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setHoverBackgroundColor(new Color(220, 220, 220));
        setPressedBackgroundColor(new Color(200, 200, 200));
        setDisabledBackgroundColor(new Color(139, 69, 19)); // Set the color for disabled state
        setForeground(new Color(214, 158, 45));
        setFont(new Font("Arial", Font.BOLD, 16)); // Set the specific font
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isEnabled()) {
            g.setColor(disabledBackgroundColor);
        } else if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(new Color(139, 69, 19)); // Solid brown color
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (border1) {
            g.setColor(Color.yellow);
            if (border2) {
                g.setColor(Color.red);
            }
        } else {
            g.setColor(getForeground());
        }
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        repaint();
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    public Color getDisabledBackgroundColor() {
        return disabledBackgroundColor;
    }

    public void setDisabledBackgroundColor(Color disabledBackgroundColor) {
        this.disabledBackgroundColor = disabledBackgroundColor;
    }

    public void setBorder1(boolean bool) {
        MyButton.border1 = bool;
    }
    
    public void setBorder2(boolean bool) {
        MyButton.border2 = bool;
    }
}

// Uncomment the following lines to test the button in a frame
/*
public class CustomizedButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        MyButton button = new MyButton("Custom Button");
        button.setPreferredSize(new Dimension(150, 50));
        frame.add(button);

        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
*/