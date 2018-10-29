/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *Interfaz que facilita el uso de las colisiones con el grifo.
 * @author kevin Avevedo
 */
public interface EntidadJugador {
    
    public void tick();
    
    public void render(Graphics g);
    
    public Rectangle getBounds();
    
    public double getX();
    
    public double getY();
    
    public void setX(double x);
    
    public void setY(double y);
    
}
