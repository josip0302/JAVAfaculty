package org.example;

public class LineSegment extends AbstractGraphicalObject{
    public LineSegment(Point point1,Point point2) {
        super(new Point[]{point1,point2});
    }

    public LineSegment() {
        super(new Point[]{new Point(0,0),new Point(0,10)});
    }



    @Override
    public Rectangle getBoundingBox() {
        int x1=this.getHotPoint(0).getX(),y1=getHotPoint(0).getY(),x2=this.getHotPoint(1).getX(),y2=this.getHotPoint(1).getY();
        return new Rectangle(this.getHotPoint(0).getX(),getHotPoint(0).getY(),x2-x1,y2-y1);
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        return GeometryUtil.distanceFromLineSegment(this.getHotPoint(0),this.getHotPoint(1),mousePoint);
    }

    @Override
    public void render(Renderer r) {

        r.drawLine(hotPoints[0],hotPoints[1]);
    }

    @Override
    public String getShapeName() {
        return "Linija";
    }

     @Override
    public GraphicalObject duplicate() {
        return new LineSegment(this.getHotPoint(0),this.getHotPoint(1));
    }
}
