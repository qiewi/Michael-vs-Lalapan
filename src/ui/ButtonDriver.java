package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;

public class ButtonDriver extends JPanel {
    private MyButton[] buttons;
    private BufferedImage selectedZombie = null;
	private BufferedImage[] zombieImages = new BufferedImage[10];

    public BufferedImage getZombieImage(String name) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("resources/ZombiesList/" + name + ".png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();   
        }       

        return img;
    }

    public ButtonDriver() {
        // Load an image for demonstration purposes
        BufferedImage image = getZombieImage("Normal");

        // Initialize buttons
        buttons = new MyButton[3];
        buttons[0] = new MyButton(50, 50, 100, 50, image);
        buttons[1] = new MyButton("Click Me", 200, 50, 100, 50);
        buttons[2] = new MyButton("Tile Button", 350, 50, 100, 50, 1);

        // Set up the panel
        setPreferredSize(new Dimension(500, 200));
        setBackground(Color.LIGHT_GRAY);

        // Add mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (MyButton button : buttons) {
                    if (button.getBounds().contains(e.getPoint())) {
                        button.setMousePressed(true);
                        button.setMouseHover(true);
                        System.out.println("Button clicked: " + button.getText());
                    } else {
                        button.setMousePressed(false);
                    }
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                for (MyButton button : buttons) {
                    if (button.getBounds().contains(e.getPoint())) {
                        button.setMousePressed(true);
                    } else {
                        button.setMousePressed(false);
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for (MyButton button : buttons) {
                    button.setMousePressed(false);
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (MyButton button : buttons) {
                    button.setMouseHover(button.getBounds().contains(e.getPoint()));
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MyButton button : buttons) {
            button.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyButton Driver");
        ButtonDriver panel = new ButtonDriver();
        frame.setContentPane(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
