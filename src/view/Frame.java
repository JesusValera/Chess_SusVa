package view;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Frame extends JFrame {

    private final int WIDTH = 800, HEIGHT = 800;

    public Frame() {
        propiedades();
    }

    private void propiedades() {
        this.setSize(WIDTH, HEIGHT);
        Panel panel = new Panel(this);
        this.setContentPane(panel);
        this.setTitle("Ajedrez - SusVa.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
    }

}
