package gui;

import core.interpreter.Interpreter;

import javax.swing.*;

public class PseudocodeInterpreter extends JFrame {

    private JTextPane programTextPane;
    private JTextPane outputTextPane;
    private JButton runButton;
    private JPanel jPanel;

    public PseudocodeInterpreter() {
        super("PseudocodeInterpreter");
        setContentPane(jPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        runButton.addActionListener(e -> buttonRunClick());

        Interpreter.addOutputListener(this::programText);

        pack();
    }

    private void buttonRunClick() {
        String text = programTextPane.getText();
        outputTextPane.setText("");
        Interpreter.interpret(text);
        Interpreter.execute();
    }

    private void programText(String text) {
        String originalText = outputTextPane.getText();
        outputTextPane.setText(originalText + text + "\n");
    }

    public static void main(String[] args) {
        new PseudocodeInterpreter().setVisible(true);
    }
}
