package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.UIManager;

public class Frame extends JFrame {

    private final int WIDTH = 800, HEIGHT = 800;

    public Frame() throws Exception {
        propiedades();
    }

    private void propiedades() throws Exception {
        this.setSize(WIDTH, HEIGHT);
        Panel panel = new Panel(this);
        this.setContentPane(panel);
        this.setTitle("Chess - SusVa (v_1.0) 07/2017.");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

}
