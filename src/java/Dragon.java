

import javax.swing.*;
import java.awt.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dragon{
    @XmlElement
    private  String name;

    @XmlElement
    private int edad;
    @XmlElement
    private Dragon padre;
    @XmlElement
    private Dragon hi;
    @XmlElement
    private Dragon hd;
    @XmlElement
    private boolean raiz;
    @XmlElement
    private final String DragImg = "src/Juego/dragon.jpg";


    public Dragon(String name, int edad){
        this.name = "Dragon"+ name;
        this.edad= edad;
        //System.out.println("Creado "+ this.edad);

    }
    public Dragon (){
    }

   

    public int getEdad(){
        return this.edad;
    }

    public Dragon getPadre(){
        return padre;
    }

    public Dragon getHi(){
        return hi;
    }

    public Dragon getHd(){
        return hd;
    }

    public void setPadre(Dragon d){
            this.padre = d;
    }

    public void setHi(Dragon d){
            this.hi = d;

    }
    public void setHd(Dragon d){
        this.hd = d;

    }
    public boolean getRaiz(){
        return this.raiz;
    }

    public void setRaiz(boolean b){
        this.raiz = b;
    }
   public void setEdad(int n){
        this.edad= n;
}
    public String getName(){
        return this.name;
    }
}
