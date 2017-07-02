package modelo;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Board {

    public static final int SQUARE_SIZE = 8;
    public static boolean gameIsNotOver;
    private Square[][] squares;
    private List<Point> availableMovements;
    private Point posOld, posNew;
    private Piece currentPiece;
    private boolean turnWhite;

    public Board(JPanel panel) {
        gameIsNotOver = true;
        squares = new Square[SQUARE_SIZE][SQUARE_SIZE];
        availableMovements = new ArrayList<>();
        createSquares(panel);
        turnWhite = true;
    }

    private void createSquares(JPanel panel) {
        for (int i = 0; i < SQUARE_SIZE; i++) {
            for (int j = 0; j < SQUARE_SIZE; j++) {
                Square bt = configureSquare(i, j);
                panel.add(bt);
                squares[i][j] = bt;
                addPiecesOnBoardGame(bt, new Point(i, j));
            }
        }

        putShapeOnSquare();
    }

    private Square configureSquare(int i, int j) {
        Square bt = new Square();
        bt.setName(String.valueOf(i) + String.valueOf(j));
        bt.addMouseListener(mouseAdapter);
        bt.setBackground(setBackgroundColorSquare((j + i) % 2 == 0));
        return bt;
    }

    private Color setBackgroundColorSquare(boolean esPar) {
        if (esPar) {
            return Color.white;
        } else {
            return Color.gray;
        }
    }

    private void addPiecesOnBoardGame(Square sq, Point point) {
        pawn(sq, point);
        rook(sq, point);
        knight(sq, point);
        bioshop(sq, point);
        king(sq, point);
        queen(sq, point);
    }

    private void pawn(Square sq, Point point) {
        if (posPiece(point, 1, point.y)) {
            sq.setPiece(new Pawn(point, false));
        }

        if (posPiece(point, 6, point.y)) {
            sq.setPiece(new Pawn(point, true));
        }
    }

    private void rook(Square sq, Point point) {
        if (posPiece(point, 0, 0) || posPiece(point, 0, 7)) {
            sq.setPiece(new Rook(point, false));
        }

        if (posPiece(point, 7, 0) || posPiece(point, 7, 7)) {
            sq.setPiece(new Rook(point, true));
        }
    }

    private void knight(Square sq, Point position) {
        if (posPiece(position, 0, 1) || posPiece(position, 0, 6)) {
            sq.setPiece(new Knight(position, false));
        }

        if (posPiece(position, 7, 1) || posPiece(position, 7, 6)) {
            sq.setPiece(new Knight(position, true));
        }
    }

    private void bioshop(Square sq, Point point) {
        if (posPiece(point, 0, 2) || posPiece(point, 0, 5)) {
            sq.setPiece(new Bishop(point, false));
        }

        if (posPiece(point, 7, 2) || posPiece(point, 7, 5)) {
            sq.setPiece(new Bishop(point, true));
        }
    }

    private void king(Square sq, Point point) {
        if (posPiece(point, 0, 4)) {
            sq.setPiece(new King(point, false));
        }

        if (posPiece(point, 7, 4)) {
            sq.setPiece(new King(point, true));
        }
    }

    private void queen(Square sq, Point point) {
        if (posPiece(point, 0, 3)) {
            sq.setPiece(new Queen(point, false));
        }

        if (posPiece(point, 7, 3)) {
            sq.setPiece(new Queen(point, true));
        }
    }

    private boolean posPiece(Point point, int x, int y) {
        return point.x == x && point.y == y;
    }

    private void putShapeOnSquare() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squareHasPiece(squares[i][j]);
            }
        }
    }

    private void squareHasPiece(Square square) {
        if (square.getPiece() != null) {
            square.setIcon(square.getPiece().getImage());
        } else {
            square.setIcon(null);
        }
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Selected: " + ((Square) e.getSource()).getName());
            super.mouseClicked(e);

            Square square = ((Square) e.getSource());
            Piece piece = square.getPiece();
            repintarCasillas();
            pintarCasillaSeleccionada(square);

            if (turnWhite) {
                System.err.println("White Turn");
                comerPieza(square);

                if (piece == null) {
                    return;
                }
                
                if (piece.isWhite()) {
                    availableMovements = mostrarMovimientos(piece);

                    // if availableMovements.get(0).getPoint() = new Point(-1, -1) -> then... TODO. - peon ultima pos

                    pintarMovimientosDisponiblesTablero(availableMovements);

                    currentPiece = piece;
                    posOld = new Point(Integer.valueOf(square.getName().substring(0, 1)),
                            Integer.valueOf(square.getName().substring(1)));
                }

            } else {
                System.err.println("Black Turn");
                comerPieza(square);

                if (piece == null) {
                    return;
                }
                
                if (!piece.isWhite()) {
                    availableMovements = mostrarMovimientos(piece);
                    pintarMovimientosDisponiblesTablero(availableMovements);

                    currentPiece = piece;
                    posOld = new Point(Integer.valueOf(square.getName().substring(0, 1)),
                            Integer.valueOf(square.getName().substring(1)));
                }
            }
        }

        private void pintarCasillaSeleccionada(Square square) {
            square.setBackground(new Color(0.5f, 0.5f, 0.9f, 0.3f));
        }

        private void comerPieza(Square square) {
            if (currentPiece == null) {
                return;
            }

            if (piezaCandidata(square.getName())) {
                squares[posOld.x][posOld.y].setPiece(null);
                currentPiece.setPosition(posNew);
                square.setPiece(currentPiece);
                turnWhite = !turnWhite;
                currentPiece = null;
                squares[posOld.x][posOld.y].setBackground(new Color(0.5f, 0.7f, 0.5f));
                squares[posNew.x][posNew.y].setBackground(new Color(0.3f, 0.6f, 0.3f));
                posOld = null;
                posNew = null;
                putShapeOnSquare();
            }
        }

        private void pintarMovimientosDisponiblesTablero(List<Point> availableMovements) {
            for (Point movs : availableMovements) {
                try {
                    squares[movs.x][movs.y].setBackground(new Color(0.9f, 0.5f, 0.5f, 0.4f));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("[" + movs.x + ", " + movs.y + "] - Posicion fuera de tablero.");
                }
            }
        }

        private void repintarCasillas() {
            for (int i = 0; i < squares.length; i++) {
                for (int j = 0; j < squares[i].length; j++) {
                    squares[i][j].setBackground(setBackgroundColorSquare((j + i) % 2 == 0));
                }
            }
        }

        private boolean piezaCandidata(String idPieza) {
            for (Point movs : availableMovements) {
                String positions = String.valueOf(movs.x) + String.valueOf(movs.y);
                if (positions.equals(idPieza)) {
                    System.out.println("Posiciones: " + positions);
                    posNew = movs;
                    return true;
                }
            }
            return false;
        }

        private List<Point> mostrarMovimientos(Piece piece) {
            return piece.calcularMovimientosDisponibles(squares);
        }
    };

}
