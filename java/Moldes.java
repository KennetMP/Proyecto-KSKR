
import Algoritmos_de_ordenamiento.Molde;
import java.util.ArrayList;
import java.util.List;
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
 *
 * @author kenne
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Moldes {
    @XmlElement(name = "Dragon")
    private ArrayList<Molde> itemsList;
    private String info;
    public Moldes() {
        itemsList = new ArrayList();
    }
 
    public ArrayList<Molde> getItemsList() {
        return itemsList;
    }
 
    public void setItemsList(ArrayList<Molde> itemsList) {
        this.itemsList = itemsList;
    }
    public void setInfo(String s){
        this.info = s;
    }
    public String getInfo(){
        return this.info;
    }
}
    
