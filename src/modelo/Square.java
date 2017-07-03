package modelo;

import javax.swing.JButton;
import java.awt.Point;

/**
 * Created by SusVa on 30/06/17.
 */
public class Square extends JButton {

    private Piece piece;
    private Point position;

    public Square(Point position) {
        super();
        this.position = position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Point getPosition() {
        return position;
    }

}
