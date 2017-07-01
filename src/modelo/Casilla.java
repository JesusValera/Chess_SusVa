package modelo;

import javax.swing.JButton;

/**
 * Created by SusVa on 30/06/17.
 */
public class Casilla extends JButton {

    private Pieza pieza;

    public Casilla() {
        super();
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }
}
