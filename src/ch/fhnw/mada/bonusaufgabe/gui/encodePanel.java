package ch.fhnw.mada.bonusaufgabe.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class encodePanel extends JPanel {

    private JLabel inputPath = new JLabel();
    private JLabel outputPath = new JLabel("");
    private JButton chooseInputFile = new JButton("select file");
    private JButton chooseOutputPath = new JButton("select output path");
    private JCheckBox openAfterEncode = new JCheckBox("open file after encode");
    private JButton encodeFile = new JButton("Encode file");

    private File inputTextFile;

    public encodePanel()
    {
        setLayout(new GridLayout(9,1));

        chooseInputFile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInputTextFile();
            }
        });

        chooseOutputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOutPutPath();
            }
        });


        add(new JLabel("Kodieren"));
        add(new JLabel("ASCII text file:"));
        add(inputPath);
        add(chooseInputFile);
        add(new JLabel("Output path:"));
        add(outputPath);
        add(chooseOutputPath);
        add(openAfterEncode);
        add(encodeFile);

    }

    private void setOutPutPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            outputPath.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void setInputTextFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
            inputTextFile = chooser.getSelectedFile();
            inputPath.setText(inputTextFile.getAbsolutePath());
        }
    }

}