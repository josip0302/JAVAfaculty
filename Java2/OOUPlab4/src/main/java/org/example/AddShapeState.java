package org.example;

public class AddShapeState implements State{

    private GraphicalObject prototype;
    private DocumentModel model;
    public AddShapeState(DocumentModel model, GraphicalObject prototype) {
        this.model=model;
        this.prototype=prototype;
    }
    @Override
    public void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown) {

        GraphicalObject object =prototype.duplicate();


        object.translate(mousePoint.difference(object.getHotPoint(0)));
       object.translate(new Point(-10,-50));
        model.addGraphicalObject(object);

    }

    @Override
    public void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown) {

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
