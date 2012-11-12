import processing.core.*;

public class CLASSNAME extends PApplet {
    public void setup()
    {
    }

    public void draw()
    {
    }

    public static void main(String args[])
    {
      PApplet.main(new String[] {"CLASSNAME"});
    }
}

// Template for external classes 

import processing.core.*;

public class CLASSNAME2
{
    PApplet p;

    //Constructor
    public CLASSNAME(PApplet parent)
    {
        p = parent;
    }

    //All calls to processing functions are called on p. That is p.rect(x,y,width,height) etc.
}
