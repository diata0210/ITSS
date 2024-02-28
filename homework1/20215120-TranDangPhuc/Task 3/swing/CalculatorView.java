// This is the View
// Its only job is to display what the user sees
// It performs no calculations, but instead passes
// information entered by the user to whomever needs
// it. 


import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatorView extends JFrame{
	
	private JLabel firstNumberLabel = new JLabel("First Number");
	private JTextField firstNumber  = new JTextField(10);
	

	private JLabel secondLabelLabel = new JLabel("Second Number");	
	private JTextField secondNumber = new JTextField(10);
	
	private JLabel resultLabel  = new JLabel("Result");
	private JLabel result =  new JLabel("");

	private JButton addLabel = new JButton("+");
	private JButton suButton = new JButton("-");
	private	JButton mulButton = new JButton("*");
	private JButton divButton = new JButton("/");
	private JButton modButton = new JButton("%");
	private JButton clearButton = new JButton("Clear");
	
	
	CalculatorView(){
		
		// Sets up the view and adds the components
		
		JPanel calcPanel = new JPanel(new GridLayout(6,2));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 200);
		calcPanel.add(firstNumberLabel);
		calcPanel.add(firstNumber);
		calcPanel.add(secondLabelLabel);
		calcPanel.add(secondNumber);
		calcPanel.add(resultLabel);
		calcPanel.add(result);
		calcPanel.add(addLabel);
		calcPanel.add(suButton);
		calcPanel.add(mulButton);
		calcPanel.add(divButton);
		calcPanel.add(modButton);
		calcPanel.add(clearButton);
		
		this.add(calcPanel);
		this.setSize( 200, 240 );
		// End of setting up the components --------
		
	}
	
	public int getFirstNumber(){
		
		return Integer.parseInt(firstNumber.getText());
		
	}
	
	public int getSecondNumber(){
		
		return Integer.parseInt(secondNumber.getText());
		
	}
	
	public int getCalcSolution(){
		
		return Integer.parseInt(result.getText());
		
	}
	
	public void setCalcSolution(int solution){
		
		result.setText(Integer.toString(solution));
		
	}
	// Reset the result label
	public void clear(){
		firstNumber.setText("");
		secondNumber.setText("");
		result.setText("");
	}
	// If the calculateButton is clicked execute a method
	// in the Controller named actionPerformed
	
	void addCalculateListener(ActionListener listenForCalcButton){
		
		addLabel.addActionListener(listenForCalcButton);
		
	}
	void subCalculateListener(ActionListener listenForCalcButton){
		
		suButton.addActionListener(listenForCalcButton);
		
	}
	void mulCalculateListener(ActionListener listenForCalcButton){
		
		mulButton.addActionListener(listenForCalcButton);
		
	}
	void divCalculateListener(ActionListener listenForCalcButton){
		
		divButton.addActionListener(listenForCalcButton);
		
	}
	void modCalculateListener(ActionListener listenForCalcButton){
		
		modButton.addActionListener(listenForCalcButton);
		
	}
	void clearCalculateListener(ActionListener listenForCalcButton){
		
		clearButton.addActionListener(listenForCalcButton);
		
	}
	
	// Open a popup that contains the error message passed
	
	void displayErrorMessage(String errorMessage){
		
		JOptionPane.showMessageDialog(this, errorMessage);
		
	}
	
}