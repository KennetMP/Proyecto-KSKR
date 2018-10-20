/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamientos.Algoritmos_de_ordenamiento;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kenne
 */
public class Ordenador {
   public void ordenarquicksort(ArrayList<Point> lista){
      lista=quicksort1(lista);
   }
   public ArrayList<Point> quicksort1(ArrayList <Point> numeros){
       return quicksort2(numeros,0,numeros.size());
   }
   public ArrayList<Point> quicksort2(ArrayList<Point> numeros,int izq, int der){
       if (izq>=der)
           return numeros;
        int i=izq,d=der;
       if (izq!=der){
           int pivote;
           Point aux;
           pivote=izq;
           while(izq!=der)
           {
            while(numeros.get(der).x>= numeros.get(pivote).x && izq<der)
               der--;
            while (numeros.get(der).x< numeros.get(pivote).x && izq<der)
                izq++;
            if (der!=izq){
               aux=numeros.get(der);
               numeros.set(der,numeros.get(izq));
               numeros.set(izq,aux);
                }
            if (izq==der){
                quicksort2(numeros,i,izq-1);
                quicksort2(numeros,izq+1,d);
            }
            }}
           else
                return numeros;
          return numeros;
   
   }
   public ArrayList<Point> selectionsort(ArrayList<Point> lista){
       for (int i=0;i<lista.size()-1;i++){
           int minIndex=i;
           for (int j=i +1;j<lista.size();j++){
               if(lista.get(j).x<lista.get(minIndex).x){
                   minIndex=j;
               }
           }
           Point temp=lista.get(minIndex);
           lista.set(minIndex, lista.get(i));
           lista.set(i,temp);
       }return lista;
       
   }
   public void ordenarselectionsort(ArrayList<Point> lista){
      Point aux;
      int cont1;
      int cont2;
      for (cont1=1;cont1<lista.size();cont1++){
          aux= lista.get(cont1);
          for (cont2=cont1-1;cont2>=0&&lista.get(cont2).x>aux.x;cont2--){
              lista.set(cont2+1,lista.get(cont2));
              lista.set(cont2,aux);
          }
      }
   }
}
