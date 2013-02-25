import processing.core.*;
import java.nio.file.Files;
import java.io.File;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBuffer;
import javax.imageio.ImageIO;
import java.awt.image.WritableRaster; 
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.IOException;

public class letters extends PApplet {

    ArrayList<Particle> particles;
    float[][] noise_map;

    final float max_vel = 5;
    final int num_particles = 10;
    float[][] map;

    public void setup()
    {
        size(800, 300);
        
        particles = new ArrayList<Particle>();

        int[] colors = new int[3];
        colors[0] = 0;
        colors[1] = 0;
        colors[2] = 0;

        for(int i = 0; i < num_particles; i++)
        {
            PVector pos = new PVector(random(0,width), random(0, height));
            PVector vel = new PVector(0, random(max_vel/2,max_vel));
            int color = colors[(int)random(0,3)];
            particles.add(new Particle(pos, vel, color, this)); 
        }


        noise_map = new float[width][height];

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                noise_map[i][j] = noise(i*.01f, j*.01f);
                stroke(noise_map[i][j]*255);
                point(i,j);
            }
        }

        int[] text_map = new int[width*height];

        text_map = extractBytes("a.png");

        map = new float[width][height];

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                map[i][j] = noise_map[i][j] + (float)(text_map[j + i * height]/255f);
            }
        }
    }

    public int[] extractBytes (String ImageName) 
    {
        PImage p = loadImage(ImageName);
        
        image(p, 0, 0);   

        loadPixels();

        return pixels;
    }

    public void draw()
    {
//        background(127,127,127, 0);

        for(Particle p : particles)
        {
            p.steer(map[(int)p.pos.x][(int)p.pos.y]);
            p.move();
            p.render();
        }
    }

    public static void main(String args[])
    {
      PApplet.main(new String[] {"letters"});
    }
}


