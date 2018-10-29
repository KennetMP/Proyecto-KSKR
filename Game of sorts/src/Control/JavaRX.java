/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Principal.Juego;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author kevin Avevedo
 */
public class JavaRX implements Runnable{
    
    
    private boolean conectado = false;
    private Juego juego;
    private Thread thread;
    private String mensaje = "";
    
    public JavaRX(Juego juego){
        this.juego = juego;
    }
    

    //Se crea una instancia de la librería PanamaHitek_Arduino
    private  PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private  final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (ino.isMessageAvailable()) {
                    
                    mensaje = ino.printMessage();
                    
                    if(mensaje.equals("Up")){
                        juego.Arriba();
                    }
                    else if(mensaje.equals("Down")){
                        juego.Abajo();
                    }
                    else if(mensaje.equals("Left")){
                        juego.Izquierda();
                    }
                    else if(mensaje.equals("Right")){
                        juego.Derecha();
                       
                    }
                    else if(mensaje.equals("Blanco")){
                        juego.Atacar();
                    }
                     Thread.sleep(50);
                    //Se imprime el mensaje recibido en la consola
                    System.out.println(mensaje);
                    mensaje = "";
                    juego.is_shooting = false;
                    juego.DetenerX();
                    juego.DetenerY();
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };
    
    public String getArduino() throws SerialPortException, ArduinoException{
            if (ino.isMessageAvailable()) {
                return ino.printMessage();
            }
            return null;
    }
    public void Iniciar(){
         try {
            ino.arduinoRX("COM5", 9600, listener);
            System.out.println("Arduino iniciado");
            conectado = true;
            thread = new Thread(this);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public  void main(String[] args) {
        try {
            ino.arduinoRX("COM5", 9600, listener);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
       /* while(conectado){
            
                    if(mensaje.equals("Up")){
                        juego.Arriba();
                        juego.DetenerY();
                    }
                    else if(mensaje.equals("Down")){
                        juego.Abajo();
                        juego.DetenerY();
                    }
                    else if(mensaje.equals("Left")){
                        juego.Izquierda();
                        juego.DetenerX();
                    }
                    else if(mensaje.equals("Right")){
                        juego.Derecha();
                        juego.DetenerX();
                    }
                    else if(mensaje.equals("Blanco")){
                        juego.Atacar();
                    }
            
        }*/

    }
}
