package modelo;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

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
        Square bt = new Square(new Point(i, j));
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
            super.mouseClicked(e);
            System.out.println("Selected: " + ((Square) e.getSource()).getPosition().x + "::" + ((Square) e.getSource()).getPosition().y);

            Square square = ((Square) e.getSource());
            Piece piece = square.getPiece();
            repintarCasillas();
            paintSelectedSquare(square);

            System.err.println(((turnWhite) ? "White" : "Black") + " Turn");

            if (turnWhite) {
                comerPieza(square);
                if (null != piece && piece.isWhite()) {
                    availableMovements = mostrarMovimientos(piece);
                    canCastlingTheKingAndTheRook(availableMovements, piece);
                    drawAvailableMovsOnBoard(availableMovements);

                    currentPiece = piece;
                    posOld = square.getPosition();
                }
            } else {
                comerPieza(square);
                if (null != piece && !piece.isWhite()) {
                    availableMovements = mostrarMovimientos(piece);
                    canCastlingTheKingAndTheRook(availableMovements, piece);
                    drawAvailableMovsOnBoard(availableMovements);

                    currentPiece = piece;
                    posOld = square.getPosition();
                }
            }
        }

        private void canCastlingTheKingAndTheRook(List<Point> availableMovements, Piece piece) {
            if (!(piece instanceof King)) {
                return;
            }

            if (piece.isWhite() && piece.getPosition().x == 7) {
                if (piece.getPosition().y == 4) {
                    if (squares[7][5].getPiece() == null && squares[7][6].getPiece() == null) {
                        if (squares[7][7].getPiece() instanceof Rook) {
                            availableMovements.add(new Point(7, 6));
                        }
                    }
                }
                if (piece.getPosition().y == 3) {
                    if (squares[7][2].getPiece() == null && squares[7][1].getPiece() == null) {
                        if (squares[7][0].getPiece() instanceof Rook) {
                            availableMovements.add(new Point(7, 1));
                        }
                    }
                }
            }

            if (!piece.isWhite() && piece.getPosition().x == 0) {
                if (piece.getPosition().y == 4) {
                    if (squares[0][5].getPiece() == null && squares[0][6].getPiece() == null) {
                        if (squares[0][7].getPiece() instanceof Rook) {
                            availableMovements.add(new Point(0, 6));
                        }
                    }
                }
                if (piece.getPosition().y == 3) {
                    if (squares[0][2].getPiece() == null && squares[0][1].getPiece() == null) {
                        if (squares[0][0].getPiece() instanceof Rook) {
                            availableMovements.add(new Point(0, 1));
                        }
                    }
                }
            }
        }

        private void isPawnOnLastPosition(boolean isWhite) {
            int position = (isWhite) ? 0 : 7;
            Point positionPawn;
            Piece piece = null;
            Square[] verticalAxis = new Square[8];

            for (int i = 0; i < squares.length; i++) {
                for (int j = 0; j < squares[i].length; j++) {
                    if (j == position)
                        verticalAxis[i] = squares[j][i];
                }
            }
            for (Square sq : verticalAxis) {
                if (null != sq.getPiece()) {
                    if (sq.getPiece() instanceof Pawn) {
                        positionPawn = sq.getPosition();
                        int rc = -1;
                        while (rc == -1) {
                            String[] buttons = {"Knight", "Rook", "Bishop", "Queen"};
                            rc = JOptionPane.showOptionDialog(null, "Pick one of the following pieces.", "Select",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[2]);
                        }

                        switch (rc) {
                            case 3: // Queen
                                piece = new Queen(positionPawn, isWhite);
                                break;
                            case 2: // Bishop
                                piece = new Bishop(positionPawn, isWhite);
                                break;
                            case 1: // Rook
                                piece = new Rook(positionPawn, isWhite);
                                break;
                            case 0: // Knight
                                piece = new Knight(positionPawn, isWhite);
                                break;
                        }
                        squares[positionPawn.x][positionPawn.y].setPiece(piece);
                        putShapeOnSquare();
                        break;
                    }
                }
            }
        }

        private void paintSelectedSquare(Square square) {
            Color color = new Color(0.5f, 0.5f, 0.9f, 0.3f);
            square.setBackground(color);
        }

        private void comerPieza(Square square) {
            if (currentPiece == null) {
                return;
            }

            if (piezaCandidata(square.getPosition())) {
                squares[posOld.x][posOld.y].setPiece(null);
                currentPiece.setPosition(posNew);
                square.setPiece(currentPiece);
                squares[posOld.x][posOld.y].setBackground(new Color(0.5f, 0.7f, 0.5f));
                squares[posNew.x][posNew.y].setBackground(new Color(0.3f, 0.6f, 0.3f));
                posOld = null;
                posNew = null;
                isPawnOnLastPosition(turnWhite);

                if (currentPiece instanceof King) {
                    selectedSquareToDoCastling(square, turnWhite);
                }

                putShapeOnSquare();
                currentPiece = null;
                turnWhite = !turnWhite;
            }
        }

        private void selectedSquareToDoCastling(Square square, boolean isWhite) {
            int posX = calculatePosX(isWhite);
            if (currentPiece.getPosition().x == posX) {
                if (square.getPosition().y == 6) {
                    squares[posX][7].setPiece(new King(new Point(posX, 7), isWhite));
                    squares[posX][6].setPiece(null);
                    squares[posX][5].setPiece(new Rook(new Point(posX, 5), isWhite));
                }
                if (square.getPosition().y == 1) {
                    squares[posX][0].setPiece(new King(new Point(7, 0), isWhite));
                    squares[posX][1].setPiece(null);
                    squares[posX][2].setPiece(new Rook(new Point(7, 2), isWhite));
                }
            }
        }

        private int calculatePosX(boolean isWhite) {
            if (isWhite) {
                return 7;
            } else {
                return 0;
            }
        }

        private void drawAvailableMovsOnBoard(List<Point> availableMovements) {
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

        private boolean piezaCandidata(Point position) {
            for (Point movs : availableMovements) {
                if (movs.x == position.x && movs.y == position.y) {
                    System.out.println("Posiciones: " + movs.x + ", " + movs.y);
                    posNew = movs;
                    return true;
                }
            }
            return false;
        }

        private List<Point> mostrarMovimientos(Piece piece) {
            return piece.estimateAvailableMovement(squares);
        }
    };

}
