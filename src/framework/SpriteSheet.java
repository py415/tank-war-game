package framework;

import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {

        this.image = image;

    }

    public BufferedImage grabImage24(int col, int row, int width, int height) {

        BufferedImage img = image.getSubimage((col * 24) - 24, (row * 24) - 24, width, height);
        return img;

    }

    public BufferedImage grabImage32(int col, int row, int width, int height) {

        BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        return img;

    }

    public BufferedImage grabImage64(int col, int row, int width, int height) {

        BufferedImage img = image.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
        return img;

    }

} // end class SpriteSheet