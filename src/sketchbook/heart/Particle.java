package sketchbook.heart;
import processing.core.*;


public class Particle
{
    PVector pos;
    PVector vel;
    PVector target;
    int color;
    final float max_vel = .5f;
    boolean arrived; 
    PApplet p;

    Particle(PVector pos, PVector target, int color, PApplet parent)
    {
        p = parent;
        this.pos = pos;
        this.target = target;
        this.color = color;
    }
  
    void move()
    {
        if(distSquared(pos, target) > 4)
          pos = PVector.add(pos, vel);
        else
          arrived = true;
    
    }
  
    void steer()
    {
        vel = PVector.sub(target, pos);
        vel.normalize();

        float n = p.noise(pos.x * .01f, pos.y * .01f) * max_vel*2 - max_vel;
        float sign = Math.signum(n);
        
        PVector ninety = new PVector(sign * vel.y, sign * -vel.x);
        ninety.normalize();
        ninety.mult( n * 2 );
        
        vel = PVector.mult(vel, max_vel);
        
        vel = PVector.add(vel, ninety);
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
