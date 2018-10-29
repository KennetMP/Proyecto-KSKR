/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import Personajes.FuegoGrifo;
import Entidades.EntidadDragon;
import Entidades.EntidadJugador;
import Entidades.EntidadFuegoDragon;
import Entidades.EntidadFuegoJugador;
import Principal.Juego;
import Personajes.Dragon;
import Personajes.Personajes;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Logger;

/**
 *Se encarga de controlar el movimiento y las animaciones de la mayoria de entidades del juego, 
 * accediendo a los metodos tick() y render().
 * @author kevin Avevedo
 */
public class Controlador {
    private LinkedList<EntidadJugador> ea = new LinkedList<EntidadJugador>();
    private LinkedList<EntidadDragon> eb = new LinkedList<EntidadDragon>();
    private LinkedList<EntidadFuegoDragon> ec = new LinkedList<EntidadFuegoDragon>();
    private LinkedList<EntidadFuegoJugador> ed = new LinkedList<EntidadFuegoJugador>();
    private final static Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    private boolean iniciado = false;
    
    EntidadJugador enta;
    EntidadDragon entb;
    EntidadFuegoDragon entc;
    EntidadFuegoJugador entd;
    Random r = new Random();
    FuegoGrifo TempBullet;
    Dragon TempDragon;
    Personajes personajes;
    Juego game;
    
    
    /**
     * Metodo constructor de la clase Controlador
     * @param game La instancia del juego.
     * @param personajes La instancia de los personajes.
     */
    public Controlador(Juego game, Personajes personajes){
        this.game = game;
        this.personajes = personajes;
    
    }
    /**
     * Se encarga de llamar al metodo tick() de las entidades por medio de las listas
     * que contienen las entidades.
     */
    public void tick(){
        
        if(game.getDragonesVida() == 0){
            log.info("Se han eliminado todos los dragones de la oleada " + Integer.toString(game.getOleada()));
            game.setCantidadOleada(game.getCantidadOleada() + (game.getCantidadOleada() * 0.20));
            game.setDragonesVida((int)game.getCantidadOleada());
            game.setOleada();
            log.info("La siguiente oleada es "+ Integer.toString(game.getOleada()));
            
           
        }
        if(eb.size() == 0 && iniciado == true){
            createAnimacionAzar(930);
        }
        // A Class Jugador
        for(int i = 0; i < ea.size(); i++){
            enta = ea.get(i);
            
            enta.tick();
        }
        //B Class Dragones
        for(int i = 0; i < eb.size();i++){
            entb = eb.get(i);
            
            entb.tick();
        }
        //C Class FireballD
        for(int i = 0; i < ec.size();i++){
            entc = ec.get(i);
            
            entc.tick();
        }
        //D Class FireballJ
        for(int i= 0; i < ed.size();i++){
            entd = ed.get(i);
            
            entd.tick();
        
    }
    }
    
    /**
     * Se encarga de llamar al metodo render() por medio de las listas que contienen
     * a las entidades.
     * @param g 
     */
    public void render(Graphics g){
        //A Class Jugador
        for(int i = 0; i < ea.size(); i++){
            enta = ea.get(i);
            
            enta.render(g);
        }
        //B Class Dragones
        for(int i = 0; i < eb.size(); i++){
            entb = eb.get(i);
            
            entb.render(g);
        }
        //C Class Fireball Dragones
        for(int i = 0; i < ec.size(); i++){
            entc = ec.get(i);
            
            entc.render(g);
        }
        //D Class Fireball Jugador
        for(int i = 0; i < ed.size(); i++){
            entd = ed.get(i);
            
            entd.render(g);
        }
        
        
    }

    /**
     * Se encarga de añadir una nueva entidad de jugador.
     * @param ent Entidad de jugador.
     */
    public void addEntity(EntidadJugador ent){
        ea.add(ent);
    }
    public void removeEntity(EntidadJugador ent){
        ea.remove(ent);
    }
    /**
     * Se encarga de añadir una nueva entidad de Dragon.
     * @param ent Entidad de Dragon.
     */    
    public void addEntity(EntidadDragon ent){
        eb.add(ent);
    }
    public void removeEntity(EntidadDragon ent){
        eb.remove(ent);
    }
    /**
     * Se encarga de añadir una nueva entidad de las bolas de fuego de los dragones.
     * @param ent Entidad de las bolas de fuego de los dragones.
     */
    public void addEntity(EntidadFuegoDragon ent){
        ec.add(ent);
    }
    public void removeEntity(EntidadFuegoDragon ent){
        ec.remove(ent);
    }
    /**
     * Se encarga de añadir una nueva entidad de las bolas de fuego del grifo.
     * @param ent Entidad de las bolas de fuego del grifo.
     */
    public void addEntity(EntidadFuegoJugador ent){
        ed.add(ent);
    }
    public void removeEntity(EntidadFuegoJugador ent){
        ed.remove(ent);
    }
    

    public void createAnimacionAzar(int x){
        log.info("Se ha iniciado una mini oleada de 10 dragones");
        int y = 10 +r.nextInt(500);
        for(int i = 0; i < 10; i ++){
      
            addEntity(new Dragon(x,y,personajes,this,game));
            x  += 100;
            y = 10 + r.nextInt(600);
           
            
        }
    }


    /**
     * Se encarga de obtener una lista que contiene al grifo.
     * @return Una lista que contiene al grifo
     */

    public LinkedList<EntidadJugador> getEntityA(){
        return ea;
    }
    /**
     * Se encarga de obtener una lista que contiene a los dragones creados.
     * @return Una lista que contiene a los dragones creados.
     */
    public LinkedList<EntidadDragon> getEntityB(){
        return eb;
    }
    /**
     * Se encarga de obtener una lista que contiene a las bolas de fuego de los dragones creados.
     * @return Una lista que contiene a las bolas de fuego de los dragones creados.
     */
    public LinkedList<EntidadFuegoDragon> getEntityC(){
        return ec;
    }
    /**
     * Se encarga de obtener una lista que contiene a las bolas de fuego del grifo.
     * @return Una lista que contiene a las bolas de fuego del grifo.
     */
    public LinkedList<EntidadFuegoJugador> getEntityD(){
        return ed;
    }
    


    

}
