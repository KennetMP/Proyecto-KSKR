/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;


import Entidades.EntidadFuegoJugador;
import Graficos.Animaciones;
import Graficos.Controlador;
import Graficos.ObjetosJuego;
import Principal.Juego;
import Personajes.Personajes;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 *Se encarga de mantener los atributos de las bolas de fuego del grifo y controlar sus
 * movimientos y actualizaciones en pantalla usando sus metodos tick() y render().
 * @author kevin Avevedo
 */
public class FuegoGrifo extends ObjetosJuego implements EntidadFuegoJugador {
    
   
    private Personajes personajes;
    
    private Juego game;
    
    private Controlador c;
    
    Animaciones anim;
    /**
     * Metodo constructor de la clase FuegoGrifo
     * @param x La posicion inicial en x.
     * @param y La posicion inicial en y.
     * @param personajes Instancia de personajes para acceder a los sprites correspondientes.
     * @param game La instancia del juego.
     * @param c Instancia del controlador de los movimientos y actualizaciones de imagenes.
     */
    public FuegoGrifo(double x, double y,Personajes personajes, Juego game,Controlador c){
        super( x, y);
        this.personajes = personajes;
        this.game = game;
        this.c = c;
        
        anim = new Animaciones(7,personajes.fireball[0],personajes.fireball[1],personajes.fireball[2],personajes.fireball[3]);
        
      
    }
    
    
    
    
    /**
     * Se encarga de obtener un recatngulo de x pixeles de ancho y alto, los necesarios
     * para obtener buenos resultados en las colisiones con esta Entidad.
     * @return 
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y+32,16,16);
        
    }
    /**
     * Se encarga de controlar el movimiento y las colisones de las bolas de fuego del grifo.
     */
    public void tick(){
        x += 10;
        
        if(x >= 950){ // Limite de existencia en la pantalla.
            c.removeEntity(this);
            
        }
     
     
   
        anim.runAnimation();
    }
    /**
     * Se encarga de actualizar los sprites de las bolas de fuego del grifo.
     * 
     * @param g Los graficos que se le pasan desde la clase Juego.
     */
    public void render(Graphics g){
        anim.drawAnimation(g, x, y, 0);
    }
    /**
     * Se encarga de obtener la posicion en x de las bolas de fuego.
     * @return El valor de la posicion en x.
     */
    public double getX(){
        return x;
    }

    /**
     * Se encarga de obtener la posicion en y de las bolas de fuego.
     * @return El valor de la posicion en y.
     */
    public double getY() {
        return y;
    }


}
