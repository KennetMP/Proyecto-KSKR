
import Algoritmos_de_ordenamiento.Molde;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase para manejar la lista de dragones (Moldes) para enviarlos
 * en formato xml y viceversa
 * @author kenne
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Moldes {
    
    /////////////////Atributos///////////////////////
    
    @XmlElement(name = "Dragon")
    private ArrayList<Molde> itemsList;
    private String info;
    
    //Constructor
    public Moldes() {
        itemsList = new ArrayList();
    }
    
    /**
     * get de la lista de dragones (moldes)
     * @return la lista de dragones (moldes)
     */
    public ArrayList<Molde> getItemsList() {
        return itemsList;
    }
 
    /**
     * set de la lista que contiene esta clase
     * @param itemsList asigna la lista de dragones que contiene esta clase
     */
    public void setItemsList(ArrayList<Molde> itemsList) {
        this.itemsList = itemsList;
    }
    /**
     * set de la info de esta clase
     * sirve para que la rest api sepa que hacer con la lista
     * @param s lo que se quiere poner
     */
    public void setInfo(String s){
        this.info = s;
    }
    /**
     * get se la info
     * @return la info para que la rest api sepa que hacer
     */
    public String getInfo(){
        return this.info;
    }
}
    
