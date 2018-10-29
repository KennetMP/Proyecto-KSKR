package Algoritmos_de_ordenamiento;

import java.util.ArrayList;

public class Familia {    
    
    //Constructor
    public Familia(){
    }
    
    /**
     * Metodo para asiganar los padre, hijo izquierdo y el hijo derecho al dragon
     * @param L la lista de los dragones(moldes) a los que se les va a asiganar familia
     */
    public void family(ArrayList<Molde> L){
        for(int i =0; i*2 +2 <L.size();i++ ){
            if(i==0){
                L.get(i).setHi(L.get((i*2)+1));
                L.get(i).setHd(L.get((i*2)+2));
            }
            else{
                L.get(i).setHi(L.get((i*2)+1));
                L.get(i).setHd(L.get((i*2)+2));
                L.get(i).setPadre(L.get((i-1)/2));
            }
        }
    }

}
