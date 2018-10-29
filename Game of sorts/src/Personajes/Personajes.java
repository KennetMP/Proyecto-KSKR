/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Graficos.SpriteSheet;
import Principal.Juego;
import java.awt.image.BufferedImage;

/**
 *Se encarga de cargar las imagenes en arrays para simplificar el uso de los sprites.
 * @author kevin Avevedo
 */
public class Personajes {
    public BufferedImage[] player = new BufferedImage[4];
    public BufferedImage[] dragon = new BufferedImage[4];
    public BufferedImage[] fireball = new BufferedImage[4];
    public BufferedImage[] fireballD = new BufferedImage[4];
    public BufferedImage[] fireballD2 = new BufferedImage[4];
    private SpriteSheet ss,ss1,ss2,ss3;
    
    public Personajes(Juego game){
         ss = new SpriteSheet(game.getSpriteSheet());
         ss1 = new SpriteSheet(game.getSpriteSheetDragon());
         ss2 = new SpriteSheet(game.getSpriteSheetBullet());
         ss3 = new SpriteSheet(game.getSpriteSheetBulletD());
      
         
         getPersonajes();
    }
    /**
     * Se encarga de almacenar las imagenes cargadas y meterlas en arrays para cada entidad que necesite usar 
     * los sprites.
     */
    public void getPersonajes(){ 
        // SPRITES DEL GRIFO
        player[0] = ss.grabImage(1, 3, 96, 96);
        player[1] = ss.grabImage(2, 3, 96, 96);
        player[2] = ss.grabImage(3, 3, 96, 96);
        player[3] = ss.grabImage(4, 3, 96, 96);
        // SPRITES DE LOS DRAGONES
        dragon[0] = ss1.grabImage(1, 2, 96, 96);
        dragon[1] = ss1.grabImage(2, 2, 96, 96);
        dragon[2] = ss1.grabImage(3, 2, 96, 96);
        dragon[3] = ss1.grabImage(4, 2, 96, 96);
        // SPRITES DE LAS BOLAS DE FUEGO DEL GRIFO
        fireball[0] = ss2.grabImage(1, 3, 96, 96);
        fireball[1] = ss2.grabImage(2, 3, 96, 96);
        fireball[2] = ss2.grabImage(3, 3, 96, 96);
        fireball[3] = ss2.grabImage(4, 3, 96, 96);
        //SPRITES DE LAS BOLAS DE FUEGO DE LOS DRAGONES
        fireballD[0] = ss3.grabImage(1, 2, 96, 96);
        fireballD[1] = ss3.grabImage(2, 2, 96, 96);
        fireballD[2] = ss3.grabImage(3, 2, 96, 96);
        fireballD[3] = ss3.grabImage(4, 2, 96, 96);

        
    }
    
}
