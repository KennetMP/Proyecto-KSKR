/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.awt.Rectangle;

/**
 *
 * @author kevin Avevedo
 */
public class ObjetosJuego {
    public double x;
    public double y;
    
    public ObjetosJuego(double x, double y){
        this.x = x;
        this.y = y;
    }
    /**
     * Se encarga de obtener un rectangulo que rodea a la entidad
     * para usarla en colisones.
     * @param width
     * @param height
     * @return 
     */
    public Rectangle getBounds(int width, int height){
        return new Rectangle((int)x, (int)y,96,height);
        
    }
}
