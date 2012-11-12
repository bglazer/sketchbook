import processing.core.*;
import java.util.ArrayList;

public class heart extends PApplet {
    
    Particle p;
    ArrayList<Particle> particles, tmp_particles;
    PImage heart;
    int[] color_choices;

    int num_active = 10;
    int threshold = 4;
    int parts_added = 15;
    int steps = 0;

    int num_particles; 

    public void setup()
    {
      size(600,600);
      
      noStroke();
      
      heart = loadImage("heart.png");
      image(heart,width/4,height/4);
      loadPixels();
    
      color_choices = new int[3];
      color_choices[0] = color(158,0,100);
      color_choices[1] = color(235,94,136);
      color_choices[2] = color(158,63,92);

      num_particles = 0;
      
      tmp_particles = new ArrayList<Particle>();
      
    //  for(int i = 0; i < num_particles; i++)
    //  {
    //    particles.add(new Particle(randVec(0, 300), randVec(0, 300))); 
    //  }
      
      for(int i = 0; i < height; i+=random(2,4))
      {
        for(int j = 0; j < width; j+=random(2,4))
        {
          if(red(pixels[i*width + j]) == 255)
          {
            tmp_particles.add(new Particle(randVec(0, width), new PVector(j,i), color_choices[(int)(random(0,3))], this)); 
            num_particles++;
          }
        }
      }

      particles = new ArrayList<Particle>(tmp_particles.size());
      ArrayList<Integer> nums = new ArrayList<Integer>(tmp_particles.size());
      
      for(int i = 0; i < tmp_particles.size(); i++)
        nums.add(i,i);

      for( int i = 0; i < tmp_particles.size(); i++)
      {
          int rand_i = (int)(random(0,nums.size()));
          int rand_num = nums.get(rand_i);
          Particle rand_part = tmp_particles.get(rand_num);
          particles.add(i, rand_part);
          nums.remove(rand_i);
      }

      background(183);
      
      noiseDetail(8, .5f);
      
    //  for(int i = 0; i < 300; i ++)
    //  {
    //    for(int j = 0; j < 300; j++)
    //    {
    //      float n = noise(i*.01,j*.01);
    //      
    //      if(n > .5)
    //        stroke(0);
    //      else
    //        stroke(255,0,0);
    //      
    //      point(i,j);
    //    }
    //  }
    }

    public void draw()
    {
        //fill(158,158,158,10);
        //rect(0,0,300,300);

        for( int i = 0; i < num_active; i++ )
        {
            p =  particles.get(i); 
            p.steer();
            p.move();
            p.render();
        }
        
        if(steps > threshold)
        {
            num_active = num_active > num_particles - parts_added ? num_active : num_active + parts_added;
            steps = 0;
        }     
        else
            steps++;
    }

    PVector randVec(float min, float max)
    {
      return new PVector(random(min,max), random(min,max));
    }

    public void keyPressed()
    {
      if( key == ' ' )
      {
        for( Particle p2 : particles )
        {
          p2.pos = randVec(0,300);
          p2.arrived = false;
        }
      }
    }

    public static void main(String args[])
    {
      PApplet.main(new String[] {"heart"});
    }
}
