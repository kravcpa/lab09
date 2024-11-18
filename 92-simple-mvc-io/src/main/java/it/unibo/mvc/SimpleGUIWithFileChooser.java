package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;

    private final JFrame frame;
    private final Controller controller;

    /**
     * constructor.
     */
    public SimpleGUIWithFileChooser() {
        this.frame = new JFrame();
        this.controller = new Controller();

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        final JButton btn = new JButton("Save");

        final JPanel topBar = new JPanel();
        final JTextField browsePath = new JTextField();
        final JButton browseBtn = new JButton("Browse");

        browsePath.setEditable(false);
        browsePath.setText(controller.getCurrentFilePath());

        topBar.setLayout(new BorderLayout());
        topBar.add(browsePath, BorderLayout.CENTER);
        topBar.add(browseBtn, BorderLayout.LINE_END);

        panel.add(topBar, BorderLayout.NORTH);
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

        browseBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooster = new JFileChooser("Choose where to save");
                fileChooster.setSelectedFile(controller.getCurrentFile());

                final int result = fileChooster.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File destination = fileChooster.getSelectedFile();
                        controller.setCurrentFile(destination);
                        browsePath.setText(controller.getCurrentFilePath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "unexpected behavior", JOptionPane.ERROR_MESSAGE);
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
        new SimpleGUIWithFileChooser().display();
    }

}
