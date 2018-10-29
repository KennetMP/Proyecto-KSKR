/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Principal.Juego;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que se ancarga de controlar los eventos del teclado y llama a los metodo pertenecientes a la clase
 * Juego.S
 * @author kevin Avevedo
 */
public class Teclado extends KeyAdapter{ // se encarga de detectar las pulsaciones de teclas y luego las manda a la clase Juego 
    
    
    
    Juego game;
    
    /**
     * Metodo constructor de la clase Teclado que facilita su uso recibiendo la instancia de la clase Juego.
     * @param game La clase principal del juego.
     */
    public Teclado(Juego game){
        this.game = game;
        
    }
    
    
    
    /**
     * Se encarga de controlar los eventos de presion en el teclado.
     * @param e 
     */
    public void keyPressed(KeyEvent e){
        game.keyPressed(e);
    }
    /**
     * Se encarga de controlar los eventos cuando se deja de presionar las teclas.
     * @param e 
     */
    public void keyReleased(KeyEvent e){
        game.keyReleased(e);
        
    }
    
}
