package framework;

import window.BufferedImageLoader;

import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Texture {

    // PATHS
    private String bmp_path = "jar/resources/images/bmp/";
    private String png_path = "jar/resources/images/png/transparent/";
    private String exp_large_path = "jar/resources/images/png/frames/transparent/Explosion_large_transparent/";
    private String exp_small_path = "jar/resources/images/png/frames/transparent/Explosion_small_transparent/";
    private String pickup_path = "jar/resources/images/png/frames/transparent/Pickup_transparent/";
    private String weapon_path = "jar/resources/images/png/frames/transparent/Weapon_transparent/";
    private String null_path = "jar/resources/images/png/frames/null.png";
    private String ext_png = ".png";
    private String ext_bmp = ".bmp";
    private String ext_gif = ".gif";

    // IMAGES
    public BufferedImage[] explosion_large = new BufferedImage[8];
    public BufferedImage[] explosion_small = new BufferedImage[7];
    public BufferedImage[] spr_pickup = new BufferedImage[5];
    public BufferedImage[] spr_weapon = new BufferedImage[4];
    public BufferedImage[] weapon = new BufferedImage[3];
    public BufferedImage[] spr_shield = new BufferedImage[2];
    public BufferedImage[] spr_tank = new BufferedImage[2];
    public BufferedImage[] spr_wall = new BufferedImage[2];
    public BufferedImage title;
    public BufferedImage background;
    private BufferedImageLoader loader = new BufferedImageLoader();
    public SoundPlayer background_sound;
    public String exp_large_snd_path;
    public String exp_small_snd_path;

    public Texture() {

        getTextures();

    }

    private void getTextures() {

        BufferedImageLoader loader = new BufferedImageLoader();

        background_sound = new SoundPlayer(1, "jar/resources/sound/Music.wav");
        exp_large_snd_path = "jar/resources/sound/Explosion_large.wav";
        exp_small_snd_path = "jar/resources/sound/Explosion_small.wav";

        explosion_large[0] = loader.loadImage(exp_large_path + "explosion_large_frame_0" + ext_png);
        explosion_large[1] = loader.loadImage(exp_large_path + "explosion_large_frame_1" + ext_png);
        explosion_large[2] = loader.loadImage(exp_large_path + "explosion_large_frame_2" + ext_png);
        explosion_large[3] = loader.loadImage(exp_large_path + "explosion_large_frame_3" + ext_png);
        explosion_large[4] = loader.loadImage(exp_large_path + "explosion_large_frame_4" + ext_png);
        explosion_large[5] = loader.loadImage(exp_large_path + "explosion_large_frame_5" + ext_png);
        explosion_large[6] = loader.loadImage(exp_large_path + "explosion_large_frame_6" + ext_png);
        explosion_large[7] = loader.loadImage(null_path);

        explosion_small[0] = loader.loadImage(exp_small_path + "explosion_small_frame_0" + ext_png);
        explosion_small[1] = loader.loadImage(exp_small_path + "explosion_small_frame_1" + ext_png);
        explosion_small[2] = loader.loadImage(exp_small_path + "explosion_small_frame_2" + ext_png);
        explosion_small[3] = loader.loadImage(exp_small_path + "explosion_small_frame_3" + ext_png);
        explosion_small[4] = loader.loadImage(exp_small_path + "explosion_small_frame_4" + ext_png);
        explosion_small[5] = loader.loadImage(exp_small_path + "explosion_small_frame_5" + ext_png);
        explosion_small[6] = loader.loadImage(null_path);

        spr_pickup[0] = loader.loadImage(pickup_path + "pickup_frame_0" + ext_png);
        spr_pickup[1] = loader.loadImage(pickup_path + "pickup_frame_1" + ext_png);
        spr_pickup[2] = loader.loadImage(pickup_path + "pickup_frame_2" + ext_png);
        spr_pickup[3] = loader.loadImage(pickup_path + "pickup_frame_3" + ext_png);
        spr_pickup[4] = loader.loadImage(null_path);

        weapon[0] = loader.loadImage(weapon_path + "weapon_frame_0" + ext_png);
        weapon[1] = loader.loadImage(weapon_path + "weapon_frame_1" + ext_png);
        weapon[2] = loader.loadImage(weapon_path + "weapon_frame_2" + ext_png);

        spr_weapon[0] = loader.loadImage(png_path + "Shell" + ext_png);
        spr_weapon[1] = loader.loadImage(png_path + "Rocket" + ext_png);
        spr_weapon[2] = loader.loadImage(png_path + "Bouncing" + ext_png);
        spr_weapon[3] = loader.loadImage(weapon_path + "weapon_frame_2" + ext_png);

        spr_shield[0] = loader.loadImage(png_path + "Shield1" + ext_png);
        spr_shield[1] = loader.loadImage(png_path + "Shield2" + ext_png);

        spr_tank[0] = loader.loadImage(png_path + "Tank1" + ext_png);
        spr_tank[1] = loader.loadImage(png_path + "Tank2" + ext_png);

        spr_wall[0] = loader.loadImage(png_path + "Wall1" + ext_png);
        spr_wall[1] = loader.loadImage(png_path + "Wall2" + ext_png);

        title = loader.loadImage(bmp_path + "Title" + ext_png);
        background = loader.loadImage(bmp_path + "Background" + ext_bmp);

    }

} // end class Textures
