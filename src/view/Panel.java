package view;

import modelo.Board;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author SusVa
 */
public class Panel extends JPanel {
    
    public Panel(Frame frame) {
        properties(frame);
        new Board(this);
    }
    
    private void properties(Frame frame) {
        this.setSize(frame.getSize().width, frame.getSize().height);
        this.setLayout(new GridLayout(8, 8));
    }
    
}
