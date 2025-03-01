package hr.fer.oprpp1.hw08.jnotepadpp.translate;

import javax.swing.*;

public class LJlabel extends JLabel {
    private static final long serialVersionUID = 1L;
    String key;

    public LJlabel(String key,ILocalizationProvider lp) {
        this.key = key;
        this.setText(lp.getString(key));
    }
    void updateLabel(){
        this.setText(key);
    }
}
