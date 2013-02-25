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
    float[][] map;

    final float max_vel = 5;
    final int num_particles = 1000;

    final int opacity = 00;

    PImage letter;

    public void setup()
    {
        size(800, 300);
        
        noStroke();
        smooth();

        particles = new ArrayList<Particle>();

        int[] colors = new int[3];
        colors[0] = color(65,0,0);
        colors[1] = color(140,0,0);
        colors[2] = color(140,70,0);

        for(int i = 0; i < num_particles; i++)
        {
//            PVector pos = new PVector(random(0,width), random(0,height));
            PVector pos = new PVector(random(0,width), 0);
            PVector vel = new PVector(0, random(max_vel/2,max_vel));
            int color = colors[(int)random(0,3)];
            particles.add(new Particle(pos, vel, color, this)); 
        }


        map = new float[width][height];

        int[] text_map = new int[width*height];

        text_map = extractBytes("b.png");

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                map[i][j] = map[i][j] + (float)(text_map[j + i * height]/255f);
            }
        }
 
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                map[i][j] = noise(i*.05f, j*.05f);

                if(pixels[j*width + i] == color(0,0,0))
                {
                  map[i][j] = 1;
                }

//                println(noise(i*.01f, j*.01f));
//                map[i][j] = .5f;
//                stroke(map[i][j]*255);
//                point(i,j);
            }
        }

        background(127);
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
        fill(127,127,127,opacity);
        rect(0,0,width,height);

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


