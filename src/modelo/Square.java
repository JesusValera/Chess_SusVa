package modelo;

import javax.swing.JButton;

/**
 * Created by SusVa on 30/06/17.
 */
public class Square extends JButton {

    private Piece piece;

    public Square() {
        super();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
