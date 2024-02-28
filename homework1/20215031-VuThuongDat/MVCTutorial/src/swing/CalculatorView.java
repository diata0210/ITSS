package swing;

import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatorView extends JFrame{

    private JTextField firstNumber  = new JTextField(10);
    private JLabel operatorLabel = new JLabel("+");
    private JTextField secondNumber = new JTextField(10);
    private JTextField calcSolution = new JTextField(10);
    private JButton addButton = new JButton("+");
    private JButton subtractButton = new JButton("-");
    private JButton multiplyButton = new JButton("x");
    private JButton divideButton = new JButton("/");
    private JButton calculateButton = new JButton("=");
    
    CalculatorView(){
        
        JPanel Panel = new JPanel();
        Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 120);
        calcSolution.setEditable(false);
        
		JPanel calcPanel = new JPanel();
        calcPanel.add(firstNumber);
		calcPanel.add(operatorLabel);
        calcPanel.add(secondNumber);
		calcPanel.add(calculateButton);
        calcPanel.add(calcSolution);
        
        JPanel operatorPanel = new JPanel();
		operatorPanel.add(new JLabel("Operator: "));
        operatorPanel.add(addButton);
        operatorPanel.add(subtractButton);
        operatorPanel.add(multiplyButton);
        operatorPanel.add(divideButton);
        
		Panel.add(calcPanel);
        Panel.add(operatorPanel);
        
        this.add(Panel);
        
    }
    
    public int getFirstNumber(){
        
        return Integer.parseInt(firstNumber.getText());
        
    }
    
    public int getSecondNumber(){
        
        return Integer.parseInt(secondNumber.getText());
        
    }
    
    public int getCalcSolution(){
        
        return Integer.parseInt(calcSolution.getText());
        
    }
    
    public void setCalcSolution(int solution){
        
        calcSolution.setText(Integer.toString(solution));
        
    }

    public String getOperatorLabel(){
        return operatorLabel.getText();
    }

    public void setOperatorLabel(String operator){
        operatorLabel.setText(operator);
    }

    void addCalculateListener(ActionListener listenForButton){
        
        calculateButton.addActionListener(listenForButton);

    }
	void addAddListener(ActionListener listenForButton){
		addButton.addActionListener(listenForButton);
	}

	void addSubtractListener(ActionListener listenForButton){
		subtractButton.addActionListener(listenForButton);
	}

	void addMultiplyListener(ActionListener listenForButton){
		multiplyButton.addActionListener(listenForButton);
	}

	void addDivideListener(ActionListener listenForButton){
		divideButton.addActionListener(listenForButton);
	}

    void displayErrorMessage(String errorMessage){
        
        JOptionPane.showMessageDialog(this, errorMessage);
        
    }
    
}
