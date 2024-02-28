package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    
    private CalculatorView theView;
    private CalculatorModel theModel;
    
    public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        
        this.theView.addCalculateListener(new CalculateListener());
		this.theView.addAddListener(new AddListener());
		this.theView.addSubtractListener(new SubtractListener());
		this.theView.addMultiplyListener(new MultiplyListener());
		this.theView.addDivideListener(new DivideListener());
    }
    
    class CalculateListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            
            int firstNumber, secondNumber = 0;
            
            try{
                
                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();
                String operator = theView.getOperatorLabel();
                if (operator == "+") {
                    theModel.addTwoNumbers(firstNumber, secondNumber);
                } else if (operator == "-") {
                    theModel.subtractTwoNumbers(firstNumber, secondNumber);
                } else if (operator == "x") {
                    theModel.multiplyTwoNumbers(firstNumber, secondNumber);
                } else if (operator == "/") {
                    theModel.divideTwoNumbers(firstNumber, secondNumber);
                }
                
                theView.setCalcSolution(theModel.getCalculationValue());
                
            }
            
            catch(NumberFormatException ex){
                
                System.out.println(ex);
                
                theView.displayErrorMessage("You Need to Enter 2 Integers");
                
            }
            
        }
        
    }
    
	class AddListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
			theView.setOperatorLabel("+");
        }
	}	

	class SubtractListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
			theView.setOperatorLabel("-");
        }
	}	

	class MultiplyListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
			theView.setOperatorLabel("x");
        }
	}	

	class DivideListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
			theView.setOperatorLabel("/");
        }
	}	
}
