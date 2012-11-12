Particle p;
ArrayList<Particle> particles;
PImage heart;

void setup()
{
  size(300,300);
  
  noStroke();
  
  heart = loadImage("heart.png");
  image(heart,0,0);
  loadPixels();
  
  int num_particles = 0;
  
  particles = new ArrayList<Particle>();
  
//  for(int i = 0; i < num_particles; i++)
//  {
//    particles.add(new Particle(randVec(0, 300), randVec(0, 300))); 
//  }
  
  for(int i = 0; i < height; i+=random(5,7))
  {
    for(int j = 0; j < width; j+=random(5,7))
    {
      if(red(pixels[i*width + j]) == 255)
      {
        particles.add(new Particle(randVec(0, 300), new PVector(j,i))); 
        num_particles++;
      }
    }
  }
  
  background(183);
  
  noiseDetail(8, .5);
  
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

void draw()
{
  fill(255,255,255,100);
  rect(0,0,300,300);
  
  for( Particle p2 : particles )
  {
    p2.steer();
    p2.move();
    p2.render();
  }
  
}

PVector randVec(float min, float max)
{
  return new PVector(random(min,max), random(min,max));
}

void keyPressed()
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
