package ch.fhnw.mada.bonusaufgabe.gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JPanel encodePanel = new encodePanel();
    private JPanel decodePanel = new JPanel();

    public GUI()
    {
        setTitle("Encode/decode textfiles");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400,400));

        setLayout(new GridLayout(1,2));

        add(encodePanel);
        add(decodePanel);

        pack();
        setVisible(true);
    }

}
