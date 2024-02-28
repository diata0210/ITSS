

import javax.swing.JOptionPane;


public class HelloWorld {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null,"Uhhh, your name is: ","Name asked",JOptionPane.PLAIN_MESSAGE);
        
        JOptionPane.showMessageDialog(null, "Hello " + name, "Hello World" , JOptionPane.INFORMATION_MESSAGE);
    }
}