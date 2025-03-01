package org.example;

public class Oval extends AbstractGraphicalObject{
    public Oval() {
        super(new Point[]{new Point(0,10),new Point(10,0)});
    }
    public Oval(int x1,int y1,int x2,int y2) {
        super(new Point[]{new Point(x1,y1),new Point(x2,y2)});
    }

    @Override
    public Rectangle getBoundingBox() {
        int x1=this.getHotPoint(0).getX(),y1=getHotPoint(0).getY(),x2=this.getHotPoint(1).getX(),y2=this.getHotPoint(1).getY();
        int w=Math.abs(x1-x2)*2;
        int h=Math.abs(y1-y2)*2;
        return new Rectangle(x1,y2,w,h);
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        int a = this.getHotPoint(0).getX() - this.getHotPoint(1).getX();
        int b = this.getHotPoint(1).getY() - this.getHotPoint(0).getY();
        int cx=this.getHotPoint(1).getX();
        int cy=this.getHotPoint(0).getY();
        double distance = Math.sqrt((Math.pow(mousePoint.getX() - cx, 2) / Math.pow(a, 2)) +
                (Math.pow(mousePoint.getY() - cy, 2) / Math.pow(b, 2)));
        return distance;
    }

    @Override
    public void render(Renderer r) {

      int a=this.getHotPoint(0).getX()-this.getHotPoint(1).getX();
      int b=this.getHotPoint(0).getY()-this.getHotPoint(1).getY();
      Point c=new Point(this.getHotPoint(1).getX(),this.getHotPoint(0).getY());
      int n=360;
      Point[] points=new Point[n];
      for(int i=0;i<n;i++){

          int x= (int) (a*Math.cos(i));
          int y= (int) (b*Math.sin(i));
          /*if(i>180){
             y*= -1 ;
          }
          if(i<270 && i>90){
              x*= -1 ;
          }*/
          points[i]=new Point(x,y).translate(c);
      }
      r.fillPolygon(points);
    }

    @Override
    public String getShapeName() {
        return "Oval";
    }

    @Override
    public GraphicalObject duplicate() {
        return new Oval(this.getHotPoint(0).getX(),this.getHotPoint(0).getY(),this.getHotPoint(1).getX(),this.getHotPoint(1).getY());
    }
}
