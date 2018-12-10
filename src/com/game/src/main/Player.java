package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class Player extends GameObject implements EntityA {

    private double velX;
    private double velY;

    private Textures tex;
    private Game game;
    private Controller c;

    public Player(double x, double y, Textures tex, Game game, Controller c) {

        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;

    }

    public void tick() {

        x += velX;
        y += velY;

        checkBorders();
        checkCollision();

    }

    private void checkCollision() {

        for (int i = 0; i < game.entityListC.size(); ++i) {
            EntityC entityC = game.entityListC.get(i);

            if (Physics.checkCollision(entityC, this)) {
                c.removeEntity(entityC);
                Game.HEALTH -= 10;
                if (Game.HEALTH == 0) {
                    c.removeEntity(this);
                }
            }
        }

    }

    private void checkBorders() {

        // LEFT
        if (x <= 0)
            x = 0;

        // RIGHT
        if (x >= Game.getScreenWidth() - tex.player.getWidth())
            x = Game.getScreenWidth() - tex.player.getWidth();

        // TOP
        if (y < 0)
            y = 0;

        // BOTTOM
        if (y >= Game.getScreenHeight() - tex.player.getHeight())
            y = Game.getScreenHeight() - tex.player.getHeight();

    }

    public void render(Graphics g) {

        g.drawImage(tex.player, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {

        return (new Rectangle((int) x, (int) y, 64, 64));

    }

    public double getX() {

        return x;

    }

    public void setX(double x) {

        this.x = x;

    }

    public double getY() {

        return y;

    }

    public void setY(double y) {

        this.y = y;

    }

    public double getVelX() {

        return velX;

    }

    public void setVelX(double velX) {

        this.velX = velX;

    }

    public double getVelY() {

        return velY;

    }

    public void setVelY(double velY) {

        this.velY = velY;

    }

} // end class Player
