/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Conexion_ServidorWeb.Molde;
import Conexion_ServidorWeb.Peticiones;
import Conexion_ServidorWeb.CorreosGOS;
import Control.Mouse;
import Control.JavaRX;
import Control.Teclado;
import Graficos.BufferedImageLoader;
import Personajes.FuegoGrifo;
import Graficos.Controlador;
import Personajes.Dragon;
import Personajes.Personajes;
import Personajes.Jugador;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import Entidades.EntidadJugador;
import Entidades.EntidadDragon;
import Entidades.EntidadFuegoDragon;
import Entidades.EntidadFuegoJugador;
import Logger.Logger_Game;
import java.applet.Applet;
import java.applet.AudioClip;



/**
 *Clase principal de Game Of Sorts, permite manipular todas las demas clases y ademas 
 * proporciona la interfaz grafica del usuario.
 * @author kevin Avevedo
 */
public class Juego extends Canvas implements Runnable,KeyListener {

    public static final int ancho = 1350;
    public static final int alto = 700;
    
    public final String title = "Game of Sorts";
    
    private boolean running = false;
    private boolean loopingGame = false;
    private boolean loopingMenu = false;
    private Thread thread;
    
    
     private final static Logger log = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
     
    private BufferedImage image = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage spriteSheetBullet = null;
    private BufferedImage spriteSheetBulletD = null;
    private BufferedImage fondo = null;
    private BufferedImage fondoMenu = null;
    private BufferedImage Dragon = null;
    
    public boolean is_shooting = false;
    private  boolean peticion = false;
    private boolean inicializado = false;
    public int CO = 0;
    
    private int Oleada = 1;
    public int dragonesNacidos = 0;
    private double cantidadOleada = 100;
    private int dragonesVida;

   
    public int cont = 0;
    private Jugador p;
    private Controlador c;
    private Personajes personajes;
    private Menu menu;
    
    private static Peticiones peticiones;
    ///////////////////////////////////////////////////////////////////////////////////
    // Variables que contienen la informacion de los dragones que se seleccionan con click
    private String Layout = "";
    private String Nombre = "";
    private String Edad = "";
    private String Resistencia = "";
    private String VelocidadRecarga = "";
    private String Clase = "";
  
    ////////////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<Dragon> dracos ;
    public static ArrayList<Dragon> dragones_pantalla;
    public ArrayList<Integer> edades;
    
    private final AudioClip musica = Applet.newAudioClip(getClass().getResource("/Musica/juego.wav"));
    private final AudioClip musicaM = Applet.newAudioClip(getClass().getResource("/Musica/menu.wav"));
    public ArrayList<Molde> moldes = new ArrayList<Molde>();
    
    public LinkedList<EntidadJugador> ea;
    public LinkedList<EntidadDragon> eb;
    public LinkedList<EntidadFuegoDragon> ec;
    public LinkedList<EntidadFuegoJugador> ed;
    
    private static CorreosGOS correos;
    private JavaRX control;
    
    
    private int VIDA ;
    
    public static enum STATE{
        MENU,
        GAME
        
        
    };
    public static STATE state = STATE.MENU;
    
    /**
     * Se encarga de proporcionar los valores iniciales de los sprites, de los objetos que participan en
     * el juego y ademas inicia la primer oleada de dragones.
     */
    public void init(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            fondo = loader.loadImage("/Imagenes/F.png");
            fondoMenu = loader.loadImage("/Imagenes/FM.jpg");
            spriteSheet = loader.loadImage("/Imagenes/grifo.png");
            spriteSheetBullet = loader.loadImage("/Imagenes/fireball.png");
            spriteSheetBulletD = loader.loadImage("/Imagenes/fireballD.png");
            Dragon = loader.loadImage("/Imagenes/DragonM.png");
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        addKeyListener(new Teclado(this));
        addMouseListener(new Mouse(this));
        dracos = new ArrayList<Dragon>();
        edades = new ArrayList<Integer>();
        personajes = new Personajes(this);
        
        try{
            control  = new JavaRX(this);
            control.Iniciar();
            log.info("Se ha iniciado el control correctamente");
        }
        catch(Exception e){
            log.info("No se ha podido iniciar el control");
           
        }
        c = new Controlador(this,personajes);
        log.info("Se ha creado el controlador");
        //c.createAnimacionArboles(1000);
        //c.createAnimacionAzar(1200);
        
        //c.addEntity(new Dragon(900,400,personajes,c,this));
        
       
        p = new Jugador(0,200,personajes,this,c);
        log.info("Se ha creado el jugador");
        menu = new Menu();
        
        ea = c.getEntityA();
        eb = c.getEntityB();
        ec = c.getEntityC();
        ed = c.getEntityD();
        setLayout(CO);
        correos = new CorreosGOS(this);
        correos.Iniciar();
        
        dragonesVida = 100;
     
        
        }
    
    
    /**
     * Se encarga de iniciar el hilo del juego y permite que se ejecute un bucle infinito en el
     * metodo run.
     */
    private synchronized void start(){
        if(running)
            return; // get out of this method
            
        running = true;
        thread = new Thread(this);
        thread.start();
        log.info("El hilo se ha inciado correctamente");
        
    }
    /**
     * Se encarga de detener la ejecucion del juego, cambiando el valor de la variable
     * conectado a false.
     */
    private synchronized void stop(){
        if(!running)
            return; // get out of this method
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(1);
       
           
    }
    
    
    

    
    
    
    
    
    /**
     * Se encarga de iniciar el juego, ejecutando el metodo init y luego entra en un bucle
     * infinito en donde toma el control de las actualizaciones por segundo que se dan en la pantalla
     * además, se encarga de llamar a los metodos tick() y render().
     */
    @Override
    public void run() {
        
        init();
        log.info("Se han inicializado los datos principales del juego");
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        log.info("Ha inciado el bucle infinito");
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            
            
            

            if(delta >= 1){
           
                tick();
                updates++;
                delta--;
                
            }
            render();
            frames++;
            
            
            if(System.currentTimeMillis() - timer > 1000){
                if(state == STATE.GAME){
                    cont++;
                }
                timer += 1000;
            
    
                
                if(cont == 7 && state == STATE.GAME){
                    cont = 0;
                    
                }
                
                updates = 0;
                frames = 0;
            }
            
        }
        stop();
    }

    /**
     * Se encarga de controlar el movimiento de todos los objetos que interactuan en la pantalla
     * llamando a sus propios metodos tick().
    */
    private void tick(){
        if(state == STATE.GAME){

            p.tick();
            c.tick();
            

            if(c.getEntityB().size() == 0){
                c.createAnimacionAzar(1500);
            }
        }   
    }
    public void Dragon_Info(Graphics g,String nombre,String edad, String resistencia){
        Font fnt0 = new Font("arial",Font.BOLD,10);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("Nombre : "+nombre, 1010, 50);
        g.drawString("Edad : "+edad, 1010, 60);
        g.drawString("Resistencia : "+resistencia, 1010, 70);
        
    }
    /**
     * Se encarga de mostrar los sprites de todos los objetos que se tienen que mostrar en la 
     * pantalla, llamando a sus propios metodos render(), además, se encarga de mostrar el panel de informacion del juego
     */
    private void render(){
        BufferStrategy bs = this.getBufferStrategy(); // this refers to Canvas
        
        if(bs == null){
            
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////////////////////////////
        g.drawImage(image, 0, 0,getWidth(),getHeight(),this);
        
        if(state == STATE.GAME){
            
            if(!loopingGame){
                log.info("Se ha iniciado los loops de la musica del juego");
                musicaM.stop();
                musica.loop();
                loopingGame = true;
                loopingMenu = false;
            }
            g.drawImage(fondo, 0, 0, null);
            p.render(g);
            c.render(g);
            /////////////////////////
            // PANEL DE INFORMACION
            g.setColor(Color.BLACK);
            g.fillRect(1000, 0, 400, alto);
            //String de dragon status
            Font fnt0 = new Font("arial",Font.BOLD,20);
            g.setFont(fnt0);
            g.setColor(Color.WHITE);
            g.drawString("Dragon Status", 1100, 25);
            
            
           // Informacion de los dragones
            Font fnt1 = new Font("arial",Font.BOLD,15);
            g.setFont(fnt1);
            g.setColor(Color.BLUE);
            g.drawString("Nombre :   "+Nombre, 1010, 60);
            g.drawString("Edad :   "+Edad, 1010, 90);
            g.drawString("Resistencia :   "+Resistencia, 1010, 120);
            g.drawString("Clase :   "+Clase, 1010, 150);
            g.drawString("Velocidad de recarga :   "+VelocidadRecarga, 1010, 180);
            
            // Informacion de Layout
            Font fnt2 = new Font("arial",Font.BOLD,20);
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Layout Actual", 1100, 220);
            Font fnt3 = new Font("arial",Font.BOLD,15);
            g.setFont(fnt3);
            g.setColor(Color.BLUE);
            g.drawString(Layout, 1010, 250);
            
            
            // Informacion de los dragones con vida
            Font fnt4 = new Font("arial",Font.BOLD,20);
            g.setFont(fnt4);
            g.setColor(Color.YELLOW);
            g.drawString("Dragones con vida  en la oleada " + Integer.toString(Oleada)+" : "+dragonesVida , 300, 20);
            
            
            // Barra de vida del jugador
            g.setColor(Color.gray);
            g.fillRect(5, 5, 210, 30);
            
            g.setColor(Color.red);
            g.fillRect(5, 5, VIDA, 30);
            
            g.setColor(Color.white);
            g.drawRect(5, 5, 210, 30);
            ////////////////////////////
            if(VIDA <= 0){
                log.info("El jugador ha perdido sus puntos de vida");
                state = STATE.MENU;
                dracos.clear();
                musica.stop();
                ea.clear();
                eb.clear();
                ec.clear();
                ed.clear();
                CO = 0;
                loopingGame = false;
                inicializado = false;
             
            }
            
            
        }
        else if(state == STATE.MENU){
            g.drawImage(fondoMenu, 0, 0, null);
            menu.render(g);
            if(!loopingMenu){
                log.info("Se ha iniciado el loop de la musica del menu");
                VIDA = 210;
                musicaM.loop();
                loopingMenu = true;
                if(!inicializado){
                   c.createAnimacionAzar(1200);
                   inicializado = true;
                }
                
            }
            if(VIDA <= 0){
                dracos.clear();
                moldes.clear();
                musica.stop();
                
       
            }
        }
     
    
        g.dispose();
        bs.show();
        
    }
    /**
     * Se encarga de convertir la lista de dragones de tipo Dragon a tipo Molde, para luego 
     * ser enviados a la Rest Api para ordenarlos
     */
    public void ConvertDragonMolde(){
        log.info("Se ha convertido la lista de dragones a tipo molde para ser enviados al servidor web");
        for(int i = 0; i < dracos.size(); i++){
            Molde molde = new Molde();
            molde.setEdad(dracos.get(i).getEdad());
            molde.setNombre(dracos.get(i).getNombre());
            molde.setVelocidadRecarga((int)dracos.get(i).getVelocidadRecarga());
            
            moldes.add(molde);
            
        }   
    }


    
    /**
     * Se encarga de obtener el estado de la peticion
     * @return La variable peticion.
     */
    public boolean isPeticion() {
        return peticion;
    }
    /**
     * Se encarga de modificar el estado de la variable posicion.
     * @param peticion El estado de la variable peticion.
     */
    public void setPeticion(boolean peticion) {
        this.peticion = peticion;
    }
    public int getOleada(){
        return Oleada;
    }
    public void setOleada(){
        Oleada++;
       
    }
    public void setDragonesVida(){
        dragonesVida -= 1;
    }
    public void setDragonesVida(int dragonesVida){
        this.dragonesVida = dragonesVida;
    }
    public int getDragonesVida(){
        return dragonesVida;
    }
    public void setCantidadOleada(double cantidadOleada){
        this.cantidadOleada = cantidadOleada;
    }
    public double getCantidadOleada(){
        return cantidadOleada;
    }
    
    
 

    
    
    
    
    
        /**
         * Se encarga de crear la ventana y iniciar el hilo principal del juego.
         * @param args 
         */
        public static void main(String args[]){
 
        Juego game = new Juego();
                Logger_Game.Establish();
         log.info("Se ha iniciado la clase principal del juego");
        
        game.setPreferredSize(new Dimension(ancho, alto));
        game.setMaximumSize(new Dimension(ancho, alto));
        game.setMinimumSize(new Dimension(ancho, alto));
        
        
        JFrame frame = new JFrame(game.title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
        
        
        
       
        
        }
        /**
         * Se encarga de retornar la hoja de sprites del grifo.
         * @return La hoja de sprites del grifo.
         */
        public BufferedImage getSpriteSheet(){
            return spriteSheet;
        }
        /**
         * Se encarga de retornar la hoja de sprites de las bolas de fuego del grifo.
         * @return La hoja de sprites de las bolas de fuego del grifo.
         */
        public BufferedImage getSpriteSheetBullet(){
            return spriteSheetBullet;
        }
        /**
         * Se encarga de retornar la hoja de sprites de los dragones.
         * @return La hoja de sprites de los dragones.
         */
        public BufferedImage getSpriteSheetDragon(){
            return Dragon;
        }
        /**
         * Se encarga de retornar la hoja de sprites de las bolas de fuego de los dragones.
         * @return La hoja de sprites de las bolas de fuego de los dragones.
         */
        public BufferedImage getSpriteSheetBulletD(){
            return spriteSheetBulletD;
        }
        
 
        

        

        
        public void setVida(int VIDA){
            this.VIDA -= VIDA;
        }
        /**
         * Se encarga de modificar el nombre que aparece en el panel de informacion al
         * seleccionar a los dragones.
         * @param Nombre El nombre que se va a mostrar en el panel.
         */
        public void setNombre(String Nombre){
            this.Nombre = Nombre;
        }
        /**
         * Se encarga de modificar la edad que aparece en el panel de informacion al
         * seleccionar a los dragones.
         * @param Edad La edad que se va a mostrar en el panel.
         */
        public void setEdad(String Edad){
            this.Edad = Edad;
        }
        /**
         * Se encarga de modificar la resistencia que aparece en el panel de informacion al
         * seleccionar a los dragones.
         * @param Resistencia La resistencia que se va a mostrar en el panel.
         */
        public void setResistencia(String Resistencia){
            this.Resistencia = Resistencia;
        }
        /**
         * Se encarga de modificar la velocidad de recarga que aparece en el panel de informacion al
         * seleccionar a los dragones.
         * @param VelocidadRecarga La velocidad de recarga que se va a mostrar en el panel.
         */
        public void setVelocidadRecarga(String VelocidadRecarga){
            this.VelocidadRecarga = VelocidadRecarga;
        }
        /**
         * Se encarga de modificar la clase que aparece en el panel de informacion al
         * seleccionar a los dragones.
         * @param Clase La clase que se va a mostrar en el panel.
         */
        public void setClase(String Clase){
            this.Clase = Clase;
        }
        /**
         * Se encarga de obtener el tipo de ordenamiento que se le está pidiendo al servidor web.
         * @return Un String que indica el ordenamiento.
         */
        public String getLayout(){
            return Layout;
        }
        /**
         * Se encarga de modificar el layout que muestra el tipo de ordenamiento.
         * @param num El numero que indique el siguiente ordenamiento.
         */
        public void setLayout(int num){
            switch(num){
                case 1:
                    Layout = "Selection Sort";
                    break;
                case 2:
                    Layout = "Insertion Sort";
                    break;
                case 3:
                    Layout = "Quick Sort";
                    break;
                case 4:
                    Layout = "Arbol Binario";
                    break;
                case 5:
                    Layout = "Arbol AVL";
                    break;
                default:
                    break;
                    
                
            }
        }
        

        

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(state == STATE.GAME){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_D){
           
            p.setVelX(7);
        }
        else if(key == KeyEvent.VK_A){
            p.setVelX(-7);
            

        }
        else if(key == KeyEvent.VK_W){
            p.setVelY(-7);
          

        }
        else if(key == KeyEvent.VK_S){
            p.setVelY(7);
            

        }
        else if(key == KeyEvent.VK_SPACE && !is_shooting){
            is_shooting = true;
            c.addEntity(new FuegoGrifo(p.getX()+20,p.getY(),personajes,this,c));
            
        }
        else if(key == KeyEvent.VK_1){
            setLayout(1);
            

        }
        else if(key == KeyEvent.VK_2){
            setLayout(4);
            

        }

        }
    }
    /**
     * Se encarga de darle direccion hacia la derecha al jugador cuando se usa el control.
     */
    public  void Derecha(){
 
        p.setVelX(12);
      
       
    }
    /**
     * Se encarga de darle direccion hacia la izquierda al jugador cuando se usa el control.
     */
    public void Izquierda(){
    
        p.setVelX(-12);
    
       
    }
    /**
     * Se encarga de darle direccion hacia arriba al jugador cuando se usa el control.
     */
    public void Arriba(){
       
        p.setVelY(-12);
       
       
    }
    /**
     * Se encarga de darle direccion hacia abajo al jugador cuando se usa el control.
     */
    public void Abajo(){
      
        p.setVelY(12);
      
     
    }
    /**
     * Se encarga de dar la instruccion de ataque del grifo cuando se usa el control.
     */
    public void Atacar(){
       
        if(!is_shooting){
         is_shooting = true;
         c.addEntity(new FuegoGrifo(p.getX()+20,p.getY(),personajes,this,c));
        }
    }
    /**
     * Se encarga de detener el movimiento del jugador en el eje x cuando se usa el control.
     */
    public void DetenerX(){
        p.setVelX(0);
    }
    /**
     * Se encarga de detener el movimiento del jugador en el eje y cuando se usa el control.
     */
    public void DetenerY(){
        p.setVelY(0);
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_D){
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_A){
            p.setVelX(0);
            

        }
        else if(key == KeyEvent.VK_W){
            p.setVelY(0);
            

        }
        else if(key == KeyEvent.VK_S){
            p.setVelY(0);
          

        }
        else if(key == KeyEvent.VK_SPACE){
            is_shooting = false;
            
        }
    }
    
}
