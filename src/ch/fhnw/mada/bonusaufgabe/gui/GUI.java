package ch.fhnw.mada.bonusaufgabe.gui;

import ch.fhnw.mada.bonusaufgabe.logic.DecodeData;
import ch.fhnw.mada.bonusaufgabe.logic.DecodeManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

    private JPanel encodePanel = new encodePanel();
    private JPanel decodePanel = new JPanel();

    private JTextField textFiledInputFile = new JTextField("Bitte wählen Sie eine ASCII formatierte Textdatei aus");
    private JButton chooseInputFile = new JButton("Wählen...");
    private JTextField outputPath = new JTextField("Bitte wählen Sie einen Ausgabefad aus");
    private JButton chooseOutputPath = new JButton("Wählen...");
    private JCheckBox openAfterEncode = new JCheckBox("Dateien nach dem Kodieren öffnen");
    private JButton buttonEncode = new JButton("Kodieren");

    private File inputFile;

    public GUI()
    {
        setTitle("Mathematik für die Datenkommunikation");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(540,250));

        setLayout(new BorderLayout());

        // add the listeners to buttons
        chooseInputFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInputfile();
            }
        });

        chooseOutputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
                    outputPath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        buttonEncode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecodeManager decodeManager = new DecodeManager();

                DecodeData decodeData = new DecodeData(inputFile, outputPath.getText());
                try
                {
                    decodeManager.decode(decodeData);
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
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

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(3,1));

        JPanel temp1 = new JPanel();
        temp1.setLayout(new FlowLayout());
        textFiledInputFile.setPreferredSize(new Dimension(400, 23));
        textFiledInputFile.setEnabled(false);
        temp1.add(textFiledInputFile);
        temp1.add(chooseInputFile);
        containerPanel.add(temp1);

        JPanel temp2 = new JPanel();
        temp2.setLayout(new FlowLayout());
        outputPath.setPreferredSize(new Dimension(400, 23));
        outputPath.setEnabled(false);
        temp2.add(outputPath);
        temp2.add(chooseOutputPath);
        containerPanel.add(temp2);

        JPanel temp3 = new JPanel();
        temp3.setLayout(new FlowLayout());
        openAfterEncode.setPreferredSize(new Dimension(400, 23));
        temp3.add(openAfterEncode);
        buttonEncode.setFont(new Font("", Font.BOLD, 12));
        temp3.add(buttonEncode);
        containerPanel.add(temp3);

        panelKodieren.add(containerPanel, BorderLayout.NORTH);

        // create dekodieren for tabbedPane as a JPanel


        // add tabbedPane on top
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Kodieren", panelKodieren);
        tabbedPane.addTab("Dekodieren", new JPanel());
        add(tabbedPane, BorderLayout.CENTER);


        pack();
        setVisible(true);
    }

    private void setInputfile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            inputFile = chooser.getSelectedFile();
            textFiledInputFile.setText(inputFile.getAbsolutePath());
        }

    }

}
