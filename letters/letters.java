import processing.core.*;
import java.util.ArrayList;

public class letters extends PApplet {

    ArrayList<Particle> particles;
    float[][] noise_map;

    final float max_vel = 5;
    final int num_particles = 100;

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
            PVector pos = new PVector(random(0,width), 0);
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
    }

    public void draw()
    {
        for(Particle p : particles)
        {
//            p.steer();
//            p.move();
//            p.render();
        }
    }

    public static void main(String args[])
    {
      PApplet.main(new String[] {"letters"});
    }
}


