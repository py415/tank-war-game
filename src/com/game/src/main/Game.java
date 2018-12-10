package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * @author Philip Yu
 */
public class Game extends Canvas implements Runnable {

    // THREADING
    private boolean running = false;
    private Thread thread;

    // CONSTANTS
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private final String TITLE = "Game";

    // VARIABLES
    private BufferedImage world = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private boolean isShooting = false;

    // OBJECTS
    private Player player;
    private Controller c;
    private Textures tex;
    private Enemy enemy;
    private Pickup pickup;
    public static STATE State = STATE.MENU;

    public LinkedList<EntityA> entityListA;
    public LinkedList<EntityB> entityListB;
    public static int HEALTH = 100 * 2;
    private Menu menu;
    public LinkedList<EntityC> entityListC;

    private void tick() {

        if (State == STATE.GAME) {
//            player.tick();
//            enemy.tick();
            c.tick();
            pickup.tick();
        }

    }

    public static void main(String[] args) {

        Game game = new Game();

        game.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        game.setMaximumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        game.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }

    private synchronized void start() {

        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    private synchronized void stop() {

        if (!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);

    }

    @Override
    public void run() {

        init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();

            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            render();
            frames++;


            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("\nFPS: " + frames);
                System.out.println("TICKS: " + updates);
                updates = 0;
                frames = 0;
            }
        }

        stop();

    }

    private void init() {

        requestFocus();

        tex = new Textures();

        addKeyListener(new KeyInput(this));
        addMouseListener(new MouseInput());

        c = new Controller(this);

        player = new Player(200, 200, tex, this, c);
        enemy = new Enemy(300, 200, tex, this, c);

        menu = new Menu();

        entityListA = c.getEntityA();
        entityListB = c.getEntityB();
        entityListC = c.getEntityC();

        entityListA.add(player);
        entityListB.add(enemy);

        pickup = new Pickup(100, 100, tex);

    }

    public static int getScreenWidth() {

        return SCREEN_WIDTH;

    }

    public static int getScreenHeight() {

        return SCREEN_HEIGHT;

    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////

        g.drawImage(world, 0, 0, getWidth(), getHeight(), this);

        if (State == STATE.GAME) {

            int bgWidth = tex.background.getWidth();
            int bgHeight = tex.background.getHeight();

            int fillBgX = SCREEN_WIDTH / bgWidth;
            int fillBgY = SCREEN_HEIGHT / bgHeight;

            for (int col = 0; col <= fillBgY; ++col) {
                for (int row = 0; row <= fillBgX; ++row) {
                    g.drawImage(tex.background, row * bgWidth, col * bgHeight, bgWidth, bgHeight, null);
                }
            }

            // HEALTH BAR
            g.setColor(Color.RED);
            g.fillRect(5, 5, 200, 50);

            if (HEALTH > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(5, 5, HEALTH, 50);
            } else if (HEALTH <= 0) {
                g.setColor(Color.RED);
            }

            g.drawString("Health: " + HEALTH, 5, 75);

            g.setColor(Color.WHITE);
            g.drawRect(5, 5, 200, 50);

            // HEALTH BAR
            g.setColor(Color.BLUE);
            g.fillRect(SCREEN_WIDTH - 205, 5, 200, 50);

            if (HEALTH > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(SCREEN_WIDTH - 205, 5, HEALTH, 50);
            } else if (HEALTH <= 0) {
                g.setColor(Color.BLUE);
            }

            g.drawString("Health: " + HEALTH, SCREEN_WIDTH - 205, 75);

            g.setColor(Color.WHITE);
            g.drawRect(SCREEN_WIDTH - 205, 5, 200, 50);

//            player.render(g);
//            enemy.render(g);
            c.render(g);
            pickup.render(g);

        } else if (State == STATE.MENU) {

            menu.render(g);

        }

        //////////////////////////////////
        g.dispose();
        bs.show();


    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            player.setVelX(0);
        } else if (key == KeyEvent.VK_A) {
            player.setVelX(0);
        } else if (key == KeyEvent.VK_S) {
            player.setVelY(0);
        } else if (key == KeyEvent.VK_W) {
            player.setVelY(0);
        } else if (key == KeyEvent.VK_ENTER) {
            isShooting = false;
        }

        if (key == KeyEvent.VK_L) {
            enemy.setVelX(0);
        } else if (key == KeyEvent.VK_J) {
            enemy.setVelX(0);
        } else if (key == KeyEvent.VK_K) {
            enemy.setVelY(0);
        } else if (key == KeyEvent.VK_I) {
            enemy.setVelY(0);
        } else if (key == KeyEvent.VK_SPACE) {
            isShooting = false;
        }

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (State == STATE.GAME) {
            if (key == KeyEvent.VK_D) {
                player.setVelX(5);
            } else if (key == KeyEvent.VK_A) {
                player.setVelX(-5);
            } else if (key == KeyEvent.VK_S) {
                player.setVelY(5);
            } else if (key == KeyEvent.VK_W) {
                player.setVelY(-5);
            } else if (key == KeyEvent.VK_ENTER && !isShooting) {
                isShooting = true;
                Bullet bullet = new Bullet(player.getX() + 32, player.getY() - 25, tex, this, c);
                entityListC.add(bullet);
                c.addEntity(bullet);
            }

            if (key == KeyEvent.VK_Q) {
                System.out.println("\nExiting...");
                System.exit(1);
            }

            if (key == KeyEvent.VK_L) {
                enemy.setVelX(5);
            } else if (key == KeyEvent.VK_J) {
                enemy.setVelX(-5);
            } else if (key == KeyEvent.VK_K) {
                enemy.setVelY(5);
            } else if (key == KeyEvent.VK_I) {
                enemy.setVelY(-5);
            } else if (key == KeyEvent.VK_SPACE && !isShooting) {
                isShooting = true;
                Bullet bullet = new Bullet(enemy.getX() + 32, enemy.getY() - 25, tex, this, c);
                entityListC.add(bullet);
                c.addEntity(bullet);
            }
        }

    }

    public enum STATE {

        MENU,
        GAME

    }

} // end class Game
