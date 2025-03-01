package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraphicalObject implements GraphicalObject{
    Point[] hotPoints;
    boolean[] hotPointSelected;
    boolean selected;
    List<GraphicalObjectListener> listener;

    public AbstractGraphicalObject(Point[] Points) {
        this.hotPoints = Points;
        hotPointSelected=new boolean[Points.length];
        for(int i =0;i<Points.length;i++){
            hotPointSelected[i]=false;
        }
        listener=new ArrayList<>();
        selected=false;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
          this.selected=selected;
          NotifyListeners(this);
    }

    private void NotifyListeners(AbstractGraphicalObject abstractGraphicalObject) {
        for (GraphicalObjectListener l:listener){
            l.graphicalObjectSelectionChanged(abstractGraphicalObject);
        }
    }

    @Override
    public int getNumberOfHotPoints() {
        return hotPoints.length;
    }

    @Override
    public Point getHotPoint(int index) {
        return hotPoints[index];
    }

    @Override
    public void setHotPoint(int index, Point point) {
        hotPoints[index]=point;
    }

    @Override
    public boolean isHotPointSelected(int index) {
        return hotPointSelected[index];
    }

    @Override
    public void setHotPointSelected(int index, boolean selected) {
           hotPointSelected[index]=selected;
    }

    @Override
    public double getHotPointDistance(int index, Point mousePoint) {
        return GeometryUtil.distanceFromPoint(hotPoints[index],mousePoint);
    }

    @Override
    public void translate(Point delta) {
        for (int i=0;i<hotPoints.length;i++){
            setHotPoint(i,hotPoints[i].translate(delta));
        }
    }

    @Override
    public String toString() {
      String rez="";
      for (int i =0;i<hotPoints.length;i++){
          rez+=hotPoints[i].toString()+"\n";
      }return rez;
    }

    @Override
    public void addGraphicalObjectListener(GraphicalObjectListener l) {
        listener.add(l);
    }

    @Override
    public void removeGraphicalObjectListener(GraphicalObjectListener l) {
         listener.remove(l);
    }


}
