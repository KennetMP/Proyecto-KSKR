/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Entidades.EntidadFuegoDragon;
import Graficos.Animaciones;
import Graficos.Controlador;
import Graficos.ObjetosJuego;
import Principal.Juego;
import Personajes.Personajes;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Esta clase mantiene los atributos de las bolas de fuego de los dragones. Permitiendo 
 * conocer su posicion en x y y. Además, permite mostrar los sprites de las bolas de 
 * fuego de los dragones.
 * @author kevin Avevedo
 */
public class FuegoDragon extends ObjetosJuego implements EntidadFuegoDragon{
    
  
    private Personajes personajes;
    
    private Juego game;
    Random r = new Random();
    private Controlador c;
    private int velocidadRecarga;
    
    Animaciones anim;
    /**
     * Metodo Constructor de la clase FuegoDragon. Facilita el uso de esta clase.
     * @param x Su posicion en x.
     * @param y Su posicion en y.
     * @param personajes La clase que contiene los sprites de todos los personajes.
     * @param game La clase principal del juego.
     * @param c El controlador de los movimientos de los personajes.
     * @param velocidadRecarga La velocidad de recarga de las bolas de fuego.
     */
    public FuegoDragon(double x, double y,Personajes personajes, Juego game,Controlador c, int velocidadRecarga){
        super( x, y);
        this.personajes = personajes;
        this.game = game;
        this.c = c;
        this.velocidadRecarga  = velocidadRecarga;
        
        anim = new Animaciones(7,personajes.fireballD[0],personajes.fireballD[1],personajes.fireballD[2],personajes.fireballD[3]);
        
      
    }
    
    
    
    
    /**
     * Se encarga de obtener un rectangulo que rodea a la bola de fuego del dragon y
     * que tiene 96 pixeles de alto y de ancho.
     * @return 
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y+32,16,16);
        
    }
    /**
     * Metodo que se encarga de controlar el movimiento de la bola del dragon
     * y además controla los límites en la pantalla.
    */
    public void tick(){
        x -= 1 + velocidadRecarga;
        if( x < 0){
           c.removeEntity(this);
        }
        anim.runAnimation();
    }
    
    /**
     * Se encarga de mostrar los sprites en la pantalla usando los sprites que se encuentran en la clase personajes
     * y por medio de la clase Animaciones dibuja los sprites.
     * @param g Los graficos que vienen desde el juego principal.
     */
    public void render(Graphics g){
        anim.drawAnimation(g, x, y, 0);
    }
    
    /**
     * Se encarga de obtener el valor de la posicion en x.
     * @return El valor del eje x.
     */
    public double getX(){
        return x;
    }
    
    

    /**
     * Se encarga de obtener el valor de la posicion en y.
     * @return El valor del eje y.
     */
    public double getY() {
        return y;
    }


}
