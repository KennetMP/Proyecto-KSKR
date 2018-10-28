package Algoritmos_de_ordenamiento;

import java.util.ArrayList;

public class Familia {
    //private static int c = 100;
    private static ArrayList<Integer> lnum = new ArrayList();
    
    
    public Familia(){
        
    }
    
    
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
