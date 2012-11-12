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
    if(pos.dist(target) > 2)
      pos = PVector.add(pos, vel);
    else
      arrived = true;
    
//    pos = PVector.add(pos, randVec);
  }
  
  void steer()
  {
    vel = PVector.sub(target, pos);
    vel.normalize();

    float n = p.noise(pos.x * .01f, pos.y * .01f) * max_vel*2 - max_vel;
    float sign = Math.signum(n);
    
    PVector ninety = new PVector(sign * vel.y, sign * -vel.x);
//    println(ninety);
    ninety.normalize();
    ninety.mult( n * 2 );
//    println(ninety);
    
    vel = PVector.mult(vel, max_vel);
    
    vel = PVector.add(vel, ninety);
    
//    vel.add(randVec(-1, 1));
    
  }
  
  void render()
  {
    if(arrived)
      p.fill(235,0,147);
    else
      p.fill(color);
      
    p.ellipse(pos.x, pos.y, 3, 3);
//    stroke(255,0,0);
//    point(pos.x, pos.y);
  }
  
  PVector randVec(float min, float max)
  {
    return new PVector(p.random(min,max), p.random(min,max));
  }
  
  PVector noiseVec()
  {
    return new PVector(p.noise(pos.x)*2-1,p.noise(pos.y)*2-1); 
  }
}
