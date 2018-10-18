package Juego;

import java.util.ArrayList;

public class Crear_Dragon {
    //private static int c = 100;
    private static ArrayList<Integer> lnum = new ArrayList();



    public ArrayList crear(int c){
        System.out.println("eeeeee");
        int cont = 1;
        ArrayList list1 = new ArrayList();
        for(int i = 0; i < c; i++){
            int num = (int) (Math.random() * 1000);
            while(lnum.contains(num)){
                 num = (int) (Math.random() * 1000);
            }
            lnum.add(num);
            System.out.println(i);
            Dragon dragon = new Dragon(Integer.toString(cont),num);
            list1.add(dragon);
            cont++;
        }
        lnum.clear();
        return list1;
    }

}
