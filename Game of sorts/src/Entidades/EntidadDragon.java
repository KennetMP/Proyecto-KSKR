/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Personajes.Dragon;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *Interfaz que facilita el uso de las colisiones con los dragones.
 * @author kevin Avevedo
 */
public interface EntidadDragon {
    
    public void tick();
    
    public void render(Graphics g);
    
    public Rectangle getBounds();
    
    public double getX();
    
    public double getY();
    
    public int getEdad();
    
    public void setEdad(int edad);
    
    public int getResistencia();
    
    public void setResistencia(int resistencia);
    
    public String getNombre();
    
    public void setNombre(String nombre);
    
    public void setClase(String clase);
    
    public String getClase();
    
    public void setPadre(Dragon padre);
    
    public Dragon getPadre();
    
    public void setVelocidadRecarga(int velocidadRecarga);
    
    public double getVelocidadRecarga();
    


    
}
