package ch.fhnw.mada.bonusaufgabe.gui;

import ch.fhnw.mada.bonusaufgabe.logic.DecodeData;
import ch.fhnw.mada.bonusaufgabe.logic.DecodeManager;
import ch.fhnw.mada.bonusaufgabe.logic.EncodeData;
import ch.fhnw.mada.bonusaufgabe.logic.EncodeManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

    private JTextField textFieldTextInputFile = new JTextField("Bitte wählen Sie eine ASCII formatierte Textdatei aus");
    private JButton chooseInputTextFile = new JButton("Wählen...");
    private JTextField textFieldEncodingOutputPath = new JTextField("Bitte wählen Sie einen Ausgabefad aus");
    private JButton chooseEncodingOutputPath = new JButton("Wählen...");
    private JCheckBox openAfterEncode = new JCheckBox("Kodierungstabelle nach dem Kodieren öffnen");
    private JButton buttonEncode = new JButton("Kodieren");

    private JTextField textFieldInputFileEncoded = new JTextField("Bitte wählen Sie eine Kodierte Datei aus");
    private JButton chooseEncodedInputFile = new JButton("Wählen...");
    private JTextField textFieldInputEncodeTable = new JTextField("Bitte wählen Sie die entsprechende Kodierungstabelle aus");
    private JButton chooseEncodedTableFile = new JButton("Wählen...");
    private JTextField textFieldDecodingOutputPath = new JTextField("Bitte wählen Sie einen Ausgabepfad aus");
    private JButton chooseDecodingOutputPath = new JButton("Wählen...");
    private JCheckBox openAfterDecode = new JCheckBox("Textdatei nach dem Dekodieren öffnen");
    private JButton buttonDecode = new JButton("Dekodieren");

    private File inputTextFile;
    private File inputEncodedFile;
    private File inputDecodingTable;

    public GUI()
    {
        setTitle("Mathematik für die Datenkommunikation");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(560,270));

        setLayout(new BorderLayout());

        // add the listeners to buttons
        chooseInputTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInputTextFile();
            }
        });
        chooseEncodingOutputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEncodeOutputPath();
            }
        });
        buttonEncode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeEncoding();
            }
        });
        chooseEncodedInputFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEncoededInputFile();
            }
        });
        chooseEncodedTableFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEncodedTableFile();
            }
        });
        chooseDecodingOutputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDecodingOutputPath();
            }
        });
        buttonDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeDecoding();
            }
        });

        // add title on top
        Font font = new Font("Verdana", Font.BOLD, 18);
        JLabel title = new JLabel("Kodieren und Dekodieren von ASCII Textdateien", JLabel.CENTER);
        title.setFont(font);
        add(title, BorderLayout.NORTH);

        // create kodieren for tabbedPane as a JPanel
        JPanel panelKodieren = new JPanel();
        panelKodieren.setLayout(new BorderLayout());

        JPanel containerPanelKodieren = new JPanel();
        containerPanelKodieren.setLayout(new GridLayout(4, 1));

        JPanel temp1Kodieren = new JPanel();
        temp1Kodieren.setLayout(new FlowLayout());
        textFieldTextInputFile.setPreferredSize(new Dimension(400, 23));
        textFieldTextInputFile.setEnabled(false);
        temp1Kodieren.add(textFieldTextInputFile);
        temp1Kodieren.add(chooseInputTextFile);
        containerPanelKodieren.add(temp1Kodieren);

        JPanel temp2Kodieren = new JPanel();
        temp2Kodieren.setLayout(new FlowLayout());
        textFieldEncodingOutputPath.setPreferredSize(new Dimension(400, 23));
        textFieldEncodingOutputPath.setEnabled(false);
        temp2Kodieren.add(textFieldEncodingOutputPath);
        temp2Kodieren.add(chooseEncodingOutputPath);
        containerPanelKodieren.add(temp2Kodieren);

        JPanel temp3Kodieren = new JPanel();
        temp3Kodieren.setLayout(new FlowLayout());
        openAfterEncode.setPreferredSize(new Dimension(400, 23));
        temp3Kodieren.add(openAfterEncode);
        buttonEncode.setFont(new Font("", Font.BOLD, 12));
        temp3Kodieren.add(buttonEncode);
        containerPanelKodieren.add(temp3Kodieren);

        panelKodieren.add(containerPanelKodieren, BorderLayout.NORTH);

        // create dekodieren for tabbedPane as a JPanel
        JPanel panelDekodieren = new JPanel();
        panelDekodieren.setLayout(new BorderLayout());

        JPanel containerPanelDekodieren = new JPanel();
        containerPanelDekodieren.setLayout(new GridLayout(4,1));

        JPanel temp1Dekodieren = new JPanel();
        temp1Dekodieren.setLayout(new FlowLayout());
        textFieldInputFileEncoded.setPreferredSize(new Dimension(400, 23));
        textFieldInputFileEncoded.setEnabled(false);
        temp1Dekodieren.add(textFieldInputFileEncoded);
        temp1Dekodieren.add(chooseEncodedInputFile);
        containerPanelDekodieren.add(temp1Dekodieren);

        JPanel temp2Dekodieren = new JPanel();
        temp2Dekodieren.setLayout(new FlowLayout());
        textFieldInputEncodeTable.setPreferredSize(new Dimension(400, 23));
        textFieldInputEncodeTable.setEnabled(false);
        temp2Dekodieren.add(textFieldInputEncodeTable);
        temp2Dekodieren.add(chooseEncodedTableFile);
        containerPanelDekodieren.add(temp2Dekodieren);

        JPanel temp3Dekodieren = new JPanel();
        temp3Dekodieren.setLayout(new FlowLayout());
        textFieldDecodingOutputPath.setPreferredSize(new Dimension(400, 23));
        textFieldDecodingOutputPath.setEnabled(false);
        temp3Dekodieren.add(textFieldDecodingOutputPath);
        temp3Dekodieren.add(chooseDecodingOutputPath);
        containerPanelDekodieren.add(temp3Dekodieren);

        JPanel temp4Dekodieren = new JPanel();
        temp4Dekodieren.setLayout(new FlowLayout());
        openAfterDecode.setPreferredSize(new Dimension(400, 23));
        temp4Dekodieren.add(openAfterDecode);
        buttonDecode.setFont(new Font("", Font.BOLD, 12));
        temp4Dekodieren.add(buttonDecode);
        containerPanelDekodieren.add(temp4Dekodieren);

        panelDekodieren.add(containerPanelDekodieren, BorderLayout.NORTH);

        // add tabbedPane on top
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Kodieren", panelKodieren);
        tabbedPane.addTab("Dekodieren", panelDekodieren);
        add(tabbedPane, BorderLayout.CENTER);

        JLabel autor = new JLabel("by Andreas Lüscher / Florian Bruggisser / Raphael Bolliger", JLabel.CENTER);
        autor.setPreferredSize(new Dimension(400,25));
        add(autor, BorderLayout.SOUTH);

        setResizable(false);

        pack();
        setVisible(true);
    }

    private void makeEncoding() {
        DecodeManager decodeManager = new DecodeManager();

        DecodeData decodeData = new DecodeData(inputTextFile, textFieldEncodingOutputPath.getText());
        try
        {
            decodeManager.decode(decodeData, openAfterEncode.isSelected());
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void setEncodeOutputPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            textFieldEncodingOutputPath.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void setInputTextFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            inputTextFile = chooser.getSelectedFile();
            textFieldTextInputFile.setText(inputTextFile.getAbsolutePath());
            textFieldEncodingOutputPath.setText(inputTextFile.getParentFile().getPath());
        }

    }

    private void setEncoededInputFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "dat");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            inputEncodedFile = chooser.getSelectedFile();
            textFieldInputFileEncoded.setText(inputEncodedFile.getAbsolutePath());
            textFieldDecodingOutputPath.setText(inputEncodedFile.getParentFile().getPath());
        }

    }

    private void setEncodedTableFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(inputEncodedFile.getParentFile());
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            inputDecodingTable = chooser.getSelectedFile();
            textFieldInputEncodeTable.setText(inputDecodingTable.getAbsolutePath());
        }

    }

    private void setDecodingOutputPath() {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            textFieldDecodingOutputPath.setText(chooser.getSelectedFile().getAbsolutePath());
        }

    }

    private void makeDecoding()
    {
        EncodeManager encodeManager = new EncodeManager();
        EncodeData encodeData = new EncodeData();
        encodeManager.readInputFiles(inputEncodedFile, inputDecodingTable, encodeData);
        encodeManager.convertByteArrayToBitRepresentationString(encodeData);
        encodeManager.convertTableStringToHashMap(encodeData);
        encodeManager.cutLastCharacters(encodeData);
        encodeManager.encodeText(encodeData);
        encodeManager.writeTextFile(encodeData, textFieldDecodingOutputPath.getText(), openAfterDecode.isSelected());
    }

}
