package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

	private CalculatorView theView;
	private CalculatorModel theModel;

	public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		this.theView.addAction(new CalculatorActionListener());
		this.theView.subAction(new CalculatorActionListener());
		this.theView.mulAction(new CalculatorActionListener());
		this.theView.divAction(new CalculatorActionListener());
		this.theView.perAction(new CalculatorActionListener());
		this.theView.clearAction(new ClearActionListener());
	}

	class CalculatorActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int firstNumber, secondNumber;
			try {
				firstNumber = theView.getFirtNumber();
				secondNumber = theView.getSecondNumber();
				String command = e.getActionCommand();
				switch (command) {
					case "+":
						theModel.addTwoNumbers(firstNumber, secondNumber);
						break;
					case "-":
						theModel.subTwoNumbers(firstNumber, secondNumber);
						break;
					case "*":
						theModel.mulTwoNumbers(firstNumber, secondNumber);
						break;
					case "/":
						try {
							theModel.divTwoNumbers(firstNumber, secondNumber);
						} catch (ArithmeticException ex) {
							theView.displayErrorMessage("You can't divide by 0");
							return; // Stop further execution
						}
						break;
					case "%":
						try {
							theModel.perTwoNumbers(firstNumber, secondNumber);
						} catch (ArithmeticException ex) {
							theView.displayErrorMessage("You can't divide by 0");
							return; // Stop further execution
						}
						break;
				}
				theView.setResult(theModel.getCalculationValue());
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("You Need to Enter 2 Integers");
			}
		}
	}

	class ClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.clear();
		}
	}
}
