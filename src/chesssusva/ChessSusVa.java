package chesssusva;

import view.Frame;

import javax.swing.JOptionPane;

/**
 * @author SusVa
 */
public class ChessSusVa {

    public static void main(String[] args) {
        try {
            new Frame();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Hubo un problema al cargar la aplicación.",
                    "Error",
                    JOptionPane.OK_OPTION);
        }
    }

}
