/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos_de_ordenamiento;


import java.util.ArrayList;

/**
 * Clase que contiene varios algoritmos de ordenamiento
 * @author kenne
 */
public class Ordenador1 {
    
    /**
     * Metodo de ordenamiento por QuickSort
     * @param list la lista que va a ser ordenada
     * @param valor si se va a ordenar de mayor a menor o viceversa
     * @return la lista ya ordenada
     * referencia: https://github.com/mircealungu/Playground/blob/master/Algos/java/qs/QuickSort.java
     */
 
    public ArrayList<Molde> QuickSort (ArrayList<Molde> list,int valor)
    {
        quicksort(list, 0, list.size()-1,valor);
        
        return list;
    }

    /*
        returns a sorted list
    */
    private void quicksort (ArrayList<Molde> list, int left, int right,int valor)
    {
        if (left < right)
        {
            int pindex = partition (list, left, right,valor);
            quicksort (list, left, pindex - 1,valor);
            quicksort (list, pindex + 1, right,valor);
        }

    }

    /*
        partitions the list with respect to the pivot
        specified by pindex
        to the left the smaller elements, to the right
        the larger elements
    */
    private int partition (ArrayList<Molde> list, int ileft, int iright,int valor)
    {
        int pivot = list.get(iright).getEdad(); // get value of pivot
        int ileftmostlarge = ileft; // starting of the large partition of the array

        // if i find something which is smaller i swap it
        for (int i = ileft; i < iright; i++)
        { if (valor==1){
            if (pivot > list.get(i).getEdad()) { // found an element smaller than the pivot
                swap(list, i, ileftmostlarge);
                ileftmostlarge = ileftmostlarge + 1;
            }
            
        }
        else {  if (pivot < list.get(i).getEdad()) { // found an element smaller than the pivot
                swap(list, i, ileftmostlarge);
                ileftmostlarge = ileftmostlarge + 1;
            }
            
        }
        }
        swap (list, ileftmostlarge, iright);
        return ileftmostlarge;
    }
    /**
     * función para realizar un swap de elementos en una lista
     * @param list la lista donde están los elementos
     * @param i indice del primer elemento
     * @param j  indice del segundo elemento
     */
    private void swap (ArrayList<Molde> list, int i, int j)
    {
        Molde aux = list.get(i);
        list.set(i, list.get(j));
        list.set(j, aux);
    }

    /**
     * Metodo Selection Sort
     * @param list la lista que se quiere ordenar
     * @param valor para ver si se ordena de menor a mayor o viceversa
     * @return la lista ordenada
     */
   public ArrayList<Molde> Selection (ArrayList<Molde> list,int valor){
       ordenarselectionsort(list,valor);
       int x = 900;
       int y = 600;
       for(Molde m: list){
            if(y ==200){
               m.setX(x);
               m.setX(y);
               y= 600;
               x+=100;
               
            }
            else{
               m.setX(x);
               m.setX(y);
               y-=100;
            }
        }
       return list;
   }
   private void ordenarselectionsort(ArrayList<Molde> lista, int valor){
      Molde aux;
      int cont1;
      int cont2;
      if (valor==1){
      for (cont1=1;cont1<lista.size();cont1++){
          aux= lista.get(cont1);
          for (cont2=cont1-1;cont2>=0&&lista.get(cont2).getVelocidadRecarga()>aux.getVelocidadRecarga();cont2--){
              lista.set(cont2+1,lista.get(cont2));
              lista.set(cont2,aux);
          }}}else{ for (cont1=1;cont1<lista.size();cont1++){
          aux= lista.get(cont1);
          for (cont2=cont1-1;cont2>=0&&lista.get(cont2).getVelocidadRecarga()<aux.getVelocidadRecarga();cont2--){
              lista.set(cont2+1,lista.get(cont2));
              lista.set(cont2,aux);
                  
                  }
      }
   }
}

    /**
     * Metodo para ordenar por medio del algoritmo Selection Sort
     * @param list la lista que se quiere ordenar
     * @param tipo si se quiere ordenar de mayor a menor o viceversa
     * @return la lista ya ordenada
     * Referencia: https://github.com/joeyajames/Java/blob/master/insertionSort.java
     */
   public ArrayList<Molde> insertionSort (ArrayList<Molde> list,int tipo) {
	int i, j, key;
        Molde temp;
        if (tipo==1){
	for (i = 1; i < list.size(); i++) {
		key = list.get(i).getEdad();
		j = i - 1;
		while (j >= 0 && key < list.get(j).getEdad()) {
			temp = list.get(j);
			list.set(j, list.get(j + 1));
			list.set(j + 1, temp);
			j--;
		}
	}
        int x = 900;
        int y = 600;
        for(Molde m: list){
            if(y ==200){
               m.setX(x);
               m.setY(y);
               y= 600;
               x+=100;
               
            }
            else{
               m.setX(x);
               m.setY(y);
               y-=100;
            }       
        }
	return list;
        }
        else{
            for (i = 1; i < list.size(); i++) {
		key = list.get(i).getEdad();
		j = i - 1;
		while (j >= 0 && key > list.get(j).getEdad()) {
			temp = list.get(j);
			list.set(j, list.get(j + 1));
			list.set(j + 1, temp);
			j--;
		}
	}
        int x = 900;
        int y = 600;
        for(Molde m: list){
            if(y ==200){
               m.setX(x);
               m.setY(y);
               y= 600;
               x+=100;
               
            }
            else{
               m.setX(x);
               m.setY(y);
               y-=100;
            }
        }
	return list;
        }
}
}
