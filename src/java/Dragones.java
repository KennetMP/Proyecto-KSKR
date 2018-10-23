
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
public class Dragones {
     @XmlElement(name = "item")
    private List<Dragon> itemsList;
 
    public Dragones() {
        itemsList = new ArrayList();
    }
 
    public List<Dragon> getItemsList() {
        return itemsList;
    }
 
    public void setItemsList(List<Dragon> itemsList) {
        this.itemsList = itemsList;
    }
 
}
    
