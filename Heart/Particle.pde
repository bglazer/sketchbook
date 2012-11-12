class Particle
{
  PVector pos;
  PVector vel;
  PVector target;
  final float max_vel = 1;
  boolean arrived; 

  Particle(PVector pos, PVector target)
  {
    this.pos = pos;
    this.target = target;
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

    float n = noise(pos.x * .01f, pos.y * .01f) * 2 - 1;
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
      fill(255,0,0);
    else
      fill(59,242,81);
      
    ellipse(pos.x, pos.y, 3, 3);
//    stroke(255,0,0);
//    point(pos.x, pos.y);
  }
  
  PVector randVec(float min, float max)
  {
    return new PVector(random(min,max), random(min,max));
  }
  
  PVector noiseVec()
  {
    return new PVector(noise(pos.x)*2-1,noise(pos.y)*2-1); 
  }
}

