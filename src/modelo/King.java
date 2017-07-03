package modelo;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author SusVa
 */
public class King extends Piece {
    
    public King(Point p, boolean esBlanco) {
        super(p, esBlanco);
    }
    
    public void setShape(boolean esBlanco) {
        if (esBlanco) {
            setImage("src/icons/brey2.png");
        } else {
            setImage("src/icons/nrey2.png");
        }
    }
    
    @Override
    public ArrayList<Point> estimateAvailableMovement(Square[][] squares) {
        ArrayList<Point> boxes = posicionesCandidatas(squares);

        return boxes;
    }

    private ArrayList<Point> posicionesCandidatas(Square[][] squares) {
        ArrayList<Point> boxes = new ArrayList<>();
        
        for (int posX = getPosition().x - 1; posX <= getPosition().x + 1; posX++) {
            for (int posY = getPosition().y - 1; posY <= getPosition().y + 1; posY++) {
                if (posX < 0 || posX >= 8 || posY < 0 || posY >= 8) {
                    continue;
                }
                if (null == squares[posX][posY].getPiece()) {
                    boxes.add(new Point(posX, posY));
                } else if (isWhite() != squares[posX][posY].getPiece().isWhite()) {
                    boxes.add(new Point(posX,posY));
                }
            }
        }

        return boxes;
    }
}
