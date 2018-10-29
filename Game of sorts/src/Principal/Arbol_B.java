/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 *
 * @author kevin Avevedo
 */
public class Arbol_B {
    private int alto;
    private int largo;
    private int x;
    private int y;
    private String name;

    public Arbol_B(String name) {
        this.largo =25;
        this.alto = 40;
        this.name = name;
    }

    public int getAlto() {
        return alto;
    }

    public int getLargo() {
        return largo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void dibujar(ArrayList<Arbol_B> n){
        n.get(0).setX(x);
    }
    
}
