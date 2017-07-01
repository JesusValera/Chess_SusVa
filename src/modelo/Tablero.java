package modelo;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Tablero {

    private Casilla[][] casillas;
    private boolean turnoBlanco;
    //public boolean juegoNoTerminado;
    private final int CASILLAS = 8;
    // El id de las piezas es el mismo que el de la casilla en el que se 
    //  encuentra, el cual es equivalente a la posicion en la que se ubica
    //  tomando como id las coordenadas X e Y en el momento de su creacion.
    private List<Point> movimientosDisponibles;

    private Point posAntigua, posNueva;
    private Pieza piezaSeleccionada;

    public Tablero(JPanel panel) {
        //juegoNoTerminado = true;
        casillas = new Casilla[8][8];
        //piezas = new HashMap<>();
        movimientosDisponibles = new ArrayList<>();
        crearCampos(panel);
        turnoBlanco = true;
    }

    private void crearCampos(JPanel panel) {
        for (int i = 0; i < CASILLAS; i++) {
            for (int j = 0; j < CASILLAS; j++) {

                Casilla bt = configurarCasilla(i, j);
                anadirListas(panel, bt, i, j);
            }
        }

        pintarIconos();
    }

    private Casilla configurarCasilla(int i, int j) {
        Casilla bt = new Casilla();
        bt.setName(String.valueOf(i) + String.valueOf(j));
        bt.addMouseListener(mouseAdapter);
        bt.setBackground(colorFondo((j + i) % 2 == 0));
        return bt;
    }

    private Color colorFondo(boolean esPar) {
        if (esPar) {
            return Color.white;
        } else {
            return Color.gray;
        }
    }

    private void anadirListas(JPanel panel, Casilla bt, int i, int j) {
        panel.add(bt);
        casillas[i][j] = bt;
        anadirPiezas(bt, new Point(i, j));
    }

    private void anadirPiezas(Casilla bt, Point p) {
        peon(bt, p);
        torre(bt, p);
        caballo(bt, p);
        alfil(bt, p);
        rey(bt, p);
        reina(bt, p);
    }

    private void peon(Casilla bt, Point p) {
        if (posPeonNegro(p)) {
            crearPieza(bt, new Peon(p, false));
        }
        if (posPeonBlanco(p)) {
            crearPieza(bt, new Peon(p, true));
        }
    }

    private boolean posPeonNegro(Point p) {
        return p.x == 1;
    }

    private boolean posPeonBlanco(Point p) {
        return p.x == 6;
    }

    private void torre(Casilla bt, Point p) {
        if (posTorreNegro(p)) {
            crearPieza(bt, new Torre(p, false));
        }
        if (posTorreBlanco(p)) {
            crearPieza(bt, new Torre(p, true));
        }
    }

    private boolean posTorreNegro(Point p) {
        return p.x == 0 && p.y == 0 || p.x == 0 && p.y == 7;
    }

    private boolean posTorreBlanco(Point p) {
        return p.x == 7 && p.y == 0 || p.x == 7 && p.y == 7;
    }

    private void caballo(Casilla bt, Point p) {
        if (posCaballoNegro(p)) {
            crearPieza(bt, new Caballo(p, false));
        }
        if (posCaballoBlanco(p)) {
            crearPieza(bt, new Caballo(p, true));
        }
    }

    private boolean posCaballoNegro(Point p) {
        return p.x == 0 && p.y == 1 || p.x == 0 && p.y == 6;
    }

    private boolean posCaballoBlanco(Point p) {
        return p.x == 7 && p.y == 1 || p.x == 7 && p.y == 6;
    }

    private void alfil(Casilla bt, Point p) {
        if (posAlfilNegro(p)) {
            crearPieza(bt, new Alfil(p, false));
        }
        if (posAlfilBlanco(p)) {
            crearPieza(bt, new Alfil(p, true));
        }
    }

    private boolean posAlfilNegro(Point p) {
        return p.x == 0 && p.y == 2 || p.x == 0 && p.y == 5;
    }

    private boolean posAlfilBlanco(Point p) {
        return p.x == 7 && p.y == 2 || p.x == 7 && p.y == 5;
    }

    private void rey(Casilla bt, Point p) {
        if (posReyNegro(p)) {
            crearPieza(bt, new Rey(p, false));
        }
        if (posReyBlanco(p)) {
            crearPieza(bt, new Rey(p, true));
        }
    }

    private boolean posReyNegro(Point p) {
        return p.x == 0 && p.y == 4;
    }

    private boolean posReyBlanco(Point p) {
        return p.x == 7 && p.y == 4;
    }

    private void reina(Casilla bt, Point p) {
        if (posReinaNegro(p)) {
            crearPieza(bt, new Reina(p, false));
        }
        if (posReinaBlanco(p)) {
            crearPieza(bt, new Reina(p, true));
        }
    }

    private boolean posReinaNegro(Point p) {
        return p.x == 0 && p.y == 3;
    }

    private boolean posReinaBlanco(Point p) {
        return p.x == 7 && p.y == 3;
    }

    private void crearPieza(Casilla bt, Pieza pieza) {
        pieza.anadirColor(pieza.esBlanca);
        bt.setPieza(pieza);
    }

    private void pintarIconos() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillaTienePieza(casillas[i][j]);
            }
        }
    }

    private void casillaTienePieza(Casilla casilla) {
        if (casilla.getPieza() != null) {
            casilla.setIcon(casilla.getPieza().getImagen());
        } else {
            casilla.setIcon(null);
        }
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Pulsada: " + ((Casilla) e.getSource()).getName());
            super.mouseClicked(e);

            Casilla casilla = ((Casilla) e.getSource());
            Pieza pieza = ((Casilla) e.getSource()).getPieza();
            repintarCasillas();
            pintarCasillaSeleccionada(casilla);

            if (turnoBlanco) {
                System.err.println("Turno Blanco");
                comerPieza(casilla);

                if (pieza != null) {
                    if (pieza.esBlanca) {

                        movimientosDisponibles = mostrarMovimientos(pieza);

                        // if movimientosDisponibles.get(0).getPoint() = new Point(-1, -1) -> then...

                        pintarMovimientosDisponiblesTablero(movimientosDisponibles);

                        piezaSeleccionada = pieza;
                        posAntigua = new Point(Integer.valueOf(casilla.getName().substring(0, 1)),
                                Integer.valueOf(casilla.getName().substring(1)));
                    }
                }

            } else {
                System.err.println("Turno Negro");
                comerPieza(casilla);

                if (pieza != null) {
                    if (!pieza.esBlanca) {

                        movimientosDisponibles = mostrarMovimientos(pieza);
                        pintarMovimientosDisponiblesTablero(movimientosDisponibles);

                        piezaSeleccionada = pieza;
                        posAntigua = new Point(Integer.valueOf(casilla.getName().substring(0, 1)),
                                Integer.valueOf(casilla.getName().substring(1)));
                    }
                }
            }

        }

        private void pintarCasillaSeleccionada(Casilla casilla) {
            casilla.setBackground(new Color(0.5f, 0.5f, 0.9f, 0.3f));
        }

        private void comerPieza(Casilla casilla) {
            if (piezaSeleccionada != null) {
                if (piezaCandidata(casilla.getName())) {

                    casillas[posAntigua.x][posAntigua.y].setPieza(null);
                    piezaSeleccionada.setPosicion(posNueva);
                    casilla.setPieza(piezaSeleccionada);
                    //repintarCasillas();
                    turnoBlanco = !turnoBlanco;
                    piezaSeleccionada = null;
                    casillas[posAntigua.x][posAntigua.y].setBackground(new Color(0.5f, 0.7f, 0.5f));
                    casillas[posNueva.x][posNueva.y].setBackground(new Color(0.3f, 0.6f, 0.3f));
                    posAntigua = null;
                    posNueva = null;
                    pintarIconos();
                }
            }
        }

        private void pintarMovimientosDisponiblesTablero(List<Point> movimientosDisponibles) {
            for (Point movs : movimientosDisponibles) {
                try {
                    casillas[movs.x][movs.y].setBackground(new Color(0.9f, 0.5f, 0.5f, 0.4f));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("[" + movs.x + ", " + movs.y + "] - Posicion fuera de tablero.");
                }
            }
        }

        private void repintarCasillas() {
            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas[i].length; j++) {
                    casillas[i][j].setBackground(colorFondo((j + i) % 2 == 0));
                }
            }
        }

        private boolean piezaCandidata(String idPieza) {
            for (Point movs : movimientosDisponibles) {
                String positions = String.valueOf(movs.x) + String.valueOf(movs.y);
                if (positions.equals(idPieza)) {
                    System.out.println("Posiciones: " + positions);
                    posNueva = movs;
                    return true;
                }
            }
            return false;
        }
    };

    private List<Point> mostrarMovimientos(Pieza pieza) {
        return pieza.calcularMovimientosDisponibles(casillas);
    }

//    private ArrayList<Casilla> obtenerCasillas(Casilla[][] casillas) {
////        ArrayList<Pieza> piezas = new ArrayList<>();
////        for (int i = 0; i < casillas.length; i++) {
////            for (int j = 0; j < casillas[i].length; j++) {
////                if (casillas[i][j].getPieza() != null) {
////                    piezas.add(casillas[i][j].getPieza());
////                }
////            }
////        }
////        return piezas;
//
//        ArrayList<Casilla> casillasList = new ArrayList<>();
//        for (int i = 0; i < casillas.length; i++) {
//            for (int j = 0; j < casillas[i].length; j++) {
//                casillasList.add(casillas[i][j]);
//            }
//        }
//
//        return casillasList;
//    }

}
