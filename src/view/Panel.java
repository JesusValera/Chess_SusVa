package view;

import modelo.Tablero;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author SusVa
 */
public class Panel extends JPanel {
    
    public Panel(Frame frame) {
        properties(frame);
        new Tablero(this);
    }
    
    private void properties(Frame frame) {
        this.setSize(frame.getSize().width, frame.getSize().height);
        this.setLayout(new GridLayout(8, 8));
    }
    
}
