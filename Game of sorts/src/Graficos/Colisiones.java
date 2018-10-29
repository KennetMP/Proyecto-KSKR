/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import Entidades.EntidadDragon;
import Entidades.EntidadJugador;
import Entidades.EntidadFuegoDragon;
import Entidades.EntidadFuegoJugador;

/**
 *Se encarga de de controlar las coliones entre todos los objetos que pueden colisionar en
 * el juego.
 * @author kevin Avevedo
 */
public class Colisiones {
    
    
    /**
     * Se encarga de verificar las colisones entre el grifo y los dragones que existan en ese momento.
     * @param enta Entidad de la instancia del grifo.
     * @param entb Entidad de los dragones creados y con vida que podrian colisionar con el grifo.
     * @return Devuelve true si hay  alguna colision y false si no hay colisiones.
     */
    public static boolean Collision(EntidadJugador enta, EntidadDragon entb){ // Colisiones entre el jugador y los dragones

           
        if(enta.getBounds().intersects(entb.getBounds())){
            return true;
        }
        return false;
        
        
        
    }
    /**
     * Se encarga de verififar las colisiones entre el grifo y las bolas de fuego de los dragones existentes en el momento.
     * @param enta Entidad  del grifo.
     * @param entc Entidad de las bolas de fuego.
     * @return 
     */
    public static boolean Collision(EntidadJugador enta, EntidadFuegoDragon entc){ //Colision entre el jugador y los ataques de dragones

           
        if(enta.getBounds().intersects(entc.getBounds())){
            return true;
        }
        return false;
        
        
        
    }
    

    /**
     * Se encarga de verificar las colisiones entre los dragones y las bolas de fuego del grifo.
     * @param entb Entidad de los dragones.
     * @param entd Entidad de las bolas de fuego del grifo
     * @return 
     */
    public static boolean Collision(EntidadDragon entb, EntidadFuegoJugador entd){ // Colison entre los dragones y las bolas de fuego del jugador
        
           
        if(entb.getBounds().intersects(entd.getBounds())){
                return true;
            }
        
        return false;
        
    }
    
    

    
}
