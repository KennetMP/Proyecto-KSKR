/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *Se encarga de brindar texto y botones para simular un menu.
 * @author kevin Avevedo
 */
public class Menu {
    
    public Rectangle botonJugar = new Rectangle(575,400,200,100);
    public Rectangle botonSalir = new Rectangle(575,550,200,100);
    /**
     * Se encarga de mostrar los graficos en la clase Juego.
     * @param g Los graficos que provienen de la clase Juego.
     */
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Font fnt0 = new Font("arial",Font.BOLD,70);
        Font ft1 = new Font("arial", Font.BOLD,40);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("GAME OF SORTS", 375, 100);
        
        g.setFont(ft1);
        g.drawString("JUGAR", botonJugar.x +30, botonJugar.y+60);
        g2d.draw(botonJugar);
        g.drawString("SALIR", botonSalir.x + 40, botonSalir.y + 60);
        g2d.draw(botonSalir);

        
    }
    
}
