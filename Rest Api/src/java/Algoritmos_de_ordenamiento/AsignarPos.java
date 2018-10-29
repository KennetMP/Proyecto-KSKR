/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos_de_ordenamiento;

import java.util.ArrayList;

/**
 * Clase para asignar posiciones en x y y a los dragones
 * para posicionarlos en pantalla
 * @author DELL / Saymon
 */
public class AsignarPos {
    
    //////////Atributos//////////////////
    
    private double aux = 2;
    private double x = 900;
    
    //Constructor
    public AsignarPos(){
        
    }
    
    /**
     * Metodo para asignar posiciones a estructuras lineales
     * @param l la lista de dragones(moldes) a los que se les va asignar la posicion
     */
    public void lineal(ArrayList<Molde> l){
        
        x = 900;
        for (int i=1; i<l.size();i++){
        if (x<l.get(i).getX()){
            x=l.get(i).getX();
        }
        }
        x-=50;       
        double y = 500;
        for(Molde m: l){
            if(y ==100){
               m.setX(x);
               m.setY(y);
               y= 500;
               x+=100;
               
            }
            else{
               m.setX(x);
               m.setY(y);
               y-=100;
            }
        }
    }
    /**
     * Metodo para asignar posiciones a estructuras jerarquicas (arboles)
     * @param L la lista de dragones(moldes) a los que se les va asignar la posicion
     */
    
    public void jerarquicas(ArrayList<Molde> L){
        //x = L.get(0).getX();
        x= 900;
        for(int i =0; i*2 +2 < L.size();i++ ){
            if(i==0){
                L.get(i).setX(x);
                L.get(i).setY(315);
                L.get((i*2)+1).setX(x + 146);
                L.get((i*2)+1).setY(L.get((i-1)/2).getY()-(aux*86));
                L.get((i*2)+2).setX(x + 146);
                L.get((i*2)+2).setY((aux*86)+L.get((i-1)/2).getY());
                }
            else{
                if( i < 3){
                    L.get((i*2)+2).setX(x + 292);
                    L.get((i*2)+2).setY((L.get(i).getY()+(aux*0.5)*86));
                    L.get((i*2)+1).setX(x + 292);
                    L.get((i*2)+1).setY(L.get(i).getY()-((aux*0.5)*86)); 
                }
                else{
                  L.get((i*2)+2).setX(x + 438);
                  L.get((i*2)+2).setY(L.get(i).getY()+((aux*0.25)*86));
                  L.get((i*2)+1).setX(x + 438);
                  L.get((i*2)+1).setY(L.get(i).getY()-((aux*0.25)*86)); 
                }
            }
        }
    }
    }
    

