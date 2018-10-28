package Algoritmos_de_ordenamiento;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Molde{
   
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
    private int velocidad;
    @XmlElement
    private double y;


    public Molde(String name, int edad){
        this.nombre = "Dragon"+ name;
        this.edad= edad;
        //System.out.println("Creado "+ this.edad);

    }
    public Molde (){
    }

   

    public int getEdad(){
        return this.edad;
    }

    public Molde getPadre(){
        return padre;
    }

    public Molde getHi(){
        return hi;
    }

    public Molde getHd(){
        return hd;
    }

    public void setPadre(Molde d){
            this.padre = d;
    }

    public void setHi(Molde d){
            this.hi = d;

    }
    public void setHd(Molde d){
        this.hd = d;

    }
   public void setEdad(int n){
        this.edad= n;
}
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
       this.nombre = nombre;
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    public int getVelocidad(){
        return this.velocidad;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}
