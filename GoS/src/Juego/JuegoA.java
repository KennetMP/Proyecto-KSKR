package Juego;

import AVL.ArbolAVL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class JuegoA extends JPanel implements Runnable, Commons {

    private Dimension d;
    private ArrayList<Dragon> dracos;
    private Player player;
    private Shot shot;

    private ArbolAVL arbolAVL = new ArbolAVL();

    private final int ALIEN_INIT_X = 150;
    private final int ALIEN_INIT_Y = 5;
    private int direction = 1;
    private int deaths = 0;
    private int dragones= 100;

    private boolean ingame = true;
    private final String explImg = "src/Juego/explosion.png";
    private String message = "Game Over";

    private Thread animator;

    public JuegoA() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new Juego.JuegoA.TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);

        gameInit();
        setDoubleBuffered(true);
    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    public void gameInit() {
        Crear_Dragon c = new Crear_Dragon();
        dracos = c.crear(dragones);

        player = new Player();
        shot = new Shot();
       for(Dragon d : dracos){
            arbolAVL.add(d);
        }
        dracos = (ArrayList<Dragon>) arbolAVL.preOrden();
        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawAliens(Graphics g) {

        Iterator it = dracos.iterator();

        for (Dragon draco: dracos) {

            if (draco.isVisible()) {

                g.drawImage(draco.getImage(), draco.getX(), draco.getY(), this);
            }

            if (draco.isDying()) {

                draco.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            ingame = false;
        }
    }

    public void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    public void drawBombing(Graphics g) {

        for (Dragon a : dracos) {

            Fuego b = a.getFuego();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {

            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver() {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);
    }

    public void animationCycle() {

        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {

            ingame = false;
            message = "Game won!";
        }

        // player
        player.act();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Dragon draco: dracos) {

                int alienX = draco.getX();
                int alienY = draco.getY();

                if (draco.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + ALIEN_HEIGHT)) {
                        ImageIcon ii
                                = new ImageIcon(explImg);
                        draco.setImage(ii.getImage());
                        draco.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }

            }

            int x = shot.getX();
            x += 10;

            if (x > 250) {
                shot.die();
            } else {
                shot.setX(x);
            }
        }

        // aliens

        for (Dragon draco: dracos) {

            int x = draco.getX();

            if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
                System.out.println("yyyy");
                direction = -1;
                Iterator i1 = dracos.iterator();

                while (i1.hasNext()) {

                    Dragon a2 = (Dragon) i1.next();
                    a2.setX((int) 0.0001);
                }
            }

            if (x <20) {
                gameOver();
                ingame = false;
                /**
                direction = -1;

                Iterator i2 = dracos.iterator();
                while (i2.hasNext()) {

                    Dragon a = (Dragon) i2.next();
                    a.setX(a.getX() + 1);
                }*/
            }
        }

        Iterator it = dracos.iterator();

        while (it.hasNext()) {

            Dragon draco = (Dragon) it.next();

            if (draco.isVisible()) {

                int x = draco.getX();

                /**if (x > 20) {
                    ingame = false;
                    message = "Invasion!";
                }*/

                draco.act(-direction);
            }
        }

        // bombs
        Random generator = new Random();

        for (Dragon draco: dracos) {

            int shot = (int) (Math.random() * 10000);
            Fuego b = draco.getFuego();

            if (shot == 1 && draco.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setX(draco.getX());
                b.setY(draco.getY());
            }

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + PLAYER_HEIGHT)) {
                    ImageIcon ii
                            = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    b.setDestroyed(true);
                }
            }

            if (!b.isDestroyed()) {

                b.setX((int) (b.getX() -0.1));

                if (b.getX() <= 20) {
                    b.setDestroyed(true);
                }
            }
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {

            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 1000 - timeDiff;

            if (sleep < 0) {
                sleep = 200;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }

        gameOver();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (ingame) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }

}
