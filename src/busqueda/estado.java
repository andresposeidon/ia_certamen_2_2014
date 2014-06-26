package busqueda;
/**
 *
 * @author Andrés Garcés
 */
import java.awt.Point;
import java.util.Objects;

public class estado implements Comparable<estado>{
    private estado antecesor;
    private String movimiento;
    private Point posicion;
    private double f, g, h;
    
   public estado(Point posicion, estado antecesor,String movimiento) {
        this.antecesor=antecesor;
        this.movimiento=movimiento;
        this.posicion=posicion;
        this.g=0;
        this.h=0;
        this.f=0;
    }
   public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }
    
    public double getH(){
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }
    
    public double getF(){
        return f;
    }
    
    public void setF(double f) {
        this.f = f;
    }

    public void calcularF(estado actual, estado objetivo) {

        this.g = actual.getG()+
                + Math.sqrt(((Math.pow(Math.abs(actual.getX() - this.getX()), 2))
                        + (Math.pow(Math.abs(actual.getY() - this.getY()), 2))));

        this.h = Math.sqrt(((Math.pow(Math.abs(objetivo.getX() - this.getX()), 2))
                + (Math.pow(Math.abs(objetivo.getY() - this.getY()), 2))));

        this.f = this.g + this.h;
    }

    public estado getAntecesor() {
        return antecesor;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public Point getPosicion() {
        Point punto;
        punto=new Point(posicion);
        return punto;
    }
    public void setPosicion(Point a){
        this.posicion.x=a.x;
        this.posicion.y=a.y;
    }

    @Override
    public String toString() {
        return posicion.toString();
    }
    
    public int getX(){
        return posicion.x;
    }
    
    public int getY(){
        return posicion.y;
    }
    

    @Override
    public int compareTo(estado o) {
        if (o.getF() == this.getF()) {
            return 0;
        } else if (this.getF() > o.getF()) {
            return 1;
        }

        return -1;
    }
  
     
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof estado))return false;
        return ((estado)obj).getPosicion().equals(this.getPosicion());
    }
      @Override
    public int hashCode() {
        //Hashcode generado por IDE
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.posicion);
        hash = 89 * hash + Objects.hashCode(this.antecesor);
        hash = 89 * hash + Objects.hashCode(this.movimiento);
        return hash;
    }
}
