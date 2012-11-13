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
    }
  
    void steer(PVector dir)
    {
        vel.add(dir);
    }
  
    void render()
    {
        if(arrived)
          p.fill(235,0,147);
        else
          p.fill(color);
          
        p.ellipse(pos.x, pos.y, 3, 3);
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
