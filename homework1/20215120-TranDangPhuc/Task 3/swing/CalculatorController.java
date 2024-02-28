import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class CalculatorController {
	
	private CalculatorView theView;
	private CalculatorModel theModel;
	
	public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		
		// Tell the View that when ever the calculate button
		// is clicked to execute the actionPerformed method
		// in the CalculateListener inner class
		
		this.theView.addCalculateListener(new CalculateListener());
		this.theView.subCalculateListener(new CalculateListener());
		this.theView.mulCalculateListener(new CalculateListener());
		this.theView.divCalculateListener(new CalculateListener());
		this.theView.modCalculateListener(new CalculateListener());
		this.theView.clearCalculateListener(new ClearListener());
	}
	
	class CalculateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			int firstNumber, secondNumber = 0;
			
			// Surround interactions with the view with
			// a try block in case numbers weren't
			// properly entered
			
			try{
			
				firstNumber = theView.getFirstNumber();
				secondNumber = theView.getSecondNumber();
				String command = e.getActionCommand();
				switch (command) {
					case "+":
						theModel.addTwoNumbers(firstNumber, secondNumber);
						break;
					case "-":
						theModel.subtractTwoNumbers(firstNumber, secondNumber);
						break;
					case "*":
						theModel.mulTwoNumbers(firstNumber, secondNumber);
						break;
					case "/":
						try {
							theModel.divTwoNumbers(firstNumber, secondNumber);
						} catch (ArithmeticException ex) {
							theView.displayErrorMessage("You can't divide by 0");
							return; 
						}
						break;
					case "%":
						try {
							theModel.modTwoNumbers(firstNumber, secondNumber);
						} catch (ArithmeticException ex) {
							theView.displayErrorMessage("You can't divide by 0");
							return; 
						}
						break;
				}
				
				
				theView.setCalcSolution(theModel.getCalculationValue());
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				theView.displayErrorMessage("You Need to Enter 2 Integers");
				
			}
			
		}
		
	}
	class ClearListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			theView.clear();
			
		}
		
	}
	
}