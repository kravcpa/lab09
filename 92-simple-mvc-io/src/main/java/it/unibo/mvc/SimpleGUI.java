// CPD-OFF
package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;

    private final JFrame frame;
    private final Controller controller;

    /**
     * constructor.
     */
    public SimpleGUI() {
        this.frame = new JFrame();
        this.controller = new Controller();

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        final JButton btn = new JButton("Save");

        panel.add(textArea, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final int choice = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Confirm",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (choice == 0) {
                        controller.saveString(textArea.getText());
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }

        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Display.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * main.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }

}
