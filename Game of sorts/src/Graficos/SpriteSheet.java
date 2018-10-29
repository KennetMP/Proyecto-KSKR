/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.awt.image.BufferedImage;

/**
 *Se encarga de tomar sprites de una imagen cargada, y luego toma la imagen por partes.
 * @author kevin Avevedo
 */
public class SpriteSheet {
    
    private BufferedImage image;
    
    /**
     * Metodo Constructor de la clase SpriteSheet.
     * @param image Una imagen cargada.
     */
    public SpriteSheet(BufferedImage image){
        this.image = image;
        
    }
    /**
     * Se encarga de tomar ciertos pixeles de la imagen para usarlos como sprites.
     * @param col La columna.
     * @param row La fila.
     * @param width El ancho.
     * @param height El alto.
     * @return Una imagen que contiene cierta parte de la hoja de sprites.
     */

    public BufferedImage grabImage(int col, int row,int width, int height){
        BufferedImage img = image.getSubimage((col*96)-96,(row * 96)-96,width,height);
        return img;
}

    
}
