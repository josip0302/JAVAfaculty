package org.example;

import java.awt.*;

public class G2DRendererImpl implements Renderer{
    private Graphics2D g2d;

    public G2DRendererImpl(Graphics2D g2d) {
       this.g2d=g2d;
    }
    @Override
    public void drawLine(Point s, Point e) {
        g2d.setColor(Color.BLUE);

        g2d.drawLine(s.getX(),s.getY(),e.getX(),e.getY());
    }

    @Override
    public void fillPolygon(Point[] points) {
        g2d.setColor(Color.YELLOW);
        Polygon polygon=new Polygon();
        for(Point p:points){
            polygon.addPoint(p.getX(),p.getY());
        }

        g2d.fillPolygon(polygon);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(0.5F));
        g2d.drawPolygon(polygon);
    }
}
