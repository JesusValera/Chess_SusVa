package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author SusVa
 */
public class Pawn extends Piece {

    public Pawn(Point p, boolean isWhite) {
        super(p, isWhite);
    }

    protected void setShape(boolean isWhite) {
        if (isWhite) {
            setImage("src/icons/bpeon.png");
        } else {
            setImage("src/icons/npeon.png");
        }
    }

    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(Square[][] squares) {
        ArrayList<Point> posiblesMovimientos = new ArrayList<>();
        Point posicionCandidata, posicionCandidataX2,
                posicionCandidataIzq, posicionCandidataDer;
        int posInicial;
        int posFinalTablero;

        ArrayList<Square> casillasList = new ArrayList<>();
        for (int i = 0; i < squares.length; i++) {
            Collections.addAll(casillasList, squares[i]);
        }

        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < casillasList.size(); i++) {
            if (casillasList.get(i).getPiece() != null) {
                pieces.add(casillasList.get(i).getPiece());
            }
        }
        pieces.remove(this);

        if (isWhite()) {
            posFinalTablero = 0;
            posicionCandidata = new Point(this.getPosition().x - 1, this.getPosition().y);
            posInicial = 6;
            posicionCandidataX2 = new Point(this.getPosition().x - 2, this.getPosition().y);
            posicionCandidataIzq = new Point(this.getPosition().x - 1, this.getPosition().y + 1);
            posicionCandidataDer = new Point(this.getPosition().x - 1, this.getPosition().y - 1);
        } else {
            posFinalTablero = 7;
            posicionCandidata = new Point(this.getPosition().x + 1, this.getPosition().y);
            posInicial = 1;
            posicionCandidataX2 = new Point(this.getPosition().x + 2, this.getPosition().y);
            posicionCandidataIzq = new Point(this.getPosition().x + 1, this.getPosition().y + 1);
            posicionCandidataDer = new Point(this.getPosition().x + 1, this.getPosition().y - 1);
        }

        if (posFinalTablero == this.getPosition().x) {
            posiblesMovimientos.add(new Point(-1, -1));
            // Transformar pieza en otra ficha. TODO.
            return posiblesMovimientos;
        }

        zancadaSimple(pieces, posiblesMovimientos, posicionCandidata);
        doblezancada(pieces, posiblesMovimientos, posicionCandidataX2,
                posInicial);
        fichaContrariaComestible(pieces, posiblesMovimientos,
                posicionCandidataIzq, posicionCandidataDer);

        return posiblesMovimientos;
    }

    private void zancadaSimple(ArrayList<Piece> pieces,
            ArrayList<Point> posiblesMovimientos, Point posicionCandidata) {

        posiblesMovimientos.add(posicionCandidata);

        for (Piece piece : pieces) {
            if (posicionCandidata.equals(piece.getPosition())) {
                posiblesMovimientos.remove(posicionCandidata);
                break;
            }
        }
    }

    private void doblezancada(ArrayList<Piece> pieces,
            ArrayList<Point> posiblesMovimientos,
            Point posicionCandidata, int posInicial) {

        if (this.getPosition().x != posInicial) {
            return;
        }

        posiblesMovimientos.add(posicionCandidata);

        for (Piece piece : pieces) {
            if (posicionCandidata.equals(piece.getPosition())) {
                posiblesMovimientos.remove(posicionCandidata);
                break;
            }
        }
    }

    private void fichaContrariaComestible(ArrayList<Piece> pieces,
            ArrayList<Point> posiblesMovimientos,
            Point posicionCandidataIzq, Point posicionCandidataDer) {
        comerPiezaIzquierda(pieces, posiblesMovimientos, posicionCandidataIzq);
        comerPiezaDerecha(pieces, posiblesMovimientos, posicionCandidataDer);
    }

    private void comerPiezaIzquierda(ArrayList<Piece> pieces,
                                     ArrayList<Point> posiblesMovimientos,
                                     Point posicionCandidataIzq) {
        for (Piece piece : pieces) {
            if (piece.isWhite() != this.isWhite()) {
                if (piece.getPosition().equals(posicionCandidataIzq)) {
                    posiblesMovimientos.add(posicionCandidataIzq);
                    break;
                }
            }
        }
    }

    private void comerPiezaDerecha(ArrayList<Piece> pieces,
                                     ArrayList<Point> posiblesMovimientos,
                                     Point posicionCandidataDer) {
        for (Piece piece : pieces) {
            if (piece.isWhite() != this.isWhite()) {
                if (piece.getPosition().equals(posicionCandidataDer)) {
                    posiblesMovimientos.add(posicionCandidataDer);
                    break;
                }
            }
        }
    }

}
