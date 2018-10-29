/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion_ServidorWeb;
import Principal.Juego;
import static Principal.Juego.state;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Se encarga de manejar las peticiones a la Rest Api.
 * @author kevin Avevedo
 */
public class CorreosGOS implements Runnable {
    
    private final static Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    private static Peticiones peticiones;
    private Thread thread;
    private boolean conectado = false;
    Juego juego;
    /**
     * Metodo constructor de la clase CorreosGOS.
     * @param juego  La instancia del juego.
     */
    public CorreosGOS(Juego juego){
        this.juego = juego;
        this.peticiones = new Peticiones(juego);
        
    }
    
    
    public void Detener(){
        try {
            conectado = false;
            thread.join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(CorreosGOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void Iniciar(){
        thread = new Thread(this);
        conectado = true;
        thread.start();
        log.info("Se ha iniciado correctamente el hilo en la clase CorreosGOS");
        
        
        
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(conectado){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            
            
 
            if(delta >= 1){
                if(juego.isPeticion() == true){
                    System.out.println(juego.getLayout());
                    peticiones.Post(juego.moldes,juego.getLayout());
                    // codigo para recibir los datos
                    System.out.println("Datos enviados");
                    System.out.println();
                    System.out.println();
                    peticiones.Get();
                    System.out.println("Datos Recibidos");
                    juego.setPeticion(false);
                }
                
                updates++;
                delta--;
                
            }
       
            frames++;
            
            
            if(System.currentTimeMillis() - timer > 1000){
             
                timer += 1000;
         
                updates = 0;
                frames = 0;
            }
            
        }
      
            if(juego.isPeticion() == true){
                
              
                peticiones.Post(juego.moldes,juego.getLayout());
                log.info("Se ha enviado una peticion de alineacion al servidor web");
                System.out.println("Datos enviados");
                System.out.println();
                System.out.println();
                peticiones.Get();
                log.info("Se ha recibido una alineacion de parte del servidor web");
                System.out.println("Datos Recibidos");
                juego.setPeticion(false);
            }
            
            
    }
               
        
        
    
    
}
