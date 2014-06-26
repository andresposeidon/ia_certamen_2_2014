/**
 *
 * @author Andrés Garcés
 */
import busqueda.busqueda;
import java.awt.Point;
import java.util.ArrayList;

public class main {
    
    public static void main(String args[]){
    Point inicial,meta;
    busqueda solucion;
    ArrayList<Point> monedas = new ArrayList<>();
    
    inicial= new Point(1,0);
    meta= new Point(3,7);
   
     monedas.add(new Point(1,2));
     monedas.add(new Point(3,1));
     monedas.add(new Point(1,4));

    solucion= new busqueda(inicial, meta, monedas);
    
    solucion.obtenerResultado();
    
   }
}
