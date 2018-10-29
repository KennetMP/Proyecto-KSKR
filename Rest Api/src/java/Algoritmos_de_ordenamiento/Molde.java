package Algoritmos_de_ordenamiento;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para manejar los atributos más necesarios de la clase dragon
 * y facilitar la conversion a xml y viceversa
 * @author DELL / Saymon
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Molde{
   
    //////////////////////Atributos///////////////////////
    
    @XmlElement
    private  String nombre;
    @XmlElement
    private int edad;
    @XmlElement
    private Molde padre;
    @XmlElement
    private Molde hi;
    @XmlElement
    private Molde hd;
    @XmlElement
    private String clase;
    @XmlElement
    private double x;
    @XmlElement
    private int velocidadRecarga;
    @XmlElement
    private double y;

    
    //Constructor
    public Molde (){
    }

    ///////////Geters y setters//////////////////
    
    /**
     * get de la edad
     * @return entero, la edad del dragon (molde)
     */
    public int getEdad(){
        return this.edad;
    }

    /**
     * get del padre del dragon (molde)
     * @return el padre del dragon (Molde)
     */
    public Molde getPadre(){
        return padre;
    }
    
    /**
     * get del hijo izquierdo del dragon
     * @return el hijo izquierdo del dragon (Molde)
     */
    public Molde getHi(){
        return hi;
    }
    /**
     * get del hijo derecho del dragon
     * @return el hijo derecho del dragon (molde)
     */
    public Molde getHd(){
        return hd;
    }
    /**
     * set del padre del dragon
     * @param d el dragon (molde) que se quiere poner como padre
     */
    public void setPadre(Molde d){
            this.padre = d;
    }
    /**
     * set del hijo izquierdo del dragon
     * @param d el dragon (molde) que se quiere poner como hijo izquierdo
     */
    public void setHi(Molde d){
            this.hi = d;

    }
    /**
     * set del hijo derecho del dragon
     * @param d  el dragon (molde) que se quiere poner como hijo derecho
     */
    public void setHd(Molde d){
        this.hd = d;

    }
    /**
     * set de la edad
     * @param n la edad que quiere que tenga el dragón
     */
   public void setEdad(int n){
        this.edad= n;
    }
   /**
    * get del nombre del dragon
    * @return el nombre del dragon
    */
    public String getNombre(){
        return this.nombre;
    }
    /**
     * set del nombre del dragon
     * @param nombre lo que se le quiere poner como nombre
     */
    public void setNombre(String nombre){
       this.nombre = nombre;
    }
    
    /**
     * get de la posicion x del dragon
     * @return la posicion x del dragon (molde)
     */
    public double getX() {
        return x;
    }
    /**
     * get de la posición y del dragon
     * @return la posicion y del dragon (molde)
     */
    public double getY() {
        return y;
    }
    /**
     * set de la posicion x del dragon
     * @param x la posición en x para el dragon(molde)
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * set de la posicion y del dragon
     * @param y la posición en y para el dragon(molde)
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * para acceder a la velocidad de recarga del dragon(molde)
     * @return la velocidad de recarga del dragon
     */
    public int getVelocidadRecarga() {
        return velocidadRecarga;
    }
    /**
     * se le asigna la velocidad de recarga al dragon (molde)
     * @param velocidadRecarga la velocidad de recarga que se le quiere asignar
     */
    public void setVelocidadRecarga(int velocidadRecarga) {
        this.velocidadRecarga = velocidadRecarga;
    }
    
}
