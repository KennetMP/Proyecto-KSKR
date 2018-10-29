/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Entidades.EntidadDragon;
import Principal.Juego;

/**
 *Se encarga controlar los eventos con el mouse, permitiendo seleccionar dragones en pantalla y obtener su informacion.
 * Ademas, se usa para simular el uso de los botones en la pantalla del menú. 
 * @author kevin Avevedo
 */
public class Mouse implements MouseListener {
    private Juego game;
  
    
    
    /**
     * Metodo constructor de la clase Mouse.
     * @param game El juego principal.
     */
    public Mouse(Juego game) {
        this.game = game;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

      for(int i = 0; i < game.eb.size(); i++){ // Verifica si se ha dado click a un dragon en pantalla
            EntidadDragon dragon = game.eb.get(i);
            if(mx >= dragon.getX() && mx <= dragon.getX()+90 && my >= dragon.getY() && my <= dragon.getY()+90){
                game.setNombre(dragon.getNombre());
                game.setEdad(Integer.toString(dragon.getEdad()) + " años de edad");
                game.setResistencia(Integer.toString(dragon.getResistencia()) +" puntos de resistencia");
                game.setClase(dragon.getClase());
                game.setVelocidadRecarga(Double.toString(dragon.getVelocidadRecarga()));
        
            }
        }
    if(mx >= 575 && mx <= 775){ // Verificar si se ha dado click en un boton del menu.
        if(my >=400 && my <= 500){
            Juego.state = Juego.STATE.GAME;
            
        }
    }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
