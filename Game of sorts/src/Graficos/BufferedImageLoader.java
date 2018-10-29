/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *Se encarga de cargar las imagenes en un Buffer tomando la ruta en donde se encuentra la imagen.
 * @author kevin Avevedo
 */
public class BufferedImageLoader {
    
    private BufferedImage image;
    /**
     * Conviente a una imagen Buffer y la retorna.
     * @param path La ruta de procedencia de la imagen.
     * @return La imagen cargada.
     * @throws IOException 
     */
    public BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }
    
}
