import core.interpreter.Interpreter;
import core.interpreter.OutputListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static core.interpreter.Interpreter.interpret;

public class GuiName {


    public static void main(String[] args) {
        JFrame frame = new JFrame("GuiName");
        frame.setContentPane(new GuiName().Jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTextPane textPane1;
    private JTextPane textPane2;
    private JButton RUNButton;
    private JPanel Jpanel;
    public GuiName()
    {
        RUNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonRunClick(e);
            }
        });
        Interpreter.addOutputListener(new OutputListener() {
            @Override
            public void outputOccur(String text) {
                ProgramText(text);
            }
        });

    }
    private void ButtonRunClick(ActionEvent arg)
    {
            String text1 = textPane1.getText();
            Interpreter.output(text1);
            textPane2.setText(text1);

    }
    private void ProgramText(String arg)
    {

    }

}
