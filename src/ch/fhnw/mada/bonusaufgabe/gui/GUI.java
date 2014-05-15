package ch.fhnw.mada.bonusaufgabe.gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JPanel encodePanel = new encodePanel();
    private JPanel decodePanel = new JPanel();

    private JTextField inputFile = new JTextField("Bitte wählen Sie eine ASCII formatierte Textdatei aus");
    private JButton chooseInputFile = new JButton("Wählen...");
    private JTextField outputPath = new JTextField("Bitte wählen Sie einen Ausgabefad aus");
    private JButton chooseOutputPath = new JButton("Wählen...");
    private JCheckBox openAfterEncode = new JCheckBox("Dateien nach dem Kodieren öffnen");
    private JButton buttonEncode = new JButton("Kodieren");

    public GUI()
    {
        setTitle("Mathematik für die Datenkommunikation");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400,400));

        setLayout(new BorderLayout());

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
        inputFile.setPreferredSize(new Dimension(400,20));
        temp1.add(inputFile);
        temp1.add(new JButton("Wählen..."));
        containerPanel.add(temp1);

        JPanel temp2 = new JPanel();
        temp2.setLayout(new FlowLayout());
        temp2.add(new JTextField("Bitte wählen Sie einen Ausgabepfad"));
        temp2.add(new JButton("Wählen..."));
        containerPanel.add(temp2);

        JPanel temp3 = new JPanel();
        temp3.setLayout(new FlowLayout());
        temp3.add(new JCheckBox("Datei nach dem Kodieren öffnen..."));
        temp3.add(new JButton("Kodieren"));
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

}
