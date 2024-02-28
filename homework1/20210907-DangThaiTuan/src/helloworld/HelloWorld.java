package helloworld;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class HelloWorld {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Hello World");
    JTextField textField = new JTextField(20);

    JButton button = new JButton("Ok");
    textField.setBounds(50, 20, 200, 30);
    button.setBounds(100, 60, 100, 30);

    frame.add(textField);
    frame.add(button);

    frame.setSize(300, 150);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    button.addActionListener(e -> {
      String name = textField.getText();
      JOptionPane.showMessageDialog(frame, "Xin chào, " + name + "!");
    });
  }

}
