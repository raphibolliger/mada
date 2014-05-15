package ch.fhnw.mada.bonusaufgabe.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt von raphi am 14.05.14.
 * Version : 1.0.0
 * Modul: algd1
 */
public class MainForm extends JFrame {

    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField textBoxInputFile;
    private JTextField textBoxOutputPath;
    private JButton buttonChooseOutputPath;
    private JCheckBox checkBoxOpen;
    private JButton buttonEncode;
    private JButton buttonChooseTextFile;

    public MainForm()
    {
        panel1.add(tabbedPane1);
        tabbedPane1.
        add(panel1);

        buttonChooseTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseInputFile();
            }
        });

        buttonChooseOutputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void chooseInputFile()
    {

    }

}
