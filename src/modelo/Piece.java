package modelo;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * @author SusVa
 */
abstract class Piece {

    private Point position;
    private ImageIcon image;
    private boolean isWhite;

    protected Piece(Point position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
        setShape(isWhite);
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setImage(String url) {
        this.image = new ImageIcon(url);
    }

    public ImageIcon getImage() {
        return image;
    }

    public boolean isWhite() {
        return isWhite;
    }

    protected abstract void setShape(boolean isWhite);

    public abstract ArrayList<Point> estimateAvailableMovement(Square[][] squares);

}
