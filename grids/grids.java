import processing.core.*;
import naybur.grid.OIGrid;
import java.util.ArrayList;
import java.util.List;
import naybur.Point;

public class grids extends PApplet {
    private int scale_x = 1024, scale_y = 700; 
    private int num_points = 500;
    private int nayburs = 4;
    private double[][] pos_range = { {0, scale_x}, {0, scale_y} };
    private int index = 0;
    OIGrid grid;
    ArrayList<Point> points;
    ArrayList<PVector> vels;
 
    public void setup()
    {
        size(scale_x,scale_y);
        points = createTestList(num_points, true, false);
        grid = new OIGrid(points, pos_range);
//        frameRate(10);
        vels = new ArrayList<PVector>();
        for(int i = 0; i < num_points; i++)
        {
            vels.add(PVector.random2D());
        }
    }

    public void draw()
    {
        background(127);
        grid.overhaul();
        for(int i = 0; i < num_points; i++)
        {
            Point point = grid.point_list.get(i);
            List<Integer> results = grid.findNearest(point,nayburs);
            float fx = (float) point.x;
            float fy = (float) point.y;

            for(Integer index : results)
            {
                Point result = grid.point_list.get(index);
                float rx = (float)result.x;
                float ry = (float)result.y;
                line(fx,fy,rx,ry);
            }
            
            PVector vel = vels.get(i);
            if(point.x + vel.x >= width || point.x + vel.x <= 0)
                vel.x = -vel.x;
            if(point.y + vel.y >= height || point.y + vel.y <= 0) 
                vel.y = -vel.y;
            point.x += vel.x; 
            point.y += vel.y;
        }
    }
    
    public void keyPressed()
    {
        redraw();
    }

    public ArrayList<Point> createTestList(int num_point_list, boolean integers, boolean negative)
    {
        ArrayList<Point> list = new ArrayList<Point>();

        for(int i = 0; i < num_point_list; i++)
        {
            double x = Math.random() * scale_x;
            double y = Math.random() * scale_y;
            
            if(integers)
            {
                x = Math.floor(x);
                y = Math.floor(y);
            }

            if(negative)
            {
                x = x * 2 - scale_x; 
                y = y * 2 - scale_y; 
            }

            Point p = new Point(x, y);
            list.add(p);
        }

        return list;
    } 

    public static void main(String args[])
    {
        PApplet.main(new String[] {"grids"});
    }
}
