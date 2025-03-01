package hr.fer.oprpp1.hw08.vjezba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class Prozor extends JFrame {
    private static final long serialVersionUID = 1L;
    private FormLocalizationProvider flp;
    public Prozor() throws HeadlessException {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(0, 0);
        setTitle("Demo");
        flp = new  FormLocalizationProvider(LocalizationProvider.getInstance(), this);
        initGUI();
        pack();

    }

    private void initGUI() {
        getContentPane().setLayout(new BorderLayout());


        JButton gumb = new JButton(
                new LocalizableAction("login", flp) {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        putValue(login,flp.getString(login));
                    }
                }
        );
        getContentPane().add(gumb, BorderLayout.CENTER);

        gumb.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                // Napravi prijavu...
                }
        });


        JMenu Languages=new JMenu("Languages");
        JMenuItem en = new JMenuItem("en");
        en.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalizationProvider.getInstance().setLanguage("en");
            }
        });
        JMenuItem hr = new JMenuItem("hr");
        hr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalizationProvider.getInstance().setLanguage("hr");
            }
        });
        JMenuItem de = new JMenuItem("de");
        de.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalizationProvider.getInstance().setLanguage("de");
            }
        });
        Languages.add(hr);
        Languages.add(en);
        Languages.add(de);
        JMenuBar bar=new JMenuBar();
        bar.add(Languages);

        bar.setSize(400,400);
        bar.setLayout(null);
        bar.setVisible(true);




        flp.addLocalizationListener(new ILocalizationListener() {
            @Override
            public void localizationChanged() {
               gumb.setText(flp.getString("login"));
            }
        });
        this.setJMenuBar(bar);
    }

    public static void main(String[] args) {
        /*
        if(args.length != 1) {
            System.err.println("Očekivao sam oznaku jezika kao argument!");
            System.err.println("Zadajte kao parametar hr ili en.");
            System.exit(-1);
        }
        final String jezik = args[0];

         */
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                LocalizationProvider.getInstance().setLanguage("en");
                new Prozor().setVisible(true);
            }});

    }


}
