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

  private JLabel firstNumberLabel = new JLabel("First Number");
  private JTextField firstNumber = new JTextField();

  private JLabel secondNumberLabel = new JLabel("Second Number");
  private JTextField secondNumber = new JTextField();

  private JLabel resultLabel = new JLabel("Result");
  private JLabel calcSolution = new JLabel("");

  private JButton addLabel = new JButton("+");
  private JButton subLabel = new JButton("-");
  private JButton mulLabel = new JButton("*");
  private JButton divLabel = new JButton("/");

  private JButton perLabel = new JButton("%");
  private JButton clearLabel = new JButton("Clear");

  CalculatorView() {

    JPanel calcPanel = new JPanel(new GridLayout(6, 2));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(600, 200);

    calcPanel.add(firstNumberLabel);
    calcPanel.add(firstNumber);
    calcPanel.add(secondNumberLabel);
    calcPanel.add(secondNumber);
    calcPanel.add(resultLabel);
    calcPanel.add(calcSolution);

    calcPanel.add(addLabel);
    calcPanel.add(subLabel);
    calcPanel.add(mulLabel);
    calcPanel.add(divLabel);
    calcPanel.add(perLabel);
    calcPanel.add(clearLabel);

    this.add(calcPanel);
  }

  public int getFirtNumber() {
    return Integer.parseInt(firstNumber.getText());
  }

  public int getSecondNumber() {
    return Integer.parseInt(secondNumber.getText());
  }

  public int getResult() {
    return Integer.parseInt(calcSolution.getText());
  }

  public void setResult(int solution) {
    calcSolution.setText(Integer.toString(solution));
  }

  public void clear(){
    firstNumber.setText("");
    secondNumber.setText("");
    calcSolution.setText("");
  }

  void addAction(ActionListener listenerForAddButton) {
    addLabel.addActionListener(listenerForAddButton);
  }

  void subAction(ActionListener listenerForSubButton) {
    subLabel.addActionListener(listenerForSubButton);
  }

  void mulAction(ActionListener listenerForCalcButton) {
    mulLabel.addActionListener(listenerForCalcButton);
  }

  void divAction(ActionListener listenerForMulButton) {
    divLabel.addActionListener(listenerForMulButton);
  }

  void perAction(ActionListener listenerForPerButton) {
    perLabel.addActionListener(listenerForPerButton);
  }

  void clearAction(ActionListener listenerForClearButton) {
    clearLabel.addActionListener(listenerForClearButton);
  }

  void displayErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage);
  }
}
