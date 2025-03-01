package org.example;

public class Main {
    public static void main(String[] args) {
      TextEditorModel model = new TextEditorModel("Gori gora \n Gori borovina \n GORI BOOOOOROOOOOOOOVIIIIINAAAAA");
      model.print();
      TextEditor editor=new TextEditor(model);

    }
}