package busqueda;

/**
 *
 * @author Andrés Garcés
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;
import modelo.laberinto;

public class busqueda {

    private estado actual, meta, inicial;
    private PriorityQueue<estado> abierto;
    private ArrayList<estado> cerrado;
    private laberinto juego;
    private ArrayList<Point> monedas,copiaMonedas;
    private Point monedaMeta;

    public busqueda(Point actual, Point meta, ArrayList<Point> monedass) {
        this.inicial = new estado(actual, null, null);
        this.actual = new estado(actual, null, null);
        this.meta = new estado(meta, null, null);
        this.copiaMonedas = new ArrayList<Point>();
        this.monedas = new ArrayList<Point>(monedass);
        copiaMonedas = (ArrayList) monedas.clone();
        monedaMeta = new Point(meta.x, meta.y);
        abierto = new PriorityQueue<estado>();
        cerrado = new ArrayList<estado>();
        juego = new laberinto();
    }

    public void obtenerResultado() {
        estado resultado = iniciarBusqueda();
        ArrayList<estado> pila = new ArrayList<>();
        laberinto LaberintoResultado = new laberinto();
        int cont = 0;

        if (resultado == null) {

            System.out.print("\nEl laberinto no tiene salida\n");
        } else {

            while (resultado.getAntecesor() != null) {
                pila.add(resultado);
                resultado = resultado.getAntecesor();
                cont++;
            }
            
            for(int i = 0; i < copiaMonedas.size(); i++) {
                LaberintoResultado.setObjetoTipo(copiaMonedas.get(i).x, copiaMonedas.get(i).y, 2);
            }
            
            LaberintoResultado.setLaberinto(inicial.getX(), inicial.getY());
            System.out.print("Laberinto Inicial\n");
            LaberintoResultado.print();

            while (!pila.isEmpty()) {
                System.out.print("Avanzar hacia " + pila.get(cont - 1).getMovimiento() + " al Punto(" + pila.get(cont - 1).getX() + "," + pila.get(cont - 1).getY() + ")\n");
                LaberintoResultado.setLaberinto(pila.get(cont - 1).getX(), pila.get(cont - 1).getY());
                LaberintoResultado.print();
                pila.remove(cont - 1);
                cont--;
            }

            System.out.print("Laberinto resuelto :)\n");
        }
    }

    public estado iniciarBusqueda() {
        monedas.add(monedaMeta);
        boolean exito = false;
        estado inicialTemp = new estado(inicial.getPosicion(), null, null);

        if (inicialTemp.getPosicion().equals(meta.getPosicion())) {
            exito = true;
        }
        while (!monedas.isEmpty()) {
            exito = false;
            meta.setPosicion(monedas.get(0));
            inicialTemp.calcularF(actual, meta);

            abierto.add(inicialTemp);
            while (!abierto.isEmpty() && !exito) {
                //actual = abierto.get(0);
                actual = abierto.poll();
                if (!cerrado.contains(actual)) {
                    cerrado.add(actual);
                    //System.out.println("cerrado: "+actual.toString()+""+actual.getF());
                    expando(actual);
                }
                if (actual.getPosicion().equals(meta.getPosicion())) {
                    exito = true;
                }

                abierto.remove(0);
            }
            if (exito) {
                abierto.clear();
                cerrado.clear();
                inicialTemp = new estado(actual.getPosicion(), actual, "Come Moneda");

            } else {
                break;
            }

            monedas.remove(0);
        }


        if (!exito) {
            return null;
        }

        return actual;
    }

    public void expando(estado actual) {
        Point posicion;
        estado opcion;

        posicion = actual.getPosicion();
        if (posicion.x - 1 >= 0 && juego.getLaberinto()[posicion.x - 1][posicion.y] != 1) {
            posicion.x -= 1;
            opcion = new estado(posicion, actual, "arriba");
            opcion.calcularF(actual, meta);
            abierto.add(opcion);
        }

        posicion = actual.getPosicion();
        if (posicion.x + 1 <= juego.getLaberinto().length - 1 && juego.getLaberinto()[posicion.x + 1][posicion.y] != 1) {
            posicion.x += 1;
            opcion = new estado(posicion, actual, "abajo");
            opcion.calcularF(actual, meta);
            abierto.add(opcion);
        }

        posicion = actual.getPosicion();
        if (posicion.y - 1 >= 0 && juego.getLaberinto()[posicion.x][posicion.y - 1] != 1) {
            posicion.y -= 1;
            opcion = new estado(posicion, actual, "izquierda");
            opcion.calcularF(actual, meta);
            abierto.add(opcion);
        }

        posicion = actual.getPosicion();
        if (posicion.y + 1 <= juego.getLaberinto()[0].length - 1 && juego.getLaberinto()[posicion.x][posicion.y + 1] != 1) {
            posicion.y += 1;
            //System.out.println("posicion = "+posicion);
            opcion = new estado(posicion, actual, "derecha");
            opcion.calcularF(actual, meta);
            abierto.add(opcion);
        }
    }
}
