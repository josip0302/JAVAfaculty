package hr.fer.oprpp1.hw08.jnotepadpp.translate;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FormLocalizationProvider extends LocalizationProviderBridge implements WindowListener {
    JFrame frame;
    public FormLocalizationProvider(ILocalizationProvider provider, JFrame frame) {
        super(provider);
        this.frame=frame;
        frame.addWindowListener(this);
    }


    @Override
    public void windowOpened(WindowEvent e) {
        this.connect();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.disconnect();
    }

    @Override
    public void windowClosed(WindowEvent e) {
          this.disconnect();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
