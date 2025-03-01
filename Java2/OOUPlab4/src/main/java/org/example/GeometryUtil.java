package org.example;

public class GeometryUtil {
    public static double distanceFromPoint(Point point1, Point point2) {
      return Math.sqrt(Math.pow(point2.getX()-point1.getX(),2)+Math.pow(point2.getY()-point1.getY(),2));
    }

    public static double distanceFromLineSegment(Point s, Point e, Point p) {
        // Izračunaj koliko je točka P udaljena od linijskog segmenta određenog
        // početnom točkom S i završnom točkom E. Uočite: ako je točka P iznad/ispod
        // tog segmenta, ova udaljenost je udaljenost okomice spuštene iz P na S-E.
        // Ako je točka P "prije" točke S ili "iza" točke E, udaljenost odgovara
        // udaljenosti od P do početne/konačne točke segmenta.
        if(s.getX()>p.getX()){
            return distanceFromPoint(s,p);

        }else if (e.getX()<p.getX()){
            return distanceFromPoint(e,p);
        }else {
            System.out.println(Math.abs((e.getX()-s.getX())*(s.getY()-p.getY())-(s.getX()-p.getX())*(e.getY()-s.getY()))/distanceFromPoint(s,e));
            return Math.abs((e.getX()-s.getX())*(p.getY()-s.getY())-(p.getX()-s.getX())*(e.getY()-s.getY()))/distanceFromPoint(s,e);
        }

    }
}
