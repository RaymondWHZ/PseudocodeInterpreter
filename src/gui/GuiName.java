package gui;

import core.interpreter.Interpreter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GuiName extends JFrame {

    private JTextPane programTextPane;
    private JTextPane outputTextPane;
    private JButton runButton;
    private JPanel jPanel;

    public GuiName()
    {
        super("GuiName");
        setContentPane(jPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        runButton.addActionListener(this::ButtonRunClick);

        Interpreter.addOutputListener(this::ProgramText);

        pack();
    }

    private void ButtonRunClick(ActionEvent arg)
    {
        String text = programTextPane.getText();
        Interpreter.interpret(text);
        Interpreter.execute();
    }

    private void ProgramText(String arg)
    {
        String originalText = outputTextPane.getText();
        outputTextPane.setText(originalText + arg + "\n");
    }

    public static void main(String[] args) {
        new GuiName().setVisible(true);
    }
}
