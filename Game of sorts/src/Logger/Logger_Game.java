/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author kevin Avevedo
 */
public class Logger_Game {
    //Crea una variable tipo logger
    private final static Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );

    private static void setupLogger() {
        LogManager.getLogManager().reset();
        log.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        log.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("Eventos.log", true);
            fh.setLevel(Level.FINE);
            log.addHandler(fh);
        } catch (java.io.IOException e) {
            //No detiene el programa pero desconecta de la consola
            log.log(Level.SEVERE, "El logger no esta funcionando", e);
        }
         /*
         Diferentes niveles en orden.
          OFF//SEVERE//WARNING//INFO//CONFIG//FINE//FINER//FINEST//ALL*/
    }
    //
    
    public static void Establish(){
            setupLogger();
    }
}
