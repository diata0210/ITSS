package swing;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class CalculatorView extends JFrame {
    private JTextField firstNumber = new JTextField();
    
    private JTextField secondNumber = new JTextField();
    private JButton addButton = new JButton("+");
    private JButton subButton = new JButton("-");
    private JButton mulButton = new JButton("x");
    private JButton divButton = new JButton(":");
    private JLabel calValue = new JLabel("");
    private JButton clearButton = new JButton("C");

    CalculatorView() {
        JPanel calPanel = new JPanel(new GridLayout(6, 2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,200);
        
        calPanel.add(new JLabel("first Number"));
        calPanel.add(firstNumber);
        calPanel.add(new JLabel("second number"));
        calPanel.add(secondNumber);
        calPanel.add(new JLabel("calculated value"));
        calPanel.add(calValue);

        calPanel.add(addButton);
        calPanel.add(subButton);
        calPanel.add(mulButton);
        calPanel.add(divButton);
        calPanel.add(clearButton);
        this.add(calPanel);
    }

    public int getFirtNumber(){
        return Integer.parseInt(firstNumber.getText());
    }
    public int getSecondNumber() {
    return Integer.parseInt(secondNumber.getText());
  }
  public void setResult(int solution) {
    calValue.setText(Integer.toString(solution));
  }
  public int getResult() {
    return Integer.parseInt(calValue.getText());
  }
  void addAction(ActionListener listenerForAddButton) {
    addButton.addActionListener(listenerForAddButton);
  }

  void subAction(ActionListener listenerForSubButton) {
    subButton.addActionListener(listenerForSubButton);
  }

  void mulAction(ActionListener listenerForCalcButton) {
    mulButton.addActionListener(listenerForCalcButton);
  }

  void divAction(ActionListener listenerForMulButton) {
    divButton.addActionListener(listenerForMulButton);
  }
  void clearAction(ActionListener listenerForClearButton) {
    clearButton.addActionListener(listenerForClearButton);
  }
  void displayErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage);
  }
  public void clear(){
    firstNumber.setText("");
    secondNumber.setText("");
    calValue.setText("");
  }
}
