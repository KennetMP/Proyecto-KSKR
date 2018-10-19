package Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Grifo {

    private String grifo = "grifo.jpg";
    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image imagen;

    public Grifo(){
        x=40;
        y=40;
        ImageIcon img = new ImageIcon(this.getClass().getResource(grifo));
        imagen = img.getImage();

    }

    public void mover(){
        x+= dx;
        y+=dy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImagen(){
        return imagen;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx= -1;

        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 1;

        }
        if (key == KeyEvent.VK_UP){
            dy= -1;
        }
        if (key ==KeyEvent.VK_DOWN){
            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx= 0;

        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 0;

        }
        if (key == KeyEvent.VK_UP){
            dy= 0;
        }
        if (key ==KeyEvent.VK_DOWN){
            dy = 0;
        }

    }

    }
