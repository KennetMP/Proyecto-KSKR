/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos_de_ordenamiento;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class AsignarPos {
    private double aux = 2;
    private static int constante = 86;
    private int num = 0;
    
    private double x = 900;
    
    public AsignarPos(){
        
    }
    
    public void linea(ArrayList<Molde> l){
        
        double x=l.get(0).getX() ;
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
    
    public void jerarquicas(ArrayList<Molde> L){
        x = L.get(0).getX();
        int nivel = 0;
        int aux2 = 0;
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
    

