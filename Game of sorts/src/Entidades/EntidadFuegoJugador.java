/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;


/**
 *Interfaz que facilita el uso de las colisiones con las bolas de fuego del grifo.
 * @author kevin Avevedo
 */
public interface EntidadFuegoJugador {

    public void tick();
    
    public void render(Graphics g);
    
    public Rectangle getBounds();
    
    public double getX();
    
    public double getY();
    
}
    


