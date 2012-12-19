import processing.core.*;


public class Particle
{
    PVector pos;
    PVector vel;
    int color;
    final float max_vel = .5f;
    boolean arrived; 
    PApplet p;

    Particle(PVector pos, PVector vel, int color, PApplet parent)
    {
        p = parent;
        this.pos = pos;
        this.vel = vel;
        this.color = color;
    }
  
    void move()
    {
        pos = PVector.add(pos, vel);
        
        //Bounds checking and roll over
        
        if(pos.x >= p.width-1)
           pos = new PVector(0, pos.y);
        else if(pos.x <= 0)
           pos = new PVector(p.width-1, pos.y);
        if(pos.y >= p.height-1)
           pos = new PVector(pos.x, 0);
        else if(pos.y <= 0)
           pos = new PVector(pos.x, p.height-1);
    }
  
    void steer(float noise_val)
    {
        noise_val = noise_val * 2 - 1;
                                       //right                      //left
        PVector norm = noise_val > 0 ? new PVector(vel.y, -vel.x) : new PVector(-vel.y, vel.x);

        norm.normalize();

        norm.mult(p.abs(noise_val));

        vel.mult(3);

        vel.add(norm);

        vel.normalize();
        
    }
  
    void render()
    {
        p.fill(color);
          
        p.ellipse(pos.x, pos.y, 1, 1);
    }

    PVector randVec(float min, float max)
    {
        return new PVector(p.random(min,max), p.random(min,max));
    }
  
    PVector noiseVec()
    {
        return new PVector(p.noise(pos.x)*2-1,p.noise(pos.y)*2-1); 
    }

    double distSquared(PVector v1, PVector v2)
    {
        return (Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y,2));
    }
}
