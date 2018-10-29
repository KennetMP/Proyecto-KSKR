/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion_ServidorWeb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Se encarga de crear un molde que almacene los atributos de los dragones existentes en la pantalla. 
 * Dichos atributos se necesitan para enviarlos al servidor web para modificarlos.
 * @author kevin Avevedo
 */
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
    private double y;
    @XmlElement
    private int velocidadRecarga;

    /**
     * Metodo Constructor de la clase Molde.
     * @param name El nombre del dragon.
     * @param edad La edad correspondiente del dragon.
     */
    public Molde(String name, int edad){
        this.nombre = "Dragon"+ name;
        this.edad= edad;
        //System.out.println("Creado "+ this.edad);

    }
    public Molde (){
    }
    /**
     * Se encarga de modificar la posicion en x de los moldes.
     * @param x La posicion en x de los moldes.
     */
    
    public void setX(double x){
        this.x = x;
    }
    /**
     * Se encarga de modificar la posicion en y de los moldes.
     * @param y La posicion en y de los moldes.
     */
    public void setY(double y){
        this.y = y;
    }
    /**
     * Se encarga de obtener la posicion en x de los moldes.
     * @return La posicion en x de los moldes.
     */
    public double getX(){
        return x;
    }
    /**
     * Se encarga de obtener la posicion en y de los moldes.
     * @return La posicion en y de los moldes.
     */
    public double getY(){
        return y;
    }

   
    /**
     * Se encarga de obtener el valor de la edad de los moldes.
     * @return El valor de la edad de los moldes.
     */
    public int getEdad(){
        return this.edad;
    }
    /**
     * Se encarga de obtener el padre de los moldes.
     * @return El padre de los moldes.
     */
    public Molde getPadre(){
        return padre;
    }

    public Molde getHi(){
        return hi;
    }

    public Molde getHd(){
        return hd;
    }
    /**
     * Se encarga de modificar el padre de los moldes.
     * @param d El padre de los moldes.
     */
    public void setPadre(Molde d){
            this.padre = d;
    }

    public void setHi(Molde d){
            this.hi = d;

    }
    public void setHd(Molde d){
        this.hd = d;

    }
    /**
     * Se encarga de modificar el valor de la edad de los moldes.
     * @param n El valor de la edad de los moldes.
     */
    public void setEdad(int n){
        this.edad= n;
    }
    /**
     * Se encarga de obtener el nombre de los moldes.
     * @return El nombre de los moldes.
     */
    public String getNombre(){
        return this.nombre;
    }
    /**
     * Se encarga de modificar el nombre de los moldes.
     * @param nombre El nombre de los moldes.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;

    }
    /**
     * Se encarga de obtener el valor de la velocidad de recarga de los moldes.
     * @return La velocidad de recarga de los moldes.
     */
    public int getVelocidadRecarga(){
        return velocidadRecarga;
    }
    /**
     * Se encarga de modificar el valor de la velocidad de recarga de los moldes.
     * @param velocidadRecarga La velocidad de recarga de los moldes.
     */
    public void setVelocidadRecarga(int velocidadRecarga){
        this.velocidadRecarga = velocidadRecarga;
    }
}


