package hr.fer.oprpp1.hw08.jnotepadpp;

import hr.fer.oprpp1.hw08.jnotepadpp.translate.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.translate.ILocalizationListener;
import hr.fer.oprpp1.hw08.jnotepadpp.translate.LocalizationProvider;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class JNotepadPP extends JFrame{
    private static final long serialVersionUID = 1L;

    private FormLocalizationProvider flp;
    private MultipleDocumentModel model;
    JPanel statusPanel;
    JLabel statusLabel2;
    JLabel statusLabel1;
    JLabel sat;

    public JNotepadPP() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(0, 0);
        setSize(600, 600);
        setTitle("JNotepad++");
        flp = new  FormLocalizationProvider(LocalizationProvider.getInstance(), this);
        model=new DefaultMultipleDocumentModel();
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

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
        });
        initGUI();
    }

    private void initGUI() {
       

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add((Component) model, BorderLayout.CENTER);
        createActions();
        createToolbars();
        createMenus();

        statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusPanel.setLayout(new BorderLayout());
        statusLabel1 = new JLabel("length:0");
        statusLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        statusLabel2 = new JLabel("Ln:0  Col:0  Sel:0");
        statusLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(statusLabel1,BorderLayout.WEST);
        statusPanel.add(statusLabel2,BorderLayout.CENTER);
        sat=new JLabel();
        Timer timer=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sat.setText(new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss").format(new Date()));
            }
        });
        timer.setRepeats(true);
        timer.start();
        statusPanel.add(sat,BorderLayout.EAST);


    }

    void updateStaus(JTextArea editor){

        Document doc = editor.getDocument();
        int z = editor.getLineCount();
        int x=doc.getLength();

        int y=0;

        Scanner s = new Scanner(editor.getText());
        while (s.hasNext()) {
            y+=s.nextLine().replace(" ","").length();
        }
        statusLabel1.setText("Length:"+x);
        statusLabel2.setText("Ln:"+z+"  Col:"+editor.getColumns()+"  Sel:"+0);
        editor.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                statusLabel2.setText("Ln:"+z+"  Col:"+editor.getColumns()+"  Sel:"+(e.getDot()-e.getMark()));
            }
        });

    }

    private void createActions() {
        createDocumentAction.putValue(
                Action.NAME,
                "Create");
        createDocumentAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("control N"));
        createDocumentAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_O);
        createDocumentAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to create new file.");


        openDocumentAction.putValue(
                Action.NAME,
                "Open");
        openDocumentAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("control O"));
        openDocumentAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_O);
        openDocumentAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to open existing file from disk.");

        saveDocumentAction.putValue(
                Action.NAME,
                "Save");
        saveDocumentAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("control S"));
        saveDocumentAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_S);
        saveDocumentAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to save current file to disk.");

        saveAsDocumentAction.putValue(
                Action.NAME,
                "Save as");
        saveAsDocumentAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(""));
        saveAsDocumentAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_S);
        saveAsDocumentAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to save current file to disk.");

        deleteSelectedPartAction.putValue(
                Action.NAME,
                "Delete selected text");
        deleteSelectedPartAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("F2"));
        deleteSelectedPartAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_D);
        deleteSelectedPartAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to delete the selected part of text.");

        toggleCaseAction.putValue(
                Action.NAME,
                "Toggle case");
        toggleCaseAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("control F3"));
        toggleCaseAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_T);
        toggleCaseAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to toggle character case in selected part of text or in entire document.");

        cutCaseAction.putValue(
                Action.NAME,
                "cut");
        cutCaseAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(""));
        cutCaseAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_T);
        cutCaseAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to cut.");

        copyCaseAction.putValue(
                Action.NAME,
                "copy");
        copyCaseAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("control C"));
        copyCaseAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_T);
        copyCaseAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to copy.");

        pasteCaseAction.putValue(
                Action.NAME,
                "paste");
        pasteCaseAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("control P"));
        pasteCaseAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_T);
        pasteCaseAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to paste.");

        statCaseAction.putValue(
                Action.NAME,
                "stat info");
        statCaseAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(""));
        statCaseAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_T);
        statCaseAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Used to get stats.");


        exitAction.putValue(
                Action.NAME,
                "Exit");
        exitAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("control X"));
        exitAction.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_X);
        exitAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Exit application.");

        to_uppercase.putValue(
                Action.NAME,
                "to uppercase");
        to_uppercase.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(""));
        to_uppercase.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_X);
        to_uppercase.putValue(
                Action.SHORT_DESCRIPTION,
                "to uppercase.");

        invert_case.putValue(
                Action.NAME,
                "invert case");
        invert_case.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(""));
        invert_case.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_X);
        invert_case.putValue(
                Action.SHORT_DESCRIPTION,
                "invert case.");

        to_lowercase.putValue(
                Action.NAME,
                "to lowercase");
        to_lowercase.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(""));
        to_lowercase.putValue(
                Action.MNEMONIC_KEY,
                KeyEvent.VK_X);
        to_lowercase.putValue(
                Action.SHORT_DESCRIPTION,
                "to lowercase");

        flp.addLocalizationListener(new ILocalizationListener() {
            @Override
            public void localizationChanged() {
              //  gumb.setText(flp.getString("login"));
                createDocumentAction.putValue(
                        Action.NAME,
                        flp.getString("Create"));

                saveAsDocumentAction.putValue(
                        Action.NAME,
                        flp.getString( "Save_as"));
                openDocumentAction.putValue(
                        Action.NAME,
                        flp.getString( "Open"));
                saveDocumentAction.putValue(
                        Action.NAME,
                        flp.getString( "Save"));
                deleteSelectedPartAction.putValue(
                        Action.NAME,
                        flp.getString( "Delete_selected_text"));
                toggleCaseAction.putValue(
                        Action.NAME,
                        flp.getString( "Toggle_case"));
                cutCaseAction.putValue(
                        Action.NAME,
                        flp.getString("cut"));
                copyCaseAction.putValue(
                        Action.NAME,
                        flp.getString( "copy"));
                pasteCaseAction.putValue(
                        Action.NAME,
                        flp.getString("paste"));
                statCaseAction.putValue(
                        Action.NAME,
                        flp.getString("stat_info"));
                exitAction.putValue(
                        Action.NAME,
                        flp.getString( "Exit"));
                to_uppercase.putValue(
                        Action.NAME,
                        flp.getString( "to_uppercase"));
                invert_case.putValue(
                        Action.NAME,
                        flp.getString( "invert_case"));

                to_lowercase.putValue(
                        Action.NAME,
                        flp.getString(  "to_lowercase"));
            }
        });
    }
    private Action createDocumentAction=new AbstractAction() {
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent e) {
            model.createNewDocument();
        }

    };
    private Action saveDocumentAction=new AbstractAction() {
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.getCurrentDocument().getFilePath()==null) {
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Save document");
                if(jfc.showSaveDialog(JNotepadPP.this)!=JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(
                            JNotepadPP.this,
                            "Ništa nije snimljeno.",
                            "Upozorenje",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Path openedFilePath = jfc.getSelectedFile().toPath();
                model.getCurrentDocument().setFilePath(openedFilePath);
            }
            model.saveDocument(model.getCurrentDocument(), model.getCurrentDocument().getFilePath());
            updateStaus(model.getCurrentDocument().getTextComponent());
        }

    };



    private Action saveAsDocumentAction=new AbstractAction() {
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Save document");
            if(jfc.showSaveDialog(JNotepadPP.this)!=JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(
                        JNotepadPP.this,
                        "Ništa nije snimljeno.",
                        "Upozorenje",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Path openedFilePath = jfc.getSelectedFile().toPath();
            model.saveDocument(model.getCurrentDocument(),openedFilePath);
        }

    };

    private Action deleteSelectedPartAction=new AbstractAction() {
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            if(len==0) return;
            int offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            try {
                doc.remove(offset, len);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }

        }
    };
    private Action  to_lowercase=new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            int offset = 0;
            if(len!=0) {
                offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            } else {
                len = doc.getLength();
            }
            try {
                String text = doc.getText(offset, len);
                text = toLowerCase(text);
                doc.remove(offset, len);
                doc.insertString(offset, text, null);
            } catch(BadLocationException ex) {
                ex.printStackTrace();
            }
        }
        private String toLowerCase(String text) {
            char[] znakovi = text.toCharArray();
            for(int i = 0; i < znakovi.length; i++) {
                char c = znakovi[i];
                if(Character.isUpperCase(c)) {
                    znakovi[i] = Character.toLowerCase(c);
                }
            }
            return new String(znakovi);
        }

    };
    private Action  to_uppercase=new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            int offset = 0;
            if(len!=0) {
                offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            } else {
                len = doc.getLength();
            }
            try {
                String text = doc.getText(offset, len);
                text = toUpperCase(text);
                doc.remove(offset, len);
                doc.insertString(offset, text, null);
            } catch(BadLocationException ex) {
                ex.printStackTrace();
            }
        }
        private String toUpperCase(String text) {
            char[] znakovi = text.toCharArray();
            for(int i = 0; i < znakovi.length; i++) {
                char c = znakovi[i];
                if(Character.isLowerCase(c)) {
                    znakovi[i] = Character.toUpperCase(c);
                }
            }
            return new String(znakovi);
        }


    };
    private Action  invert_case=new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            int offset = 0;
            if(len!=0) {
                offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            } else {
                len = doc.getLength();
            }
            try {
                String text = doc.getText(offset, len);
                text = inverse(text);
                doc.remove(offset, len);
                doc.insertString(offset, text, null);
            } catch(BadLocationException ex) {
                ex.printStackTrace();
            }

        }
        private String inverse(String text) {
            char[] znakovi = text.toCharArray();
            char[] znakovi2=new char[znakovi.length];
            for(int i = 0; i < znakovi.length; i++) {
                znakovi2[znakovi.length-i-1]= znakovi[i];

            }
            return new String(znakovi2);
        }
    };
    private Action  toggleCaseAction=new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            int offset = 0;
            if(len!=0) {
                offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            } else {
                len = doc.getLength();
            }
            try {
                String text = doc.getText(offset, len);
                text = changeCase(text);
                doc.remove(offset, len);
                doc.insertString(offset, text, null);
            } catch(BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        private String changeCase(String text) {
            char[] znakovi = text.toCharArray();
            for(int i = 0; i < znakovi.length; i++) {
                char c = znakovi[i];
                if(Character.isLowerCase(c)) {
                    znakovi[i] = Character.toUpperCase(c);
                } else if(Character.isUpperCase(c)) {
                    znakovi[i] = Character.toLowerCase(c);
                }
            }
            return new String(znakovi);
        }

    };
    private String copy="";
    private Action  copyCaseAction=new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            int offset = 0;
            if(len!=0) {
                offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            } else {
                len = doc.getLength();
            }
            try {
                copy = doc.getText(offset, len);
            } catch(BadLocationException ex) {
                ex.printStackTrace();
            }
        }};
    private Action  cutCaseAction=new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            int offset = 0;
            if(len!=0) {
                offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            } else {
                len = doc.getLength();
            }
            try {
                copy = doc.getText(offset, len);
                doc.remove(offset, len);
            } catch(BadLocationException ex) {
                ex.printStackTrace();
            }
        }};
    private Action  pasteCaseAction=new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int len = Math.abs(editor.getCaret().getDot()-editor.getCaret().getMark());
            int offset = 0;
            if(len!=0) {
                offset = Math.min(editor.getCaret().getDot(),editor.getCaret().getMark());
            } else {
                len = doc.getLength();
            }
            try {

                doc.remove(offset, len);

                doc.insertString(offset, copy, null);

            } catch(BadLocationException ex) {
                ex.printStackTrace();
            }
        }};
    private Action  statCaseAction=new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editor=model.getCurrentDocument().getTextComponent();
            Document doc = editor.getDocument();
            int z = editor.getLineCount();
            int x=doc.getLength();

            int y=0;

            Scanner s = new Scanner(editor.getText());
            while (s.hasNext()) {
               y+=s.nextLine().replace(" ","").length();
            }



            JOptionPane.showMessageDialog(
                    JNotepadPP.this,
                    "Your document has "+x+ "characters, "+y+" non-blank characters and "+z+" lines.",
                    "Informacija",
                    JOptionPane.INFORMATION_MESSAGE);
        }};
    private Action exitAction = new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
    private Action openDocumentAction=new AbstractAction() {
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Open file");
            if(fc.showOpenDialog(JNotepadPP.this)!=JFileChooser.APPROVE_OPTION) {
                return;
            }
            File fileName = fc.getSelectedFile();
            Path filePath = fileName.toPath();

            model.loadDocument(filePath);
            updateStaus(model.getCurrentDocument().getTextComponent());

        }
        };

    private void createMenus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.add(new JMenuItem(createDocumentAction));
        fileMenu.add(new JMenuItem(openDocumentAction));
        fileMenu.add(new JMenuItem(saveDocumentAction));
        fileMenu.add(new JMenuItem(saveAsDocumentAction));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem(exitAction));
        menuBar.add(fileMenu);
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        editMenu.add(new JMenuItem(deleteSelectedPartAction));
        editMenu.add(new JMenuItem(toggleCaseAction));
        editMenu.add(new JMenuItem(cutCaseAction));
        editMenu.add(new JMenuItem(copyCaseAction));
        editMenu.add(new JMenuItem(pasteCaseAction));
        editMenu.add(new JMenuItem(statCaseAction));


        JMenu toolsMenu = new JMenu("Tools");
        JMenu slova=new JMenu("Change case");
        slova.add(new JMenuItem(to_lowercase));
        slova.add(new JMenuItem(to_uppercase));
        slova.add(new JMenuItem(invert_case));
        JMenu sort=new JMenu("Sort");
        sort.add("Ascending");
        sort.add("Descending");

        toolsMenu.add(slova);
        toolsMenu.add(sort);

        menuBar.add(toolsMenu );


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
        menuBar.add(Languages);
        this.setJMenuBar(menuBar);
        flp.addLocalizationListener(new ILocalizationListener() {
            @Override
            public void localizationChanged() {
                Languages.setText(flp.getString("Languages"));
                editMenu.setText(flp.getString("edit"));
                fileMenu.setText(flp.getString("file"));
                toolsMenu.setText(flp.getString("tools"));
                 de.setText(flp.getString("de"));
                hr.setText(flp.getString("hr"));
                en.setText(flp.getString("en"));
                sort.setText(flp.getString("sort"));
                slova.setText(flp.getString("change_case"));
            }
        });


    }

    private void createToolbars() {
        JToolBar toolBar = new JToolBar("Alati");
        toolBar.setFloatable(true);

        toolBar.add(new JButton(createDocumentAction));
        toolBar.add(new JButton(openDocumentAction));
        toolBar.add(new JButton(saveDocumentAction));
        toolBar.add(new JButton(saveAsDocumentAction));
        toolBar.addSeparator();
        toolBar.add(new JButton(deleteSelectedPartAction));
        toolBar.add(new JButton(toggleCaseAction));
        toolBar.add(new JButton(cutCaseAction));
        toolBar.add(new JButton(copyCaseAction));
        toolBar.add(new JButton(pasteCaseAction));
        toolBar.add(new JButton(statCaseAction));

        this.getContentPane().add(toolBar, BorderLayout.PAGE_START);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                LocalizationProvider.getInstance().setLanguage("en");
                new JNotepadPP().setVisible(true);
            }
        });
    }
}
