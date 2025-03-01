package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocumentModel {
    public final static double SELECTION_PROXIMITY = 10;

    // Kolekcija svih grafičkih objekata:
    private List objects = new ArrayList<>();
    // Read-Only proxy oko kolekcije grafičkih objekata:
    private List roObjects = Collections.unmodifiableList(objects);
    // Kolekcija prijavljenih promatrača:
    private List listeners = new ArrayList<>();
    // Kolekcija selektiranih objekata:
    private List selectedObjects = new ArrayList<>();
    // Read-Only proxy oko kolekcije selektiranih objekata:
    private List roSelectedObjects = Collections.unmodifiableList(selectedObjects);

    // Promatrač koji će biti registriran nad svim objektima crteža...
    private final GraphicalObjectListener goListener = new GraphicalObjectListener() {

        @Override
        public void graphicalObjectChanged(GraphicalObject go) {

        }

        @Override
        public void graphicalObjectSelectionChanged(GraphicalObject go) {

        }
    };

    // Konstruktor...
    public DocumentModel() {

    }

    // Brisanje svih objekata iz modela (pazite da se sve potrebno odregistrira)
    // i potom obavijeste svi promatrači modela
    public void clear() {
        objects.clear();
        listeners.clear();
        selectedObjects.clear();

    }

    // Dodavanje objekta u dokument (pazite je li već selektiran; registrirajte model kao promatrača)
    public void addGraphicalObject(GraphicalObject obj) {
        obj.addGraphicalObjectListener(new GraphicalObjectListener() {
            @Override
            public void graphicalObjectChanged(GraphicalObject go) {

            }

            @Override
            public void graphicalObjectSelectionChanged(GraphicalObject go) {
                if(go.isSelected()) {
                    notifyListeners();
                }
            }
        });
        objects.add(obj);
        if(obj.isSelected()){
            selectedObjects.add(obj);
        }
        notifyListeners();

    }

    // Uklanjanje objekta iz dokumenta (pazite je li već selektiran; odregistrirajte model kao promatrača)
    public void removeGraphicalObject(GraphicalObject obj) {
        objects.remove(obj);
        if(obj.isSelected()){
            selectedObjects.remove(obj);
        }

    }

    // Vrati nepromjenjivu listu postojećih objekata (izmjene smiju ići samo kroz metode modela)
    public List list() {
        return roObjects;
    }

    // Prijava...
    public void addDocumentModelListener(DocumentModelListener l) {
        listeners.add(l);

    }

    // Odjava...
    public void removeDocumentModelListener(DocumentModelListener l) {
       listeners.remove(l);
    }

    // Obavještavanje...
    public void notifyListeners() {
        for (Object l : listeners) {

            ((DocumentModelListener) l).documentChange();
        }
    }
    // Vrati nepromjenjivu listu selektiranih objekata
    public List getSelectedObjects() {
      return roSelectedObjects;
    }

    // Pomakni predani objekt u listi objekata na jedno mjesto kasnije...
    // Time će se on iscrtati kasnije (pa će time možda veći dio biti vidljiv)
    public void increaseZ(GraphicalObject go) {
        int i = objects.indexOf(go);
        objects.remove(i);
        objects.add(i+1,go);
    }

    // Pomakni predani objekt u listi objekata na jedno mjesto ranije...
    public void decreaseZ(GraphicalObject go) {
        int i = objects.indexOf(go);
        if(i>0) {
            objects.remove(i);
            objects.add(i - 1, go);
        }
    }

    // Pronađi postoji li u modelu neki objekt koji klik na točku koja je
    // predana kao argument selektira i vrati ga ili vrati null. Točka selektira
    // objekt kojemu je najbliža uz uvjet da ta udaljenost nije veća od
    // SELECTION_PROXIMITY. Status selektiranosti objekta ova metoda NE dira.
    public GraphicalObject findSelectedGraphicalObject(Point mousePoint) {
        double d=SELECTION_PROXIMITY;
        AbstractGraphicalObject rez=null;
        for (Object o:objects){
            AbstractGraphicalObject a= (AbstractGraphicalObject) o;
            double b=a.selectionDistance(mousePoint);
            if(b<=d){
                rez=a;
            }
        }
        return rez;

    }

    // Pronađi da li u predanom objektu predana točka miša selektira neki hot-point.
    // Točka miša selektira onaj hot-point objekta kojemu je najbliža uz uvjet da ta
    // udaljenost nije veća od SELECTION_PROXIMITY. Vraća se indeks hot-pointa
    // kojeg bi predana točka selektirala ili -1 ako takve nema. Status selekcije
    // se pri tome NE dira.
    public int findSelectedHotPoint(GraphicalObject object, Point mousePoint) {
        double d=SELECTION_PROXIMITY;
        int rez = -1;
        for(int i =0;i<object.getNumberOfHotPoints();i++){
            double x=object.getHotPointDistance(i,mousePoint);
            if(x<=d){
                rez=i;
            }

        }
        return rez;

    }
    public void addSelected(GraphicalObject object){
        if(objects.contains(object) && object.isSelected())
        selectedObjects.add(object);
    }
}
