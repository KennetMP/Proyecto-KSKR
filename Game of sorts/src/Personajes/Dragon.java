/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Graficos.Animaciones;
import Graficos.Controlador;
import Graficos.ObjetosJuego;
import Graficos.Colisiones;
import Principal.Juego;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import Entidades.EntidadDragon;
import Entidades.EntidadFuegoJugador;
import java.awt.Color;
import java.awt.Font;
import java.util.logging.Logger;


/**
 *Esta clase se encarga de dar los atributos de los dragones, además tiene los sprites para
 * mostrarlos en el juego. Tambien, tiene los metodos de tick() y render() que se actualizan en la pantalla.
 * @author kevin Avevedo
 */
public class Dragon extends ObjetosJuego implements EntidadDragon {
    


    
    Random r = new Random();

    
    Animaciones anim;
    
    private Personajes personajes;
    private Juego game;
    private Controlador c;
   
    private int resistencia;
    private int edad;
    private int velocidadRecarga;
    private String nombre;
    private String clase;
    private Dragon padre;
    private boolean disparando;
    private final static Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );

   


    /**
     * Constructor de la clase Dragon que ayuda a crear los dragones de manera sensilla.
     * @param x Contiene el valor de la coordenada en el eje x de la pantalla.
     * @param y Contiene el valor de la coordenada en el eje y de la pantalla.
     * @param personajes Es una clase que contiene la hoja de sprites de todos los personajes
     * @param c Controlador de movimiento de todos los personajes.
     * @param game El juego principal que tiene el control de las actualizaciones y listas de personajes.
     */
    public Dragon(double x, double y, Personajes personajes,Controlador c, Juego game){
        super( x, y); 
        this.personajes = personajes;
        this.c = c;
        this.game = game;
        
        this.nombre = "Draco" + game.dragonesNacidos;
        game.dragonesNacidos++;
        
        this.resistencia = 1 + r.nextInt(3);
        
        this.velocidadRecarga = 2 + r.nextInt(4);
        
        
        int edad = r.nextInt(1000);
        
        this.disparando = false;
        
        while(game.edades.contains(edad)){
            edad = r.nextInt(1000);
        }
        this.edad = edad;
        
        game.edades.add(this.edad);
        
        game.dracos.add(this);
     
        
        
       
        
        
        
        anim = new Animaciones(7,personajes.dragon[0],personajes.dragon[1],personajes.dragon[2],personajes.dragon[3]);
    }
    /**
     * Metodo que se llama en el juego n veces por segundo y que se encarga de controlar las colisiones entre
     * los dragones y las bolas de fuego del grifo.
     * Ademas por cada ciclo se le resta al eje x la variable x del dragon.
     */
    public void tick(){
        x -= 0.5; 
        if(resistencia == game.cont && disparando == false && x <= 1000){
            c.addEntity(new FuegoDragon(getX()-10, getY(),personajes,game,c,velocidadRecarga));
            
            disparando = true;
            
        }
        if(resistencia != game.cont){
            disparando = false;
        }
        
        if(this.resistencia == 0){
            log.info("Ha muerto "+ getNombre());
            c.removeEntity(this);
            game.dracos.remove(this);
            game.moldes.clear();
            game.ConvertDragonMolde();
            
            
            game.setDragonesVida();
            if(game.dracos.size() > 0){
                game.CO ++;
                //game.setLayout(game.CO);
                game.setPeticion(true);
            }
            if(game.CO == 5){
                game.CO = 0;
            }
       
        
           
            
        }
            
        
     
       if( x < 0){
           y = r.nextInt(600);
           x = 800;
           game.setVida(70);
       }
       for(int i = 0; i < game.ed.size(); i++){// Verificar si hay colision entre los dragones y las bolas de fuego del jugador
           EntidadFuegoJugador tempEnt = game.ed.get(i);
           if(Colisiones.Collision(this, tempEnt)){
               this.resistencia-=1;
               c.removeEntity(tempEnt);
            }
        }
       
       anim.runAnimation();
    }
    
    
    /**
     * Se encarga de obtener un rectangulo que rodea al dragon con x
     * pixeles de ancho y  y de ancho.
     * @return Un rectangulo.
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y,96,96);
        
    }
    public void Control(int xf, int yf){
        if(x > xf && y < yf){
            x--;
            y++;  
        }
        
        
    }
    /**
     * Se llama en el juego principal para mostrar el  sprite del jugador y
     * ademas muestra informacion del dragon en la parte superior del dragon.
     * @param g  Los graficos g, se obtienen desde la clase principal( ya que
     * desde ahi se llama a este metodo.
     */
    public void render(Graphics g){
        anim.drawAnimation(g, x, y, 0);
        Font fnt1 = new Font("arial",Font.BOLD,10);
        g.setFont(fnt1);
        g.setColor(Color.GREEN);
        g.drawString(nombre,(int)getX()+20,(int)getY() + 15);
    }

    /**
     * Se encarga de obtener el valor de la posicion en x.
     * @return El valor de la posicion en x.
     */
    @Override
    public double getX() {
        return x;
    }
    /**
     * Se encarga de modificar el valor de la variable x.
     * @param x  El valor de la posicion en x.
     */
    public void setX(double x){
        this.x = x;
    }
    /**
     * se encarga de obtener el valor de la posicion en y.
     * @return El valor de la posicion en y.
     */
    @Override
    public double getY() {
        return y;
    }
    /**
     * Se encarga de modificar el valor de la posicion en y
     * @param y El valor de la posicion y.
     */
    public void setY(double y){
        this.y = y;
    }
    /**
     * Se encarga de obtener el valor de la edad del dragon.
     * @return El valor de la edad del dragon.
     */
    public int getEdad(){
        return edad;
    }
    /**
     * Se encarga de modificar el valor de la edad del dragon.
     * @param edad El valor de la edad del dragon.
     */
    public void setEdad(int edad){
        this.edad = edad;
    }
    /**
     * Se encarga de obtener el valor de la resistencia del dragon.
     * @return El valor de la resistencia del dragon.
     */
    public int getResistencia() {
        return resistencia;
    }
    /**
     * Se encarga de modificar el valor de la resistencia del dragon.
     * @param resistencia El valor de la resistencia del dragon.
     */
    public void setResistencia(int resistencia){
        this.resistencia = resistencia;
    }
    /**
     * Se encarga de obtener el valor del nombre del dragon.
     * @return El valor del nombre del dragon.
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * Se encarga de modificar el valor del nombre del dragon.
     * @param nombre El valor del nombre del dragon.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }/**
     * Se encarga de obtener la clase que le corresponde al dragon. 
     * @return La clase del dragon.
     */
    public String getClase(){
        return clase;
    }
    /**
     * Se encarga de modificar la clase del dragon.
     * @param clase El valor de la clase del dragon.
     */
    public void setClase(String clase){
        this.clase = clase;
    }
    /**
     * Se encarga de modificar el padre del dragon.
     * @param padre El nuevo padre del dragon.
     */
    
    public void setPadre(Dragon padre){
        this.padre = padre;
    }
    /**
     * Se encarga de obtener el valor del padre del dragon.
     * @return El padre del dragon.
     */
    public Dragon getPadre(){
        return padre;
        
    }
    /**
     * Se encarga de obtener el valor de la velocidad de recarga del dragon.
     * @return El valor de la velocidad de recarga.
     */
    public double getVelocidadRecarga(){
        return velocidadRecarga;
    }
    /** 
     * Se encarga de modificar el valor de la velocidad de recarga del dragon.
     * @param Velocidad 
     */
    public void setVelocidadRecarga(int Velocidad){
        this.velocidadRecarga = velocidadRecarga;
    }

   

    
    
    
    
}
