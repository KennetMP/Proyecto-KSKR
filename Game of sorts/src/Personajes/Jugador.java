/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Graficos.Animaciones;
import Graficos.Controlador;
import Graficos.ObjetosJuego;
import Graficos.Colisiones;
import Principal.Juego;
import java.awt.Graphics;
import java.awt.Rectangle;
import Entidades.EntidadJugador;
import Entidades.EntidadDragon;
import Entidades.EntidadFuegoDragon;

/**
 *Clase que contiene los atributos del grifo que utiliza el jugador
 * y sus recursos graficos. Implementa de ObjestosJuego para heredar el valor de las posiciones.
 * @author kevin Avevedo
 */
public class Jugador extends ObjetosJuego implements EntidadJugador {
    
    private double velX = 0;
    private double velY = 0;
    
    private Personajes personajes;
    Juego game;
    Controlador c;
    Animaciones anim;
    
    /**
     * Metodo Contructor de la clase jugador, que simplifica el uso de la clase.
     * @param x La posicion en el eje x.
     * @param y La posicion en el eje y.
     * @param personajes La clase personajes que contiene los recursos graficos.
     * @param game El juego principal.
     * @param c El controlador de los movientos y actualizaciones de los personajes.
     */
    public Jugador(double x, double y,Personajes personajes,Juego game,Controlador c){
        super(x , y);
        this.personajes = personajes;
        this.game = game;
        this.c = c;
        
        
        anim = new Animaciones(5,personajes.player[0],personajes.player[1],personajes.player[2],personajes.player[3]);
        

    }
    /**
     * Se encarga de controlar las colisiones del grifo, el movimiento
     * y las restricciones en la pantalla.
     */
    public void tick(){
        x += velX; 
        y += velY;
        
        
        if(x <= 0){
            x = 0;
        }
        if( x >= 1000-96){
            x = 1000-96;
        }
        if(y >= 700-96 ){
            y = 700-96;
        }
        if(y <= 0){
            y = 0;
        }
        for(int i = 0; i < game.eb.size();i++){ // Verificar si hay colisones entre el grifo y algun dragon.
            EntidadDragon tempEnt = game.eb.get(i);
            
            if(Colisiones.Collision(this, tempEnt)){ // Si hay colisiones se elimina el dragon y se le resta la vida al jugador.
                c.removeEntity(tempEnt);
                game.setVida(1);
                
            }
        }
        for(int i = 0; i < game.ec.size(); i++){ // Verificar si hay colisiones entre el grifo y las bolas de fuego de los dragones.
            EntidadFuegoDragon tempEnt = game.ec.get(i);
            
            if(Colisiones.Collision(this, tempEnt)){
                c.removeEntity(tempEnt);
                game.setVida(5);
            }
        }
        anim.runAnimation(); // Se llama para animar al personaje.
        
    }
    /**
     * Se encarga de mostrar los sprites del grifo en la pantalla principal.
     * @param g Los graficos que se le pasan desde la clase juego
     */
    public void render(Graphics g){
        anim.drawAnimation(g, x, y, 0);
        
    }
    /**
     * Se encarga de obtener el rectangulo del grifo con 96 pixeles de ancho y 96
     * de alto.
     * @return Un rectangulo del grifo.
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y+25,48,48);
        
    }
    /**
     * Se encarga de obtener el valor de la posicion en x.
     * @return El valor de la posicion en x.
     */

    public double getX(){
        return x;
    }
    /**
     * Se encarga de obtener el valor de la posicion en y.
     * @return El valor de la posicion en y.
     */
    public double getY(){
        return y;
    }
    /**
     * Se encarga de modificar el valor del 
     * @param x El valor del eje x.
     */
    public void setX(double x){
        this.x = x;
    }
    /**
     * Se encarga de modificar el valor de la posicion en y.
     * @param y El valor del eje y.
     */
    public void setY(double y){
        this.y = y;
    }
    /**
     * Se encarga de modifocar el valor de la velocidad en x.
     * @param velX La velocidad en x.
     */
    public void setVelX(double velX){
        this.velX = velX;
    }
    /**
     * Se encarga de modificar el valor de la velocidad en y
     * @param velY La velocidad en y.
     */
    public void setVelY(double velY){
        this.velY = velY;
    }


    
    
}
