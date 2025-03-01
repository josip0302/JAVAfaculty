package org.example;

public class SelectShapeState implements State{
    private DocumentModel model;

    public SelectShapeState( DocumentModel model) {
        this.model = model;
    }

    @Override
    public void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        System.out.println("Selektiraj");
        if(!ctrlDown) {
            for (Object o : model.list()) {
                GraphicalObject g = (GraphicalObject) o;
                g.setSelected(false);
            }
        }

                   GraphicalObject a=null;
                   double d=10;
            for (Object o:model.list()){
                GraphicalObject g= (GraphicalObject) o;
                if(g.selectionDistance(mousePoint)<d) {
                  a=g;
                }}
                   if(a!=null) {
                       System.out.println("ima nesto");
                       a.setSelected(true);
                       model.addSelected(a);
                   }else {
                       for (Object o : model.list()) {
                           GraphicalObject g = (GraphicalObject) o;
                           g.setSelected(false);
                       }
                   }



    }

    @Override
    public void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        for (Object o : model.list()) {
            GraphicalObject g = (GraphicalObject) o;
            g.setSelected(false);
        }
    }

    @Override
    public void mouseDragged(Point mousePoint) {

    }

    @Override
    public void keyPressed(int keyCode) {

    }

    @Override
    public void afterDraw(Renderer r, GraphicalObject go) {

    }

    @Override
    public void afterDraw(Renderer r) {

    }

    @Override
    public void onLeaving() {

    }
}
