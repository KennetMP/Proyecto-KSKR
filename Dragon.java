package Juego;

import javax.swing.*;
import java.awt.*;

public class Dragon extends Sprite{

    private  String name;
    private Fuego fuego;
    private int edad;
    private final String DragImg = "src/Juego/dragon.jpg";


    public Dragon(String name, int edad){
        this.name = "Dragon"+ name;
        this.edad= edad;
        System.out.println("Creado "+ this.edad);

        fuego = new Fuego(x, y);
        ImageIcon ii = new ImageIcon(DragImg);
        setImage(ii.getImage());

    }
    public void act(int direction) {

        this.x += direction;
    }
    public Fuego getFuego(){
        return fuego;
    }
    public void setName(String name){
        this.name+=name;
    }
}
