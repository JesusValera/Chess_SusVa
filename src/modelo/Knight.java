package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author SusVa
 */
public class Knight extends Piece {
    
    public Knight(Point p, boolean esBlanco) {
        super(p, esBlanco);
    }
    
    public void setShape(boolean esBlanco) {
        if (esBlanco) {
            setImage("src/icons/bcaballo.png");
        } else {
            setImage("src/icons/ncaballo.png");
        }
    }
    
    @Override
    public ArrayList<Point> estimateAvailableMovement(Square[][] squares) {

        ArrayList<Square> casillasList = new ArrayList<>();
        for (int i = 0; i < squares.length; i++) {
            casillasList.addAll(Arrays.asList(squares[i]));
        }

        ArrayList<Piece> pieces = new ArrayList<>();
        for (Square square : casillasList) {
            if (square.getPiece() != null) {
                pieces.add(square.getPiece());
            }
        }

        pieces.remove(this);
        ArrayList<Point> movimientosDisponibles = posiblesMovimientos();

        return movimientosCaballo(movimientosDisponibles, pieces);
    }

    private ArrayList<Point> posiblesMovimientos() {
        ArrayList<Point> posiblesMovimientos = new ArrayList<>();
        Point[] posicionesCandidatas = new Point[] {
                new Point(-2, -1), new Point(-2, 1),
                new Point(-1, -2), new Point(-1, 2),
                new Point(1, -2), new Point(1, 2),
                new Point(2, -1), new Point(2, 1)
        };

        for (Point posicionesCandidata : posicionesCandidatas) {
            int x = this.getPosition().x + posicionesCandidata.x;
            int y = this.getPosition().y + posicionesCandidata.y;
            if (discriminarFueraTablero(x, y)) {
                posiblesMovimientos.add(new Point(x, y));
            }
        }

        return posiblesMovimientos;
    }

    private boolean discriminarFueraTablero(int x, int y) {
        return !(x < 0 || x > 7 || y < 0 || y > 7);
    }

    private ArrayList<Point> movimientosCaballo(ArrayList<Point> movimientosDisponibles,
                                                ArrayList<Piece> pieces) {
        ArrayList<Point> aux = new ArrayList<>();
        for (Point movs: movimientosDisponibles) {
            for (Piece p: pieces) {
                if (p.isWhite() == this.isWhite()) {
                    if (movs.equals(p.getPosition())) {
                        aux.add(movs);
                    }
                }
            }
        }
        movimientosDisponibles.removeAll(aux);

        return movimientosDisponibles;
    }
}
